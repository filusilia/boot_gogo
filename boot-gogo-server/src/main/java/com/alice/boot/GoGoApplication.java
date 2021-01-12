package com.alice.boot;

import com.alice.boot.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author Aozaki on 2020/09/01.
 * @version 1.0
 * @since 1.0
 */
@EnableOpenApi
@SpringBootApplication
@EnableConfigurationProperties
@ServletComponentScan
@EnableTransactionManagement
@Slf4j
public class GoGoApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoGoApplication.class, args);
        log.info("project name : {}", Constants.PROJECT);
        log.info("project version : {}", Constants.VERSION);
        log.info("project port : {}", Constants.PROJECT_PORT);
        log.info("powered by : ilia");
    }
}
