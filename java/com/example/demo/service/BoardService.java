package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.entity.BoardComment;
import com.example.demo.form.BoardForm;

import java.io.IOException;
import java.util.List;

public interface BoardService {

    List<Board> listBoard(Board board);

    Board oneBoard(Board board);

    int saveBoard(Board board);

    int saveBoardUpload(BoardForm boardForm) throws IOException;

    int removeBoard(Board board);

    Board modifyBoard(Board board);

    List<BoardComment> listBoardComment(BoardComment boardComment);

    int saveBoardComment(BoardComment boardComment);

    int deleteBoardComment(BoardComment boardComment);
}
