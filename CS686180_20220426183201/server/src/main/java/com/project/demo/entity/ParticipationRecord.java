package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *参加记录：(ParticipationRecord)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ParticipationRecord")
public class ParticipationRecord implements Serializable {

    //ParticipationRecord编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participation_record_id")
    private Integer participation_record_id;
    // 标题名称
    @Basic
    private String title_name;
    // 劳动时间
    @Basic
    private Timestamp working_hours;
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
    // 参加状态
    @Basic
    private String participation_status;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
