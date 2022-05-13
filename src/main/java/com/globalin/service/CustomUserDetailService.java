package com.globalin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.globalin.domain.CustomUser;
import com.globalin.domain.MemberVO;
import com.globalin.mapper.MemberMapper;

//이 클래스는 스프링 시큐리티의 유저 정보를 (인증,권한) 데이터베이스에서 가져와주는 역할
public class CustomUserDetailService implements UserDetailsService {
	private Logger log = LoggerFactory.getLogger(CustomUserDetailService.class);
	
	@Autowired
	private MemberMapper mapper;
	/*
	 * UserDetailsService 는 loadUserByUserName() 이라는 하나의 추상 메소드
	 * 리턴타입이 org.springframework.core.userdetails.UserDetails
	 * 스프링에서 취급하는 사용자 정보 객체는 오직 UserDetails타입
	 * 우리가 만든 사용자 정보객체는 MeberVO타입이다
	 * 그래서 우리가 만든 사용자 정보객체를 스프링시큐리티가 사용할수 있도록
	 * MemberVO타입을 UserDetails 타입으로 변환하는 작업이 필요
	 * 1) MemberVO 클래스를 UserDetails 인터페이스를 구현하도록 하는 방법
	 * 2) 제3의 클래스를 이용해서 MemberVO클래스를 수정하지 않고 하는 방법
	 */
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.warn("loard user by username : " + username);
		
		//username으로 Member 객체 가져오기
		MemberVO vo = mapper.read(username);
		log.warn("queride by mapper : " + vo);
		//매퍼가 vo를 가져왓으면 MemberVO를 스프링 시큐리티가 사용하는 타입으로 변경
		//vo를 가져오지 못했으면 그대로 null return
		
		return vo==null? null:new CustomUser(vo);
	}

}
