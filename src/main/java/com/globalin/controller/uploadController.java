package com.globalin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.globalin.domain.AttachFile;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
public class uploadController {

	private Logger log = LoggerFactory.getLogger(uploadController.class);
	
	//첨부파일 삭제 요청을 처리해주는 메소드
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type){
		log.info("디리또화이루 : " + fileName);
		
		//삭제할 파일 객체 미리 선언
		File file;
		
		//뭔가하면 try{} catch{}
		try {
			file = new File("c:\\temp\\"+URLDecoder.decode(fileName,"UTF-8"));
			//원본 파일 삭제
			file.delete();
			
			if(type.equals("image")) {
				String sname = file.getAbsolutePath().replace("s_", "");
				//썸네일 파일
				file = new File(sname);
				file.delete();
			
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("deleted",HttpStatus.OK);
	}
	
	//다운로드 요청을 처리해주는 메소드 작성
	//원래는 byte[] 배열로 처리해야하지만 Spring에서 조금더 쉽게 할수있도록 Resource
	//octet_stream_value : 인코딩처리된 파일
	@GetMapping(value="/download", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fileName){
		
		log.info("download : " + fileName);
		
		//리턴할 resource 객체
		Resource resource = new FileSystemResource("c:\\temp\\"+fileName);
		
		//헤더를 통해서 이파일을 다운로드 할수있다고 브라우저에게 알려줘야한다
		//이 경로를 통해 요청하게 되면 브라우저는 자동으로 해당 파일을 다운로드 하도록 하게 된다.
		
		String resourceName = resource.getFilename();
		//resourceName 자원이름에서 uuid뺌
		//_기준 뒤에서부터 가져오면 원래 파일이름이 된다.
		resourceName = resourceName.substring(resourceName.indexOf("_")+1);
		
		HttpHeaders headers = new HttpHeaders();
		//헤더에 첨부파일 이라고 브라우저에게 알려주는것 인코딩처리된 파일이름 정보도 추가
		try {
			headers.add("Content-Disposition", "attachment; filename=" + new String(resourceName.getBytes("UTF-8"),"ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
	}
	
	//서버에서 GET방식으로 이미지파일을 가져올수 있도록 처리
	//특정한 uri뒤에 파일이름을 추가하면 이미지파일 데이터를 가져와서 <img>태그를 작성해줌
	//섬네일 이미지는 이름이 복잡 '파일경로' + 's_' + 'uuid' + '파일 원래이름'
	//그리고 한글 처리까지
	
	/**
	 * @param fileName
	 * @return
	 */
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName){
		//이미지 데이터를 byte[] 배열로 전송
		log.info("fileName : " + fileName);
		
		File file = new File("c:\\temp\\" + fileName);
		
		ResponseEntity<byte[]> result = null;
		/*
		 * byte[] 배열로 이미지 파일의 데이터를 브라우저에게 전송
		 * media type을 통해 브라우저한테 파일의 종류를 알려줘야한다.
		 * 브라우저에게 알려주는 방법은 응답의 헤더정보에 포함
		 * 
		 */
		
		//헤더객체 생성
		try {
			HttpHeaders header = new HttpHeaders();
			
			//헤더에 content type 설정 정보를 추가
			//probeContent type : 해당 파일의 Content type을 조사
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			//file객체를 byte배열로 바꿔서 전송
			//헤더 정보포함
			//응답코드 ok추가
			result = new ResponseEntity<byte[]>(
					FileCopyUtils.copyToByteArray(file),
					header,
					HttpStatus.OK
					);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}

	@GetMapping("/uploadForm")
	public void uploadForm() {
		// url이 파일이름이된다 =>uploadForm.jsp

	}

	@PostMapping("/uploadFormAction")
	public void uploadFormAction(MultipartFile[] uploadFile, Model model) {

		for (MultipartFile file : uploadFile) {
			log.info("------------");
			log.info("파일 이름 : " + file.getOriginalFilename());
			log.info("파일 크기 : " + file.getSize());
			// 이거랑 헷살리지 말것 getName : input 태그의 name속성값 반환
			log.info(file.getName());

			// 파일 객체를 만드는데 저장할 위치, 파일이름이 필요
			String uploadPath = "C:\\temp";
			File saveFile = new File(uploadPath, file.getOriginalFilename());

			// 파일 업로드하는 메소드 transferTo
			try {
				file.transferTo(saveFile);

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("upload ajax");

	}
	
	//브라우저에게 전달할 데이터가 생겼으니까 void타입이아니라
	//ResponseEntity<List<AttachFile>> 타입으로 바꾼다.
	//어노테이션에 이 메소드가 어떤 데이터를 제공할지도 정해줘 produces
	@PostMapping(value = "/uploadAjaxAction", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachFile>> uploadAjaxAction(MultipartFile[] uploadFile)
	{
		log.info("upload");
		List<AttachFile> list = new ArrayList<>();
		for(MultipartFile file : uploadFile) {
			log.info("------------");
			log.info("파일 이름 : " + file.getOriginalFilename());
			log.info("파일 크기 : " + file.getSize());
			//이거랑 헷살리지 말것 getName : input 태그의 name속성값 반환
			log.info(file.getName());
			
			//파일 객체를 만드는데 저장할 위치, 파일이름이 필요
			String uploadPath = "C:\\temp";
			
			//uuid로 파일이름 중복처리
			String uploadFileName=file.getOriginalFilename();
			
			UUID uuid = UUID.randomUUID();
			
			AttachFile attach = new AttachFile();
			attach.setFileName(uploadFileName);
			attach.setUuid(uuid.toString());
			attach.setUploadPath(uploadPath);
			
			// 저장할 파일 이름에 uuid_파일이름 형식으로 저장
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			File saveFile = new File(uploadPath, uploadFileName);
			
			
			//파일 업로드하는 메소드 transferTo
			try {
				//원본 파일은 저장 
				file.transferTo(saveFile);
				
				//혹시 file이 이미지 파일인 경우 섬네일 생성
				if(checkImageType(saveFile)==true) {
					attach.setImage(true);
					//섬네일 생성후 파일이름을 조금 다르게해서 저장
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
				
				//라이브러리를 사용해서 섬네일생성
					Thumbnailator.createThumbnail(file.getInputStream(),thumbnail,100,100);
					
					thumbnail.close();
				
				}
				list.add(attach);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	//썸네일을 만들때 해당 파일이 이미지 파일인지를 먼저 검사를 해야한다
	//이미지 파일 검사메소드
	private boolean checkImageType(File file) {
		//파라미터로 받아온 file이 이미지 파일이면 return true;
		//파라미터로 받아온 file이 이미지가 아니면 return false;
		
		//probe : 조사하다
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
			
			
