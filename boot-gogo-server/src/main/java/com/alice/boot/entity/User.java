package com.alice.boot.entity;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Alice
 * Table: 用户表
 */
@Table(name = "`go_user`")
@org.hibernate.annotations.Table(appliesTo = "`go_user`", comment = "用户表。")
@Entity
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "username", length = 100, columnDefinition = "varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名'")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password", length = 100, columnDefinition = "varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '密码'")
    private String password;

    /**
     * 昵称
     */
    @Column(name = "nick_name", length = 100, columnDefinition = "varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '昵称'")
    private String nickName;

    /**
     * email
     */
    @Column(name = "email", length = 200, columnDefinition = "varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱'")
    private String email;

    /**
     * 电话
     */
    @Column(name = "phone", length = 100, columnDefinition = "varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '电话'")
    private String phone;

    /**
     * 用户状态：0-正常，1-已删除
     */
    @Column(name = "status", columnDefinition = "int(2) COLLATE utf8_bin DEFAULT NULL COMMENT '用户状态：0-正常，1-已删除'")
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time", columnDefinition = "datetime NOT NULL COMMENT '创建时间'")
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time", columnDefinition = "datetime NOT NULL COMMENT '修改时间'")
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}