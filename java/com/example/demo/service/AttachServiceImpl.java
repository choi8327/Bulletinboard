package com.example.demo.service;

import com.example.demo.entity.Attach;
import com.example.demo.repository.AttachRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachServiceImpl implements AttachService{
    private final AttachRepository attachRepository;

    public AttachServiceImpl(AttachRepository attachRepository) {
        this.attachRepository = attachRepository;
    }

    @Override
    public List<Attach> attachList(Attach attach) {
        return attachRepository.findByBoardSeq(attach.getBoardSeq());
    }
}
