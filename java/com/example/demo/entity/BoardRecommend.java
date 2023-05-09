package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "BOARD_RECOMMEND")
@Data
@Entity
public class BoardRecommend {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RECOMMEND_SEQ")
    private int recommendSeq;

    @Column(name = "BOARD_SEQ")
    private int boardSeq;

    @Column(name = "COMMENT_SEQ")
    private int commentSeq;

    @Column(name = "RECOMMEND_IP")
    private String recommendIp;

}
