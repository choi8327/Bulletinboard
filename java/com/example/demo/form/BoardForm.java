package com.example.demo.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BoardForm {
    private String boardCode;
    private String boardSubject; // 게시글 제목
    private String boardContent; // 게시글 내용
    private String boardWriter;
    private String boardPassword;
    private MultipartFile upfile;
    private List<MultipartFile> files;
}
