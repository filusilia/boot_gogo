package com.alice.boot.entity;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Alice
 * Table: 菜单表
 */
@Table(name = "`go_menu`")
@org.hibernate.annotations.Table(appliesTo = "`go_menu`", comment = "菜单表。")
@Entity
@Data
public class Menu implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name", length = 200, columnDefinition = "varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单名称'")
    private String menuName;

    /**
     * 菜单url地址
     */
    @Column(name = "menu_url", length = 300, columnDefinition = "varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单地址'")
    private String menuUrl;

    /**
     * 父id
     */
    @Column(name = "parent_id", columnDefinition = "bigint(10) DEFAULT NULL COMMENT '父菜单id'")
    private Long parentId;

    /**
     * 排序
     */
    @Column(name = "sequence", columnDefinition = "int(5) DEFAULT NULL COMMENT '排序'")
    private Integer sequence;

    /**
     * 菜单图标
     */
    @Column(name = "menu_icon", length = 300, columnDefinition = "varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单图标'")
    private String menuIcon;

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