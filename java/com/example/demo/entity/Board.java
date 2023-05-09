package com.example.demo.entity;

import lombok.Data;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;

@Table(name = "BOARD")
@Data
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOARD_SEQ")
    private int boardSeq;

    @Column(name = "BOARD_CODE")
    private String boardCode;  // 공지사항 or QNA등.. 게시판 분류

    @Column(name = "BOARD_SUBJECT")
    private String boardSubject; // 게시글 제목

    @Column(name = "BOARD_CONTENT")
    private String boardContent; // 게시글 내용

    @Column(name = "BOARD_WRITER")
    private String boardWriter;

    @Column(name = "BOARD_PASSWORD")
    private String boardPassword;

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "BOARD_CNT")
    private int boardCnt;

    @Column(name = "BOARD_PRIORITY")
    private int boardPriority;

    @Column(name = "BOARD_THUMB")
    private int boardThumb;  // 추천수

    @Column(name = "BOARD_P_SEQNO")
    private int boardPSeqno; // 상위 일련번호

    @Column(name = "BOARD_LEVEL")
    private int boardLevel;

    @Column(name = "REGIST_DATE")
    private String registDate; // 등록일

    @Column(name = "MODIFY_DATE")
    private String modifyDate; // 수정일
}
