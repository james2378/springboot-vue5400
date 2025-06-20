package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *志愿者活动：(VolunteerActivities)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "VolunteerActivities")
public class VolunteerActivities implements Serializable {

    //VolunteerActivities编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "volunteer_activities_id")
    private Integer volunteer_activities_id;
    // 活动名称
    @Basic
    private String activity_name;
    // 活动时间
    @Basic
    private Timestamp activity_time;
    // 活动人数
    @Basic
    private String number_of_activities;
    // 封面图片
    @Basic
    private String cover_photo_;
    // 活动地址
    @Basic
    private String activity_address;
    // 活动内容
    @Basic
    private String activity_content;
    // 点击数
    @Basic
    private Integer hits;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
