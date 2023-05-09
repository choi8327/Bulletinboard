package com.example.demo.repository;

import com.example.demo.entity.Attach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachRepository  extends JpaRepository<Attach, Integer> {
    List<Attach> findByBoardSeq(int boardSeq);
}
