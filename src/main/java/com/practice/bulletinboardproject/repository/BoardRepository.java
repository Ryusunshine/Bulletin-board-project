package com.practice.bulletinboardproject.repository;

import com.practice.bulletinboardproject.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    Page<Board> findByTitleContainingIgnoreCase(String searchKeyword, Pageable pageable);
}
