package com.globalin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globalin.domain.Criteria;
import com.globalin.domain.ReplyPage;
import com.globalin.domain.ReplyVO;
import com.globalin.service.ReplyService;

@RestController
@RequestMapping("/replies/")
public class ReplyController {

	@Autowired
	private ReplyService service;
	
	/*rest 방식으로 처리할때 주의할점
	 * 
	 * 외부에서 서버를 호출할때 데이터의 포맷(형식) // 서버에서 보내주는 데이터의 포맷(형식)
	 * 을 명확하게 알 수 있도록 한다.
	 * ex)
	 * 브라우저가 보낸 데이터는 json // 서버가 보낸 데이터는 문자열 형식
	 * 정상적으로 처리되었는지 http status 코드를 리턴해서 알려줄수있도록 한다.
	 * */
	
	
	//등록 post
	//url /replies/new
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value="/new", consumes="application/json", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){
		int result = service.register(vo);
		
		if(result == 1 ) {
			return new ResponseEntity<>("success" , HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//수정 put
	//url /replies/:rno
	@PutMapping(value="/{rno}", consumes="application/json", produces= {MediaType.TEXT_PLAIN_VALUE})
	@PreAuthorize("principal.username == #vo.replyer")
	public ResponseEntity<String> modify(@PathVariable("rno") int rno, @RequestBody ReplyVO vo){
		vo.setRno(rno);
		int result = service.modify(vo);
		if(result == 1 ) {
			return new ResponseEntity<>("success" , HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//삭제 delete
		//url /replies/:rno
	@DeleteMapping(value="/{rno}", produces= {MediaType.TEXT_PLAIN_VALUE})
	@PreAuthorize("principal.username == #vo.replyer")
	public ResponseEntity<String> remove(@RequestBody ReplyVO vo,@PathVariable("rno") int rno){
		
		return service.remove(rno) == 1?
				new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	//조회 get
	//url /replies/:rno
	@GetMapping
	(value="/{rno}",produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") int rno){
		
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
	}


	
	//페이지처리된리스트 get
	//url /replies/pages/:bno/:page
	@GetMapping(value="/pages/{bno}/{page}", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyPage> getList(
		@PathVariable("page") int page,
		@PathVariable("bno") int bno)
	{
		//ReplyPage = 댓글개수 + 댓글리스트
		Criteria cri = new Criteria();
		cri.setPageNum(page);
		cri.setAmount(10);
		
		
		
		return new ResponseEntity<>(service.getListPage(cri, bno),HttpStatus.OK);
	}

}
