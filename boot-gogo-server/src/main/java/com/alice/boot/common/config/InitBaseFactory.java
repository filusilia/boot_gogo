package com.alice.boot.common.config;

import com.alice.boot.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Objects;

/**
 * @author ilia on 2020/9/01.
 * <p>
 * 初始工厂
 * </p>
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
public class InitBaseFactory {

    private final Environment env;

    @Autowired
    public InitBaseFactory(Environment env) {
        this.env = env;
    }

    /**
     * 初始化基础配置
     */
    @PostConstruct
    public void init() {
        try {
            Constants.MODE = env.getProperty("spring.profiles.active");
            switch (Objects.requireNonNull(Constants.MODE)) {
                case "dev":
                case "prod":
                    initDev();
                    break;
                case "test":
                    initDict();
                    break;
                default:
                    log.info("system workspace unknow ,exit.");
            }
            log.info("base constant load complete.");
        } catch (Exception e) {
            log.error("create basis config error ,system exit.\nerror message:{}", e.getMessage());
            System.exit(0);
        }

    }

    /**
     * 关闭项目.
     */
    @PreDestroy
    public void des() {
        log.info("Thank you for using,destroy now.");
    }

    /**
     * 初始化dict表基础配置
     */
    void initDict() {
        log.info("init dict start.");

    }

    /**
     * 初始化开发的配置
     */
    void initDev() {

    }
}
