package com.globalin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globalin.mapper.Sample1Mapper;
import com.globalin.mapper.Sample2Mapper;

@Service //autowired쓰는데 서비스
public class SampleTxServiceImpl implements SampleTxService{
	
	//로그
	private Logger log = LoggerFactory.getLogger(SampleTxServiceImpl.class);
	
	
	@Autowired
	private Sample1Mapper mapper1;
	@Autowired
	private Sample2Mapper mapper2;
	
	@Transactional //트랜잭션 처리
	@Override
	public void addData(String value) {
		
		log.info("mapper1......");
		mapper1.insertCol1(value);
		
		log.info("mapper2......");
		mapper2.insertCol2(value);
		
		log.info("end......");
		
	}

}
