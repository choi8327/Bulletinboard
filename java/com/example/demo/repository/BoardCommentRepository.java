package com.example.demo.repository;

import com.example.demo.entity.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardCommentRepository  extends JpaRepository<BoardComment, Integer> {
    List<BoardComment> findByBoardSeq(int boardSeq);

    int deleteByCommentSeqAndMemberId(int commentSeq, String memberId);
}
