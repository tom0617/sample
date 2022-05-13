package com.globalin.ex02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.globalin.domain.BoardVO;
import com.globalin.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardServiceTest {
	@Autowired
	private BoardService service;
	
	@Test
	public void testExit() {
		assertNotNull(service);
	}
	
	@Test
	public void testRegist() {
		BoardVO board = new BoardVO();
		board.setTitle("asd");
		board.setContent("sd Content");
		board.setWriter("super sexy kim");
		
//		service.register(board);
		System.out.println("생성된 게시물의 번호 : "+ board.getBno());
	}
	@Test
	public void testGetList() {
		for(BoardVO board : service.getList()) {
			//System.out.println(board);
		}
	}
	@Test
	public void testModify() {
		BoardVO board = new BoardVO();
		board.setBno(14);
		board.setTitle("asd");
		board.setContent("sd Content");
		board.setWriter("super sexy kim");
		if(board ==null)
			return;
		
		service.modify(board);
		
	}
	@Test
	public void testremove() {
		
	}
	@Test
	public void testRead() {
		System.out.println(service.get(2));
	}
	
	
	
	
}
