package com.alice.nsgogo.entity;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    @Column(name = "name", length = 255, columnDefinition = " CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '游戏原名称'")
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
    @Column(name = "dump_group", columnDefinition = "varchar(100) CHARACTER SET utf8 DEFAULT '' COMMENT '提取人'")
    private String dumpGroup;

    /**
     * 镜像大小（GB）
     */
    @Column(name = "image_size", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '镜像大小（GB）'")
    private String imageSize;

    /**
     * 序列
     */
    @Column(name = "serial", columnDefinition = "varchar(50) CHARACTER SET utf8 DEFAULT NULL  COMMENT '序列'")
    private String serial;

    /**
     * 游戏番号
     */
    @Column(name = "title_id", columnDefinition = "varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '游戏番号'")
    private String titleTd;

    /**
     * 镜像校验码
     */
    @Column(name = "imgae_crc", columnDefinition = "varchar(50) CHARACTER SET utf8 DEFAULT '' COMMENT '镜像校验码'")
    private String imgaeCrc;

    /**
     * 原始文件名称
     */
    @Column(name = "file_name", columnDefinition = "varchar(100) CHARACTER SET utf8 DEFAULT '' COMMENT '原始文件名称'")
    private String fileName;

    /**
     * 发布名称
     */
    @Column(name = "release_name", columnDefinition = "varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '发布名称'")
    private String releaseName;

    /**
     * 镜像真实大小
     */
    @Column(name = "trimmed_size", columnDefinition = "int(20) unsigned zerofill DEFAULT NULL COMMENT '镜像真实大小'")
    private Integer trimmedSize;

    /**
     * 固件支持版本
     */
    @Column(name = "firmware", columnDefinition = "varchar(0) CHARACTER SET utf8 DEFAULT '' COMMENT '固件支持版本'")
    private String firmware;

    /**
     * 发行类型（1：卡带:2：网络）
     */
    @Column(name = "release_type", columnDefinition = "int(4) DEFAULT NULL COMMENT '发行类型（1：卡带:2：网络）'")
    private Integer releaseType;

    /**
     * 卡带数量
     */
    @Column(name = "card", columnDefinition = "int(4) unsigned zerofill DEFAULT 0001 COMMENT '卡带数量'")
    private Integer card;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /**
     * 更新时间
     */
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
}