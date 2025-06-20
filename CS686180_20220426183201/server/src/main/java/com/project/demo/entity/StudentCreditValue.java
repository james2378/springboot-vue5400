package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *学生信誉值：(StudentCreditValue)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "StudentCreditValue")
public class StudentCreditValue implements Serializable {

    //StudentCreditValue编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_credit_value_id")
    private Integer student_credit_value_id;
    // 学生信息
    @Basic
    private Integer student_information;
    // 学生姓名
    @Basic
    private String student_name;
    // 学生学号
    @Basic
    private String student_number;
    // 信誉积分
    @Basic
    private String credit_score;
    // 积分详情
    @Basic
    private String points_details;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
