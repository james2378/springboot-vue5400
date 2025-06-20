package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *学校劳动：(SchoolLabor)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "SchoolLabor")
public class SchoolLabor implements Serializable {

    //SchoolLabor编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_labor_id")
    private Integer school_labor_id;
    // 标题名称
    @Basic
    private String title_name;
    // 劳动时间
    @Basic
    private Timestamp working_hours;
    // 封面图片
    @Basic
    private String cover_photo_;
    // 劳动地址
    @Basic
    private String labor_address;
    // 劳动内容
    @Basic
    private String labor_content;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
