package com.globalin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.globalin.domain.Criteria;
import com.globalin.domain.ReplyVO;

public interface ReplyMapper {
	
	//댓글등록
	public int insert(ReplyVO vo);
	
	//댓글 하나 조회
	public ReplyVO read(int rno);
	
	//댓글 하나 삭제
	public int delete(int rno);
	
	//댓글 하나 수정
	public int update(ReplyVO vo);
	
	
	//페이지 처리된 댓글 리스트 가져오기
	//댓글 리스트를 가져올때는 필요한 정보가 잇다 : bno
	//1번 방법/ 마이 바티스는 두개이상의 파라미터를 전달하기위해서 두개의 파라미터를 별도의 객체로 구성
	//2번 방법/ Map을이용
	//3번 방법/ @Param 어노테이션을 사용해서 분리
	//@Param의 속성값을 mapper 에서 #{} 으로 가져다가 사용가능
	public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri,
			@Param("bno") int bno);
	//댓글의 페이지처리를 위해
	//해당 게시물에 달린 댓글이 총몇개인지 가져오는 쿼리
	public int getCountByBno(int bno);
}
