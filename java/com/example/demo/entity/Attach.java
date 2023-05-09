package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "ATTACH")
@Data
@Entity
public class Attach {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ATTACH_SEQ")
    private int attachSeq;

    @Column(name = "BOARD_SEQ")
    private int boardSeq;

    @Column(name = "ATTACH_FILE_NAME")
    private String attachFileName;

    @Column(name = "ATTACH_ORIGIN_FILE_NAME")
    private String attachOriginFileName; // 디스크저장파일

    @Column(name = "ATTACH_SIZE")
    private long attachSize;  // 파일사이즈

    @Column(name = "ATTACH_PATH")
    private String attachPath;  // 파일경로

    @Column(name = "ATTACH_EXT")
    private String attachExt;  // 확장자

    @Column(name = "REGIST_DATE")
    private String registDate; // 등록일
}
