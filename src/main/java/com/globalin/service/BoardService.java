package com.globalin.service;

import java.util.List;

import com.globalin.domain.BoardVO;
import com.globalin.domain.Criteria;

public interface BoardService {
	// 게시물 작성
	public void register(BoardVO board);

	// 게시물 하나 조회
	public BoardVO get(int bno);

	// 게시물 수정
	public boolean modify(BoardVO board);

	
	
	// 게시물 삭제
	public boolean remove(int bno);

	// 게시글 전체 조회
	public List<BoardVO> getList();
	
	//게시글 페이지 처리 이용한 조회
	public List<BoardVO> getList(Criteria cri);
	
	//
	public int getTotal(Criteria cri);
}
