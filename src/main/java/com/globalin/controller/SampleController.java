package com.globalin.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globalin.domain.SampleVO;
import com.globalin.domain.TicKet;

@RestController
@RequestMapping("/sample")
public class SampleController {
	
	private Logger log = LoggerFactory.getLogger(SampleController.class);
	/*
	 * RestController는 jsp와 달리 순수한 데이터를 반환해준다.
	 * 다양한 형식의 데이터를 전송할수있다.
	 * 주로 많이 사용하는 형태 : 문자열, JSON,XML
	 * 
	 */
	
	//문자열을 반환하는 형태
	//Controller는 리턴타입이 String인 메소드 : 
	//리턴한 결과가 jsp 파일이름이 되었는데, RestController 는 순수한 데이터를 준다.
	//produces 속성은 메소드가 생성하는 media type을 의미한다.
	@GetMapping(value="/getText" ,  produces = "text/plain; charset=UTF-8")
	public String getText() {
		
		log.info("type : "+MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세요";
		
	}
	//객체를 반환 json이나 xml을 이용한다.
	@GetMapping(value="/getSample",
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public SampleVO getSample() {
		 return new SampleVO(2,"스티븐","스트레인지");
	}
	
	@GetMapping(value="/getList")
	public List<SampleVO> getList(){
		List<SampleVO> list = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			SampleVO s = new SampleVO(i, i + "First", i+"Last");
			list.add(s);
		}
		return list;
	
	}
	@GetMapping(value="/getMap")
	public Map<String, SampleVO> getMap(){
		Map<String, SampleVO> map = new HashMap<String, SampleVO>();
		map.put("First", new SampleVO(11,"그루트","주니어"));
		return map;
	}
	
	//헤더 정보를 조작해서 헤더의 상태메시지를 같이 포함해서 전달하는 방법
	@GetMapping(value="/ImBadGate", params = {"height","weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight){
		SampleVO s= new SampleVO(0,"" + height ,"" + weight);
		
		//우리가 리턴해줄 ResponseEntity 객체
		ResponseEntity<SampleVO> result = null;
		
		//키가 180이하면 BAD_GATEWAY
		if(height <= 180) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(s);
			
		}else {
			result = ResponseEntity.status(HttpStatus.OK).body(s);
		}
		
		return result;
	}
	
	//RestController 에서 많이 사용하는 URL 경로에 파라미터를 포하하는 방법
	//?파라미터 = 값 : 쿼리스트링
	///파라미터값/
	//이 요청은 요청 url에 파라미터 값이 포함되어있다.
	//각 파라미터의 이름은 cat,pid이다 (값이 cat,pid가 아님)
	//PathVariable 에는 기본자료형 사용x
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat,@PathVariable("pid") Integer pid) {
		//String cat은 url에 {cat} 자리에 있는 값을 가져온다.
		//Integer pid는 url에{pid} 자리에 있는 값을 가져온다.
		// cat: category pid : product id
		return new String[] {"category: " + cat, "productid: "+pid};
	}
	
	@PostMapping("/ticket")
	public TicKet ticket(@RequestBody TicKet ticket) {
		
		log.info("ticket : " + ticket);
		
		return ticket;
				
	}
}
