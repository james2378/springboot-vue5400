package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *报名记录：(RegistrationRecord)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "RegistrationRecord")
public class RegistrationRecord implements Serializable {

    //RegistrationRecord编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_record_id")
    private Integer registration_record_id;
    // 活动名称
    @Basic
    private String activity_name;
    // 活动时间
    @Basic
    private Timestamp activity_time;
    // 学生信息
    @Basic
    private Integer student_information;
    // 学生姓名
    @Basic
    private String student_name;
    // 学生学号
    @Basic
    private String student_number;
    // 联系电话
    @Basic
    private String contact_number;
    // 报名状态
    @Basic
    private String registration_status;
    // 参加状态
    @Basic
    private String participation_status;
    // 报名回复
    @Basic
    private String registration_reply;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
