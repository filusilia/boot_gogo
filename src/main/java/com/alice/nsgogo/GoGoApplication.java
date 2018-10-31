package com.alice.nsgogo;

import com.alibaba.druid.pool.DruidDataSource;
import com.alice.nsgogo.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * @author Aozaki on 2018/10/23.
 * @version 1.0
 * @since 1.0
 */
@SpringBootApplication
@EnableScheduling
//@AutoConfigureAfter(MapperAutoConfiguration.class)
@MapperScan(basePackages = "com.alice.nsgogo.mapper")
@ServletComponentScan
public class GoGoApplication {
    private final static Logger log = LoggerFactory.getLogger(GoGoApplication.class);

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(GoGoApplication.class);
//    }

    public static void main(String[] args) {
        log.info("Project {} Start Success！", Constants.PROJECT_NAME);
        log.info("Power by Aozaki ! ");
        log.info("\t\tVersion : {} \t", Constants.VER);
        SpringApplication.run(GoGoApplication.class, args);
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

}
