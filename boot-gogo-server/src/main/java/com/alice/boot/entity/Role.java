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
 * Table: 角色表
 */
@Table(name = "`go_role`")
@org.hibernate.annotations.Table(appliesTo = "`go_role`", comment = "权限表。")
@Entity
@Data
public class Role implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色编码
     */
    @Column(name = "role_code", columnDefinition = "bigint(16) COLLATE utf8_bin DEFAULT NULL COMMENT '角色编码'")
    private String roleCode;

    /**
     * 角色名
     */
    @Column(name = "role_name", length = 100, columnDefinition = "varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名'")
    private String roleName;

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