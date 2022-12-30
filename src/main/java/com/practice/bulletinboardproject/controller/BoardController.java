package com.practice.bulletinboardproject.controller;

import com.practice.bulletinboardproject.service.BoardService;
import com.practice.bulletinboardproject.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/board/write")
    public String boardWriteForm(){
        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model, MultipartFile file) throws IOException { //model에 담겨서 html로 넘어감
        boardService.write(board, file);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchURL", "/board/list");
        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model, @PageableDefault(page=0, size=10, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Board> list = boardService.boardList(pageable);
        int nowPage = list.getPageable().getPageNumber() + 1; // pageable의 page는 0부터 시작
        int startPage = Math.max(nowPage -4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list",list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "boardlist";
    }

    @GetMapping("/board/view")
    public String boardView(Model model, Integer id){ // http://localhost:8080/board/view?id=1
        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }

//    @GetMapping("/board/delete")
//        public String boardDelete(Integer id){
//            boardService.boardDelete(id);
//            return "redirect:/board/list";
//    }

    @GetMapping("/board/modify/{id}") // 수정페이지
    public String boardModify(@PathVariable("id") Integer id, Model model){
        model.addAttribute("board", boardService.boardView(id));
        //상세페이지에 있는 내용이랑 수정할때 넘어가는 내용이 같기때문에

        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, Model model, MultipartFile file) throws Exception{
        Board tempBoard = boardService.boardView(id); //기존의 글 검색
        tempBoard.setTitle(board.getTitle());
        tempBoard.setContent(board.getContent());
        boardService.write(tempBoard, file);
        model.addAttribute("message", "글 수정이 완료되었습니다.");
        model.addAttribute("searchURL", "/board/list");
        return "message";
    }

}
