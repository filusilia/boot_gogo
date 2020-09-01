package com.alice.boot.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Aozaki on 2020/9/01.
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class Constants {
    /**
     * 项目工作模式
     */
    public static String MODE = "";

    /**
     * 项目名称
     */
    public static String PROJECT = "";
    /**
     * 项目版本
     */
    public static String VERSION = "";
    /**
     * 管理授权码
     */
    public static String AUTH_CODE = "";
    /**
     * 锁定系统字段,默认0,未锁定
     */
    public static int SYSTEM_LOCKED = 0;

    /**
     * 项目中心ip(系统所在主机ip),,系统默认为可以查看配置文件 servlet.context-path
     */
    public static String PROJECT_IP = "127.0.0.1";
    /**
     * 项目指定tomcat 端口号,系统默认为可以查看配置文件server.port
     */
    public static String PROJECT_PORT = "";

    @Value(value = "${project.version}")
    private void setVersion(String ver) {
        Constants.VERSION = ver;
    }

    @Value(value = "${project.name}")
    private void setProjectName(String projectName) {
        Constants.PROJECT = projectName;
    }
}
