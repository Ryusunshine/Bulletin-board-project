package com.practice.bulletinboardproject.controller.service;

import com.practice.bulletinboardproject.entity.Board;
import com.practice.bulletinboardproject.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public void write(Board board){
        boardRepository.save(board);
    }
}
