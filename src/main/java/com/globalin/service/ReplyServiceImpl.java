package com.globalin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globalin.domain.Criteria;
import com.globalin.domain.ReplyPage;
import com.globalin.domain.ReplyVO;
import com.globalin.mapper.BoardMapper;
import com.globalin.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService{
	private Logger log = LoggerFactory.getLogger(ReplyServiceImpl.class);
	
	@Autowired
	private ReplyMapper mapper;
	@Autowired
	private BoardMapper bMapper;
	//tbl_reply에 댓글 insert + tbl_board 에 댓글수 update
	//이 메소드는 두개의 sql을 묶어서 트랜잭션 처리
	@Transactional
	@Override
	public int register(ReplyVO vo) {
		
		log.info("register...:" + vo);
		bMapper.updateReplyCnt(vo.getBno(), 1);
		
		
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(int rno) {
		log.info("get.." + rno);
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("modify.." + vo);
		return mapper.update(vo);
	}

	@Override
	public int remove(int rno) {
		log.info("remove.." + rno);
		
		ReplyVO vo = mapper.read(rno);
		
		bMapper.updateReplyCnt(vo.getBno()	, -1);
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, int bno) {
		log.info("getList.." + bno);
	
		return mapper.getListWithPaging(cri, bno);
	}

	@Override
	public ReplyPage getListPage(Criteria cri, int bno) {
		ReplyPage page = new ReplyPage();
		
		log.info("cri : " + cri);
		//이페이지 안에는 댓글 개수, 댓글리스트가 들어가야 한다
		page.setReplyCnt(mapper.getCountByBno(bno));
		page.setList(mapper.getListWithPaging(cri, bno));
		
		return page;
	}
}
