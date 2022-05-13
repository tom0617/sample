package com.globalin.service;

import java.util.List;

import com.globalin.domain.Criteria;
import com.globalin.domain.ReplyPage;
import com.globalin.domain.ReplyVO;

public interface ReplyService {
	
	public int register(ReplyVO vo);
	
	public ReplyVO get(int rno);
	
	public int modify(ReplyVO vo);
	
	public int remove(int rno);
	//기존에 리스트만 가져다 주던 메소드
	public List<ReplyVO> getList(Criteria cri, int bno);
	//리스트 + 총댓글개수(해당게시물만)
	public ReplyPage getListPage(Criteria cri, int bno);
}
