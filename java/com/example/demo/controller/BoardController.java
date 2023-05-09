package com.example.demo.controller;

import com.example.demo.entity.Attach;
import com.example.demo.entity.Board;
import com.example.demo.entity.BoardComment;
import com.example.demo.form.BoardForm;
import com.example.demo.service.AttachService;
import com.example.demo.service.BoardService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class BoardController {
    private final BoardService boardService;
    private final AttachService attachService;

    public BoardController(BoardService boardService, AttachService attachService) {
        this.boardService = boardService;
        this.attachService = attachService;
    }

    @GetMapping({"/board/list"})
    public String boardList(HttpServletRequest request, Board board, Model model) throws UnsupportedEncodingException {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("sessName");

        System.out.println("================ sessId2 : " + session.getAttribute("sessId"));
        System.out.println("================ sessName : " + session.getAttribute("sessName"));
        List<Board> boardList = boardService.listBoard(board);
        model.addAttribute("list", boardList);
        return "board/list";
    }
    // 생성
    @GetMapping({"/board/create"})
    public String boardCreate() {
        return "board/create";
    }

    // 저장
    @PostMapping({"/board/save"})
    @ResponseBody
    public String boardSave(Board board) {
        boardService.saveBoard(board);
        return "SUCCESS";
    }

    // 저장 + file upload
    @PostMapping({"/board/saveUpload"})
    public String boardSaveUpload(BoardForm boardForm) throws IOException {
        boardService.saveBoardUpload(boardForm);
        return "redirect:/board/list";
    }

    // 삭제
    @PostMapping({"/board/remove"})
    @ResponseBody
    public int boardRemove(Board board) {
        int seq = boardService.removeBoard(board);
        return seq;
    }

    // 삭제
    @GetMapping({"/board/modify"})
    public String boardRemove(Board board, Model model) {
        Board dto = boardService.oneBoard(board);
        model.addAttribute("data", dto);
        return "board/modify";
    }

    // 수정
    @PostMapping({"/board/update"})
    @ResponseBody
    public Board boardUpdate(Board board) {
        Board boardData = boardService.modifyBoard(board);
        return boardData;
    }

    // 상세보기
    @GetMapping({"/board/detail"})
    public String boardDetail(Board board, Attach attach, Model model) {
        Board dto = boardService.oneBoard(board);
        List<Attach> dto2 = attachService.attachList(attach);
        if (dto2 != null && dto2.size() > 0) {
            model.addAttribute("attach", dto2.get(0));
        }
        model.addAttribute("data", dto);

        return "board/detail";
    }

    @PostMapping({"/board/comment"})
    public String boardComment(HttpServletRequest request, BoardComment boardComment, Model model) {

        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("sessName");

        System.out.println("================ sessId2 : " + session.getAttribute("sessId"));
        System.out.println("================ sessName : " + session.getAttribute("sessName"));
        List<BoardComment> boardCommentList = boardService.listBoardComment(boardComment);
        model.addAttribute("list", boardCommentList);

        return "board/detail_comment";
    }

    // 저장
    @PostMapping({"/board/comment/save"})
    @ResponseBody
    public int boardCommentSave(HttpServletRequest request, BoardComment boardComment) {
        HttpSession session = request.getSession();

        boardComment.setCommentWriter((String) session.getAttribute("sessName"));
        boardComment.setMemberId((String) session.getAttribute("sessId"));
        int result = boardService.saveBoardComment(boardComment);
        return result;
    }

    @PostMapping({"/board/comment/delete"})
    @ResponseBody
    public int boardCommentDelete(HttpServletRequest request, BoardComment boardComment) {
        HttpSession session = request.getSession();

        boardComment.setCommentWriter((String) session.getAttribute("sessName"));
        boardComment.setMemberId((String) session.getAttribute("sessId"));
        int result = boardService.deleteBoardComment(boardComment);
        return result;
    }

    ////// 이하 테스트
    @GetMapping({"/hello200", "/hello500"})
    public String hello() {
        return "hello2";
    }

    @GetMapping({"/hello700"})
    public String hello700() {
        return "test/hello2";
    }

    @GetMapping("/hello3")
    @ResponseBody
    public String[] hello3() {
        return new String[] {"Hello", "World"};
    }

    public BoardService getBoardService() {
        return this.boardService;
    }

    public AttachService getAttachService() {
        return this.attachService;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof BoardController)) return false;
        final BoardController other = (BoardController) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$boardService = this.getBoardService();
        final Object other$boardService = other.getBoardService();
        if (this$boardService == null ? other$boardService != null : !this$boardService.equals(other$boardService))
            return false;
        final Object this$attachService = this.getAttachService();
        final Object other$attachService = other.getAttachService();
        if (this$attachService == null ? other$attachService != null : !this$attachService.equals(other$attachService))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BoardController;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $boardService = this.getBoardService();
        result = result * PRIME + ($boardService == null ? 43 : $boardService.hashCode());
        final Object $attachService = this.getAttachService();
        result = result * PRIME + ($attachService == null ? 43 : $attachService.hashCode());
        return result;
    }

    public String toString() {
        return "BoardController(boardService=" + this.getBoardService() + ", attachService=" + this.getAttachService() + ")";
    }
}
