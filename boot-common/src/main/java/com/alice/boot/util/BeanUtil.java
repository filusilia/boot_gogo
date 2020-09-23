package com.alice.boot.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alice
 * 实体类转换工具
 **/
@Slf4j
public class BeanUtil {

    public static <T, F> List<T> convert(List<F> list, Class<T> targetClass) {
        if (targetClass == null) {
            log.error("targetClass is null");
            return null;
        }
        ArrayList<T> rs = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (Object source : list) {
                try {
                    T target = targetClass.newInstance();
                    BeanUtils.copyProperties(source, target);
                    rs.add(target);
                } catch (Exception e) {
                    log.error("convert error,error message => {}", e.getMessage());
                    return null;
                }
            }
        }

        return rs;
    }

    public static <S, R> R convert(S source, Class<R> targetClass) {
        try {
            if (source == null) {
                log.error("bean copy fail. case source is null");
                return null;
            } else {
                Constructor<R> constructor = targetClass.getDeclaredConstructor();
                R bean = constructor.newInstance();
                constructor.setAccessible(true);
                BeanUtils.copyProperties(source, bean);
                return bean;
            }
        } catch (Exception var3) {
            log.error("bean copy fail,message => {}", var3.getMessage());
            return null;
        }
    }
}
