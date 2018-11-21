package com.alice.nsgogo.entity;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Aozaki on 2018/11/15.
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "`ns_game_download`")
@Data
public class NsGameDownload implements Serializable {

    private static final long serialVersionUID = 6343765851335142628L;
    /**
     * 主键
     */
    @Column(name = "id", columnDefinition = " int(8) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '主键'")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 对应游戏id
     */
    @Column(name = "game_detail_id", nullable = false, columnDefinition = "int(8) unsigned zerofill NOT NULL COMMENT '对应游戏id'")
    private Integer gameDetailId;

    /**
     * 游戏下载地址
     */
    @Column(name = "download_url", length = 255,columnDefinition = "varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '游戏原名称'")
    private String downloadUrl;
    /**
     * 游戏下载密码
     */
    @Column(name = "download_url_password", length = 255,columnDefinition = "varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '游戏地址下载密码'")
    private String downloadUrlPassword;
    /**
     * 游戏下载类型
     */
    @Column(name = "download_from", length = 255,columnDefinition = "varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '游戏下载类型'")
    private String downloadFrom;
    /**
     * 游戏下载类型
     */
    @Column(name = "download_creator", length = 255,columnDefinition = "varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '下载地址创建者'")
    private String downloadCreator;
    /**
     * 备注
     */
    @Column(name = "remark", length = 255,columnDefinition = "varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '备注'")
    private String remark;

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
