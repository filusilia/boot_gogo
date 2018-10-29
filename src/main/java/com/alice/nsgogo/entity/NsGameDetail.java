package com.alice.nsgogo.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * @author Aozaki on 2018/10/25.
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "`ns_game_detail`")
@Data
public class NsGameDetail implements Serializable {
    private static final long serialVersionUID = -2834977796237253443L;
    /**
     * 主键
     */
    @Column(name = "id", columnDefinition = " int(8) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '主键'")
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 游戏原名称
     */
    @NotEmpty
    @Column(name = "name", columnDefinition = " varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '游戏原名称'")
    private String name;

    /**
     * 发行商（出版商）
     */
    @Column(name = "publisher", columnDefinition = "varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '发行商（出版商）'")
    private String publisher;

    /**
     * 发行国家
     */
    @Column(name = "region", columnDefinition = "varchar(100) CHARACTER SET utf8 DEFAULT '' COMMENT '发行国家'")
    private String region;

    /**
     * 支持语言
     */
    @Column(name = "languages", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '支持语言'")
    private String languages;

    /**
     * 提取人
     */
    @Column(name = "group", columnDefinition = "varchar(100) CHARACTER SET utf8 DEFAULT '' COMMENT '提取人'")
    private String group;

    /**
     * 镜像大小（GB）
     */
    @Column(name = "image_size", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '镜像大小（GB）'")
    private String image_size;

    /**
     * 序列
     */
    @Column(name = "serial", columnDefinition = "varchar(50) CHARACTER SET utf8 DEFAULT NULL  COMMENT '序列'")
    private String serial;

    /**
     * 游戏番号
     */
    @Column(name = "title_id", columnDefinition = "varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '游戏番号'")
    private String title_id;

    /**
     * 镜像校验码
     */
    @Column(name = "imgae_crc", columnDefinition = "varchar(50) CHARACTER SET utf8 DEFAULT '' COMMENT '镜像校验码'")
    private String imgae_crc;

    /**
     * 原始文件名称
     */
    @Column(name = "file_name", columnDefinition = "varchar(100) CHARACTER SET utf8 DEFAULT '' COMMENT '原始文件名称'")
    private String file_name;

    /**
     * 发布名称
     */
    @NotEmpty
    @Column(name = "release_name", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '发布名称'")
    private String release_name;

    /**
     * 镜像真实大小
     */
    @Column(name = "trimme_dsize", columnDefinition = "int(20) unsigned zerofill DEFAULT NULL COMMENT '镜像真实大小'")
    private Integer trimme_dsize;

    /**
     * 固件支持版本
     */
    @Column(name = "firmware", columnDefinition = "varchar(0) CHARACTER SET utf8 DEFAULT '' COMMENT '固件支持版本'")
    private String firmware;

    /**
     * 发行类型（1：卡带:2：网络）
     */
    @Column(name = "release_type", columnDefinition = "int(4) DEFAULT NULL COMMENT '发行类型（1：卡带:2：网络）'")
    private Integer release_type;

    /**
     * 卡带数量
     */
    @Column(name = "card", columnDefinition = "int(4) unsigned zerofill DEFAULT 0001 COMMENT '卡带数量'")
    private Integer card;
}