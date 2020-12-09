package com.alice.boot.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Alice
 * Table: 用户角色关联表
 */
@Table(name = "`go_user_role`")
@org.hibernate.annotations.Table(appliesTo = "`go_user_role`", comment = "用户权限表。")
@Entity
@Data
public class UserRole implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id", columnDefinition = "bigint(20) COLLATE utf8_bin DEFAULT NULL COMMENT '用户id'")
    private Long userId;

    /**
     * 角色id
     */
    @Column(name = "role_id", columnDefinition = "bigint(20) COLLATE utf8_bin DEFAULT NULL COMMENT '角色id'")
    private Long roleId;

    private static final long serialVersionUID = 1L;
}