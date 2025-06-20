package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *加团记录：(GroupRecord)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "GroupRecord")
public class GroupRecord implements Serializable {

    //GroupRecord编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_record_id")
    private Integer group_record_id;
    // 社团名称
    @Basic
    private String community_name;
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
    // 审核状态
    @Basic
    private String audit_status;
    // 加团理由
    @Basic
    private String reasons_for_adding_group;
    // 审核回复
    @Basic
    private String review_reply;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
