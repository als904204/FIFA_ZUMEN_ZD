package com.toy.fifa.Service.Community;

import com.toy.fifa.Controller.ExceptionConfig.DataNotFoundException;
import com.toy.fifa.Entity.Board;
import com.toy.fifa.Entity.User;
import com.toy.fifa.Repository.BoardRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    // 모든 게시글 조회
    public List<Board> getBoardList() {
        List<Board> boardList = boardRepository.findAll();
        return boardList;
    }

    // 게시글 자세히 보기
    public Board getBoardDetail(Long id) {
       Board board =  boardRepository.findById(id).orElseThrow(()-> {
           return new DataNotFoundException("해당 게시글을 찾을 수 없습니다 : " + id);
       });
       return board;
    }

    // 게시글 작성
    // TODO : DTO 변경
    public void createBoard(String title, String content) {
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setCreateDate(LocalDateTime.now());
        boardRepository.save(board);
    }

}
