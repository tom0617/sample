package com.globalin.domain;

import java.util.List;

public class ReplyPage {
	//댓글 개수
	private int replyCnt;
	//댓글 모음
	private List<ReplyVO> list;
	public int getReplyCnt() {
		return replyCnt;
	}
	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}
	public List<ReplyVO> getList() {
		return list;
	}
	public void setList(List<ReplyVO> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "ReplyPage [replyCnt=" + replyCnt + ", list=" + list + "]";
	}

}
