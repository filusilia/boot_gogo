package com.alice.boot.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Alice
 * Table: 登录日志
 */
@Table(name = "`go_login_log`")
@org.hibernate.annotations.Table(appliesTo = "`go_login_log`", comment = "登录日志表。")
@Entity
@Data
public class LoginLog implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 登录账号
     */
    @Column(name = "login_id", columnDefinition = "bigint(20) COLLATE utf8_bin DEFAULT NULL COMMENT '登录用户id'")
    private String loginId;

    /**
     * 登录IP地址
     */
    @Column(name = "ip", length = 50, columnDefinition = "varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '登录IP地址'")
    private String ip;

    /**
     * 登录地
     */
    @Column(name = "login_location", length = 50, columnDefinition = "varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '登录地'")
    private String loginLocation;

    /**
     * 浏览器类型
     */
    @Column(name = "browser", length = 50, columnDefinition = "varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '浏览器类型'")
    private String browser;

    /**
     * 操作系统
     */
    @Column(name = "os", length = 50, columnDefinition = "varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '操作系统'")
    private String os;

    /**
     * 登录状态
     * 0：成功
     * 1：失败
     */
    @Column(name = "status", columnDefinition = "int(2) COLLATE utf8_bin DEFAULT NULL COMMENT '登录状态 0：成功，1失败'")
    private Integer status;
    /**
     * 提示消息
     */
    @Column(name = "message", length = 200, columnDefinition = "varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '提示消息'")
    private String message;

    /**
     * 创建时间
     */
    @Column(name = "login_time", columnDefinition = "datetime NOT NULL COMMENT '访问时间'")
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;

    private static final long serialVersionUID = 1L;
}