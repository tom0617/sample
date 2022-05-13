package com.globalin.mapper;

import com.globalin.domain.MemberVO;
/*	
 * MemberVO 객체는 내부적으로 여러개의 AuthVO 객체를 가질수있게 설계
 * 하나의 데이터가 여러개의 하위 데이터를 포함하고 있다.
 * 1:N관계
 * 이런관계를 Mybatis에서 처리할때 ResultMap을 사용
 */
public interface MemberMapper {

	public MemberVO read(String userid);
	
	
	
}
