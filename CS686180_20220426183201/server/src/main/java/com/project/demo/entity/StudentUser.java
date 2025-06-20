package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *学生用户：(StudentUser)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "StudentUser")
public class StudentUser implements Serializable {

    //StudentUser编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_user_id")
    private Integer student_user_id;
    // 学生姓名
    @Basic
    private String student_name;
    // 学生性别
    @Basic
    private String student_gender;
    // 学生学号
    @Basic
    private String student_number;
    // 用户编号
    @Id
    @Column(name = "user_id")
    private Integer userId;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
