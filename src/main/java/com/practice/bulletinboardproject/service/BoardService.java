package com.practice.bulletinboardproject.service;

import com.practice.bulletinboardproject.entity.Board;
import com.practice.bulletinboardproject.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // 글작성
    public void write(Board board, MultipartFile file) throws IOException {
        String projectPath= System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";//저장경로 지정

        UUID uuid = UUID.randomUUID(); //파일이름을 랜덤으로 생성할 식별자 생성

        String fileName = uuid + "_" + file.getOriginalFilename(); //랜덤파일이름_원래파일이름

        File saveFile = new File(projectPath, fileName); //새로운 파일을 projectPath경로에다가 "name"이라는 이름으로 만들어준다.
        //saveFile이라는 빈껍데기 파일생성

        file.transferTo(saveFile);

        board.setFilename(fileName);
        board.setFilepath("/files/"+fileName);

        boardRepository.save(board);
    }

    // 모든 게시글
    public List<Board> boardList(){
        return boardRepository.findAll();
    }

    // 특정 게시글 불러오기
    public Board boardView(Integer id){
        return boardRepository.findById(id).get();
    }

    //특정 게시글 삭제
    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }


}
