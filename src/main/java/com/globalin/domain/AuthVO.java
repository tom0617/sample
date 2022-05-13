package com.globalin.domain;

public class AuthVO {
	
	private String userid,auth;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	@Override
	public String toString() {
		return "AuthVO [userid=" + userid + ", auth=" + auth + "]";
	}
	

}
