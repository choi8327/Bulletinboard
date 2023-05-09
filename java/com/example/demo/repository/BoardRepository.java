package com.example.demo.repository;

import com.example.demo.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    List<Board> findByOrderByRegistDateDesc();

    List<Board> findByBoardSubjectContainsOrderByRegistDateDesc(String boardSubject);

    List<Board> findByBoardContentContainsOrderByRegistDateDesc(String boardSubject);

    List<Board> findByBoardSubjectContainsAndBoardContentContainsOrderByRegistDateDesc(String boardSubject, String boardContent);

    Optional<Board> findByBoardSeqAndBoardPassword(int boardSeq, String boardPassword);

    List<Board> findByOrderByBoardPSeqnoDescBoardLevelAsc();
}
