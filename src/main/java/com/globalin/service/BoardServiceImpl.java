package com.globalin.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globalin.domain.BoardVO;
import com.globalin.domain.Criteria;
import com.globalin.mapper.BoardMapper;
//@Component 어노테이션과 같은 종류의 어노테이셔
	//service 역할을 하는 빈 객체 
@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardMapper mapper;
	
	private static Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Override
	public void register(BoardVO board) {
		log.info("게시물등록 : " + board);
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(int bno) {
		log.info("게시글 조회");
		
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		
		log.info("게시글 수정");
//		int updateResult = mapper.update(board);
//
//		boolean result = false;// 리턴해줄값
//		if (updateResult > 0) {
//			// 회원정보 수정성공
//			// updateMember() 메소드의 실행결과는 영향을 받은 행의 개수
//			// 수정된 회원정보의 개수가 0개보다 크다.
//			result = true;
//
//		}
		return mapper.update(board) == 1; //위랑 같은식임 1은 true 0은 false 삭제가되면 true

	}
	

	@Override
	public boolean remove(int bno) {
		log.info("게시글 삭제");
		int updateResult = mapper.delete(bno);

		boolean result = false;// 리턴해줄값
		if (updateResult > 0) {
			// 회원정보 수정성공
			// updateMember() 메소드의 실행결과는 영향을 받은 행의 개수
			// 수정된 회원정보의 개수가 0개보다 크다.
			result = true;

		}
		return result;

	}

	@Override
	public List<BoardVO> getList() {
		log.info("게시글 전체 조회");
		return mapper.getList();
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("게시글 페이지 조회 : " + cri);
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		log.info("전체 게시글 개수 조회");
		return mapper.getTotalCount(cri);
	}

}
