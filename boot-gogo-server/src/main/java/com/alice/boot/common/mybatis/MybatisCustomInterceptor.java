package com.alice.boot.common.mybatis;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

/**
 * mybatis拦截器,用于自动处理insert与update方法
 * 为insert update添加createTime与updateTime
 *
 * @author alice on 2019/12/31.
 * @version 1.0
 * @since 1.0
 */
@Component
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class MybatisCustomInterceptor implements Interceptor {
    private final static Logger log = LoggerFactory.getLogger(MybatisCustomInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object object = invocation.getArgs()[1];
        //sql类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        if (SqlCommandType.INSERT.equals(sqlCommandType)) {
            Date now = new Date();
            try {
                //insert操作时，自动插入创建时间
                Field fieldCreate = object.getClass().getDeclaredField("createTime");
                fieldCreate.setAccessible(true);
                fieldCreate.set(object, now);
            } catch (NoSuchFieldException e) {
                log.debug("not found field `createTime` skip interceptor");
            }
            try {
                Field fieldUpdate = object.getClass().getDeclaredField("updateTime");
                fieldUpdate.setAccessible(true);
                fieldUpdate.set(object, now);
            } catch (Exception e) {
                log.debug("not found field `updateTime`, skip interceptor");
            }
        } else {
            if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
                try {
                    //update时，自动更新
                    Field fieldUpdate = object.getClass().getDeclaredField("updateTime");
                    fieldUpdate.setAccessible(true);
                    fieldUpdate.set(object, new Date());
                } catch (NoSuchFieldException e) {
                    log.debug("not found field `updateTime`, skip interceptor");
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
