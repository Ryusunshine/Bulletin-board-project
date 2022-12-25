package com.practice.bulletinboardproject.repository;

import com.practice.bulletinboardproject.controller.service.BoardService;
import com.practice.bulletinboardproject.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {


}
