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
 * Table: 字典表
 */
@Table(name = "`go_dict`")
@org.hibernate.annotations.Table(appliesTo = "`go_dict`", comment = "字典表。")
@Entity
@Data
public class Dict implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 字典唯一代码
     */
    @Column(name = "code", length = 200, columnDefinition = "varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '字典唯一代码'")
    private String code;

    /**
     * 父字典id
     */
    @Column(name = "parent_id", columnDefinition = "bigint(20) DEFAULT NULL COMMENT '父字典id'")
    private Long parentId;

    /**
     * 字典名
     */
    @Column(name = "name", length = 200, columnDefinition = "varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '字典名'")
    private String name;

    /**
     * 内容
     */
    @Column(name = "val", length = 500, columnDefinition = "varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '内容'")
    private String val;

    /**
     * 内容类型
     */
    @Column(name = "type", length = 60, columnDefinition = "varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '内容类型'")
    private String type;

    /**
     * 拓展字段1
     */
    @Column(name = "ext", length = 300, columnDefinition = "varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '拓展字段'")
    private String ext;

    /**
     * 状态 ：1 启用 3 禁止修改 4 删除
     */
    @Column(name = "state", columnDefinition = "int(11) DEFAULT NULL COMMENT '状态 ：1 启用 3 禁止修改 4 删除'")
    private Integer state;

    /**
     * 备注
     */
    @Column(name = "notes", length = 500, columnDefinition = "varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '备注'")
    private String notes;

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