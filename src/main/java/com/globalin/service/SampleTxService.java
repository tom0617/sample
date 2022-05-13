package com.globalin.service;

public interface SampleTxService {
	
	//실제로 데이터를 추가해주는 메소드
	//데이터를 추가하려면 mapper를 통해서 
	//sample1mapper , sample2mapper가 필요합니다.
	
	public void addData(String value);
	

}
