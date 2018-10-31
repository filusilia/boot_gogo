package com.alice.nsgogo.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Aozaki on 2018/10/23.
 * @version 1.0
 * @since 1.0
 */
@PropertySource(value = {"classpath:project.properties"}, encoding = "UTF-8")
@Configuration
public class Constants {
    public static String VER;
    public static String PROJECT_NAME;

    @Value(value = "${project.version}")
    private void setVER(String VER) {
        Constants.VER = VER;
    }

    @Value(value = "${project.name}")
    private void setProjectName(String projectName) {
        Constants.PROJECT_NAME = projectName;
    }
}
