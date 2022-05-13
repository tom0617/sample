package com.globalin.ex02;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.globalin.domain.BoardVO;
import com.globalin.domain.Criteria;
import com.globalin.mapper.BoardMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTest {
	@Autowired
	private BoardMapper mapper;

//	@Test
//	public void insertTest() {
//		BoardVO board = new BoardVO();
//		board.setTitle("new txt");
//		board.setContent("new content");
//		board.setWriter("King");
		
		//mapper.insert(board);
//	}
//	@Test
//	public void testGetList() {
//		
//		List<BoardVO> list = mapper.getList();
//		for (BoardVO b : list) {
//			System.out.println(b);
//		}
//		
//	}
//	@Test 
//	public void testInsertSelectKey() {
//		BoardVO board = new BoardVO();
//		board.setBno(0);
//		board.setTitle("new select key");
//		board.setContent("new select key");
//		board.setWriter("newbie");
//		
//		System.out.println(mapper.read(6));
		
//		System.out.println(board);
	@Test
	public void testPaging() {
		
		//페이지 정보 생성
		Criteria cri = new Criteria();
		cri.setKeyword("새로");
		cri.setType("TC");
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		for(BoardVO board : list) {
			System.out.println(board);
		}
		
	}

}
