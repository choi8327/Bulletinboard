package com.example.demo.service;

import com.example.demo.entity.Attach;
import com.example.demo.entity.Board;
import com.example.demo.entity.BoardComment;
import com.example.demo.form.BoardForm;
import com.example.demo.repository.AttachRepository;
import com.example.demo.repository.BoardCommentRepository;
import com.example.demo.repository.BoardRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BoardSerivceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final AttachRepository attachRepository;
    private final BoardCommentRepository boardCommentRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public BoardSerivceImpl(BoardRepository boardRepository, AttachRepository attachRepository, BoardCommentRepository boardCommentRepository) {
        this.boardRepository = boardRepository;
        this.attachRepository = attachRepository;
        this.boardCommentRepository = boardCommentRepository;
    }

    @Override
    public List<Board> listBoard(Board board) {
        //List<Board> boardList = boardRepository.findAll();
        List<Board> boardList = null;

        if (StringUtils.isNotBlank(board.getBoardSubject()) && StringUtils.isNotBlank(board.getBoardContent())) {
            boardList = boardRepository.findByBoardSubjectContainsAndBoardContentContainsOrderByRegistDateDesc(board.getBoardSubject(), board.getBoardContent());
        } else if (StringUtils.isNotBlank(board.getBoardSubject())) {
            boardList = boardRepository.findByBoardSubjectContainsOrderByRegistDateDesc(board.getBoardSubject());
        } else if (StringUtils.isNotBlank(board.getBoardContent())) {
            boardList = boardRepository.findByBoardContentContainsOrderByRegistDateDesc(board.getBoardSubject());
        } else {
           boardList = boardRepository.findByOrderByBoardPSeqnoDescBoardLevelAsc();
        }

        return boardList;
    }

    @Override
    public Board oneBoard(Board board) {
        Board dto = new Board();
        Optional<Board> optional = boardRepository.findById(board.getBoardSeq());
        if (optional.isPresent()) {
            dto = optional.get();
            dto.setBoardCnt(dto.getBoardCnt() + 1);
            boardRepository.save(dto);
        }
        return dto;
    }

    @Override
    public int saveBoard(Board board) {
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        board.setRegistDate(currentTime);
        boardRepository.save(board);
        board.setBoardPSeqno(board.getBoardSeq());
        boardRepository.save(board);
        // String res = board.getBoardSeq() > 0 ? "SUCCESS" : "FAIL";
        return 1;
    }

    @Override
    public int saveBoardUpload(BoardForm boardForm) {
        // 1. 게시판 저장
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        Board board = new Board();
        board.setBoardSubject(boardForm.getBoardSubject());
        board.setBoardContent(boardForm.getBoardContent());
        board.setBoardPassword(boardForm.getBoardPassword());
        board.setBoardCode(boardForm.getBoardCode());
        board.setRegistDate(currentTime);
        boardRepository.save(board);
        board.setBoardPSeqno(board.getBoardSeq());
        boardRepository.save(board);

        if (boardForm.getUpfile() != null) {
            // 2. 파일이 저장
            MultipartFile multiFile = boardForm.getUpfile();

            String originalFile = multiFile.getOriginalFilename();
            long size = multiFile.getSize();

            String fileExt = "";
            if (originalFile.lastIndexOf('.') > 0) {
                fileExt = originalFile.substring(originalFile.lastIndexOf('.'));
            }
            String uuid = UUID.randomUUID().toString()+fileExt;
            String fileName = uploadPath + File.separator + uuid;

            Path savePath = Paths.get(fileName);

            try {
                multiFile.transferTo(savePath);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 3. attach table insert : board_seq
            Attach attach = new Attach();
            attach.setAttachFileName(uuid);
            attach.setAttachOriginFileName(originalFile);
            attach.setAttachPath(fileName);
            attach.setAttachSize(size);
            attach.setBoardSeq(board.getBoardSeq());
            attach.setAttachExt(fileExt);
            attach.setRegistDate(currentTime);

            attachRepository.save(attach);
        }
        return 0;
    }

    @Override
    public int removeBoard(Board board) {
        Optional<Board> optional = boardRepository.findByBoardSeqAndBoardPassword(board.getBoardSeq(), board.getBoardPassword());
        if (optional.isPresent()) {
            boardRepository.deleteById(board.getBoardSeq());
        } else {
            return 0;
        }
        return optional.get().getBoardSeq();
    }

    @Override
    public Board modifyBoard(Board board) {
        Board dto = new Board();
        Optional<Board> optional = boardRepository.findById(board.getBoardSeq());
        if (optional.isPresent()) {
            dto = optional.get();
            if (StringUtils.isNotBlank(board.getBoardSubject())) {
                dto.setBoardSubject(board.getBoardSubject());
            }
            if (StringUtils.isNotBlank(board.getBoardContent())) {
                dto.setBoardContent(board.getBoardContent());
            }
            boardRepository.save(dto);
        }
        return dto;
    }

    @Override
    public List<BoardComment> listBoardComment(BoardComment boardComment) {
        List<BoardComment> boardCommentList =
                boardCommentRepository.findByBoardSeq(boardComment.getBoardSeq());
        return  boardCommentList;
    }

    @Override
    public int saveBoardComment(BoardComment boardComment) {
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        boardComment.setRegistDate(currentTime);
        boardCommentRepository.save(boardComment);

        // String res = board.getBoardSeq() > 0 ? "SUCCESS" : "FAIL";
        return boardComment.getCommentSeq();
    }

    @Override
    @Transactional
    public int deleteBoardComment(BoardComment boardComment) {
        // boardCommentRepository.deleteById(boardComment.getCommentSeq());
        boardCommentRepository.deleteByCommentSeqAndMemberId(boardComment.getCommentSeq(), boardComment.getMemberId());
        return 1;
    }
}
