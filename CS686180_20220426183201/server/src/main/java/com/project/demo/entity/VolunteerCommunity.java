package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *志愿者社团：(VolunteerCommunity)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "VolunteerCommunity")
public class VolunteerCommunity implements Serializable {

    //VolunteerCommunity编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "volunteer_community_id")
    private Integer volunteer_community_id;
    // 社团名称
    @Basic
    private String community_name;
    // 建团时间
    @Basic
    private Timestamp group_establishment_time;
    // 社团人数
    @Basic
    private String number_of_associations;
    // 封面图片
    @Basic
    private String cover_photo_;
    // 社团简介
    @Basic
    private String community_profile;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
