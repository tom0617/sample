package com.globalin.ex02;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.globalin.domain.Criteria;
import com.globalin.domain.ReplyVO;
import com.globalin.mapper.ReplyMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyMapperTest {
	
	//테스트전에 확인했던 실제 존재하는 게시물번호(bno)
	private int[] bnoArr = {8197, 8196, 8195, 8194, 8193};
	
	//테스트할 매퍼
	@Autowired
	private ReplyMapper mapper;
//	
//	@Test
//	public void testCreate() {
//		
//		for(int i = 0; i < 5; i++) {
//			ReplyVO vo = new ReplyVO();
//			
//			vo.setBno(bnoArr[i]);
//			vo.setReply("댓글 테스트" + i);
//			vo.setReplyer("댓글 단사람" + i);
//			
//			mapper.insert(vo);
//		}
//	}
//	@Test
//	public void testRead() {
//		
//		System.out.println(mapper.read(1));
//		
//		
//	}
//	@Test
//	public void testdelete() {
//		mapper.delete(1);
//		for(int i = 0; i <10 ;i++)
//		{
//			System.out.println(mapper.read(i));
//		}
//	}
//	@Test
//	public void testupdate() {
//		ReplyVO vo = new ReplyVO();
//		vo.setRno(2);
//		vo.setReply("야스 테스트");
//		
//		mapper.update(vo);
//		System.out.println(mapper.read(2));
//	}
	@Test
	public void testList() {
		
		Criteria cri = new Criteria();
		
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[2]);
		
		System.out.println(replies.toString());
	}

}
