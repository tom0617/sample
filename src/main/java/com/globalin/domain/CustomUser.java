package com.globalin.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

/*
 * 스프링 시큐리티가 사용자 정보를 UserDetails 타입으로 밖에 사용하지 않기때문에
 * 우리가 사용하는 클래스 타입을 스프링 시큐리티가 사용 가능한 타입으로 변경해주는 작업이
 * 필요함	
 * 
 */
public class CustomUser extends User {

	private MemberVO member;
	
	public MemberVO getMember() {
		return member;
	}

	public void setMember(MemberVO member) {
		this.member = member;
	}

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	//우리의 MemberVO를 스프링 시큐리티가 사용할수 있도록 바꿔주는 기능
	public CustomUser(MemberVO vo) {
		super(vo.getUserid(), vo.getUserpw(), 
				vo.getAuthList()
				.stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
				.collect(Collectors.toList()));
	//java 1.8버전에 추가된 stream api + 화살표 함수 기능
		//function(auth){}
		//(auth -> ...함수몸체...)
	//AuthVO 객체를 GrantedAuthority 객체로 변환
	this.member = vo;
	}

}
