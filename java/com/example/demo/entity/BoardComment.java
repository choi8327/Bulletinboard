package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "BOARD_COMMENT")
@Data
@Entity
public class BoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMMENT_SEQ")
    private int commentSeq;

    @Column(name = "COMMENT_CONTENT")
    private String commentContent;

    @Column(name = "BOARD_SEQ")
    private int boardSeq;

    @Column(name = "COMMENT_PASSWORD")
    private String commentPassword;

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "COMMENT_WRITER")
    private String commentWriter;

    @Column(name = "REGIST_DATE")
    private String registDate; // 등록일

    @Column(name = "MODIFY_DATE")
    private String modifyDate; // 수정일
}
