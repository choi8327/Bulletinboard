package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "MEMBER")
@Data
@Entity
public class Member {
    @Id
    @Column(name = "MEM_ID")
    private String memId;

    @Column(name = "MEM_NAME")
    private String memName;

    @Column(name = "MEM_PASSWd")
    private String memPasswd;

    @Column(name = "MEM_ENABLED")
    private String memEnabled;

    @Column(name = "REGIST_DATE")
    private String registDate; // 등록일

    @Column(name = "MODIFY_DATE")
    private String modifyDate; // 수정일
}
