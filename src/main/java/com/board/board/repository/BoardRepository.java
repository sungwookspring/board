package com.board.board.repository;

import com.board.board.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select b from Board b join fetch b.posts p where b.id = :id")
    Board findOneWithPost(@Param("id") Long id);
}
