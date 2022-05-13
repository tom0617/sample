<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.uploadResult {
	width: 100%;
	background-color: gray;
}

.uploadResult ul {
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}

.uploadResult ul li {
	list-style: none;
	padding: 10px;
}

.uploadResult ul li img {
	width: 100px;
}

.bigPictureWrapper {
	position: absolute;
	display: none;
	justify-content: center;
	align-items: center;
	top: 0;
	width: 100%;
	heiight: 100%;
	background-color: gray;
	z-index: 100;
}

.bigPicture {
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
}

.bigPicture img {
	width: 600px;
}

.uploadResult ul li span {
	color: white
}
</style>
</head>
<body>
	<h1>holymoly</h1>
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple>
	</div>

	<button id="uploadBtn">파일 업로드</button>
	<div class="uploadResult">
		<ul>
			<!-- 업로드 파일 리스트 -->

		</ul>
	</div>
	<div class="bigPictureWrapper">
		<div class="bigPicture"></div>

	</div>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
		crossorigin="anonymous"></script>
	<script>
		function showImage(filePath) {
			//alert(filePath);
			$(".bigPictureWrapper").css("display", "flex").show();

			//html코드로imgg 태그를 삽입
			$(".bigPicture")
					.html(
							"<img src='/display?fileName="
									+ encodeURI(filePath) + "'>").animate({
						width : '100%',
						height : '100%'
					}, 1000);

			$(".bigPictureWrapper").on("click", function(e) {
				//이미지 크기만 0으로줄이기
				$(".bigPicture").stop().animate({
					width : '0%',
					height : '0%'
				}, 1000);

				//1초후에 숨기기
				setTimeout(function() {
					$(".bigPictureWrapper").hide();
				}, 1000);
			})

		}
		$(".uploadResult").on("click", "span", function(e) {
			//삭제 대상 파일
			let targetFile = $(this).data("file");
			//삭제대상 파일의 타입
			let type = $(this).data("type");

			$.ajax({
				url : '/deleteFile',
				type : 'POST',
				data : {
					fileName : targetFile,
					type : type
				},
				dataType : 'text',
				success : function(result) {
					alert(result);
				}

			})

		})

		$(document)
				.ready(
						function() {
							//여기서 확장자 제한
							//exe, 압축파일 제한
							//파일 크기제한
							//확장자를 검사하는제일 간단한 방법 ==>String 함수를 이용해서		
							//파일 이름안에 exe ? zip? alz? sh? 이런것들이 포함되어있는지 검사
							//우아한 엘레강스한 방법 => 표현식(정규식)
							//파일이름.exe 파일이름.zip ... 
							let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
							let maxSize = 5242880;//파일 크기 5mb

							//깨끗한 상태 저장
							var cloneObj = $(".uploadDiv").clone();

							//업로드 결과를 보여줄 div 태그 안에 ul태그 찾아오기
							var uploadResult = $(".uploadResult ul");

							//원본이미지를 보여주는 함수를 추가
							//jquery 로드 안된 상황에서도 사용할수 있도록 밖에다가 작성

							//우리가 찾아온 ul태그 안에 업로드한 파일들의 정보를 동적으로 생성하여 추가
							function showUploadedFile(uArr) {

								let uploadHtml = "";
								//업로드 파일 한개 == <li>태그 한개 
								for (let i = 0; i < uArr.length; i++) {

									if (uArr[i].image == false) {
										//이미지 파일이 아닌경우에
										//li 태그 앞에다가 파일 아이콘붙여
										let fileCallPath = encodeURIComponent("/"
												+ uArr[i].uuid
												+ "_"
												+ uArr[i].fileName);
										uploadHtml += "<li><a href='/download?fileName="
												+ fileCallPath
												+ "'><img src='/resources/img/pngegg.png'>"
												+ uArr[i].fileName
												+ "</a><span data-file=\'"+fileCallPath+"\'data-type='file'>x</li>";
									} else {
										//이미지 파일은 경우
										//img태그를 추가하는데 이거를 올린 이미지로
										//uploadHtml += "<li>" + uArr[i].fileName + "</li>";
										//우리가 첨부파일을 이미지로 올리면 원본파일 올리고, 추가로 썸네일 이미지가 생성
										//여기서 사용할거는 섬네일 이미지 / 섬네일 이미지는 파일이름
										//s_ +uuid + 원래파일이름
										//한글이름이 문제가 될수있어서 encodig처리

										//<a href="다운로드 요청주소">
										let fileCallPath = encodeURIComponent("/s_"
												+ uArr[i].uuid
												+ "_"
												+ uArr[i].fileName);

										let originPath = uArr[i].uuid + "_"
												+ uArr[i].fileName;
										//역슬래쉬 두개있는 문자열을 슬래쉬 하나로 모두 바꿔준다.
										originPath = originPath.replace(
												new RegExp(/\\/g), "/");

										uploadHtml += "<li><a href=\"javascript:showImage(\'"
												+ originPath
												+ "\')\"><img src='/display?fileName="
												+ fileCallPath
												+ "'></a><span data-file=\'"+fileCallPath+"\'data-type='image'>x</span></li>";

									}
								}
								uploadResult.append(uploadHtml);
							}

							//파일을 검사하는 함수
							function checkFile(fileName, fileSize) {
								if (fileSize > maxSize) {
									alert("파일 최대크기 초과");
									return false;
								}
								//파일 확장자 검사 : 정규식과 파일 이름이 일치하는 패턴이면 false 를 리턴
								if (regex.test(fileName)) {
									alert("해당 종류의 파일은 업로드 불가!");

									return false;
								}
								//두개 모두 통과했다면 return true;
								return true;

							}

							$("#uploadBtn")
									.on(
											"click",
											function(e) {

												//form태그 없이 form을 만들어서 보내는 방법ㅂ
												let formData = new FormData();
												//input 태그 가져오기
												let file = $("input[name='uploadFile']");
												//input 태그에서 file가져오기			
												let files = file[0].files;

												console.log(files);

												//formData에 파일 추가
												for (let i = 0; i < files.length; i++) {

													if (checkFile(
															files[i].name,
															files[i].size) == false) {
														return false;
													}

													formData.append(
															"uploadFile",
															files[i]);
												}
												//input type=file 초기화 준비
												//초기화는 언제? 파일업로드 요청해주고 나서

												//ajax 요청 보내기
												//processData , contentType 이왜 false 인가?
												//contentType : application/json
												//우리가 파일을 실어서 보내는데 content type이 multipart로 설정되서 보내진다
												//false 로 넣어줘야 contentType 이 multipart로 설정되어서 보잰디ㅏ
												//processData : 우리가 ajax로 요청을 본ㄹ때 data속성이 jquery내부적으로 query string으로 변경을
												//해버린다(데이터처리)
												//우리는 데이터처리를 하지말고 파일전송의 경우 파일의 데이터를 그대로 보내야하기때문에
												//처리하지말고 false값으준다.
												$
														.ajax({
															url : "/uploadAjaxAction",
															processData : false,
															contentType : false,
															data : formData,
															type : "POST",
															success : function(
																	result) {
																console
																		.log(result);
																//li태그 추가
																showUploadedFile(result);

																//요청 보내고 나서 성공하면 input file 초기화
																$(".uploadDiv")
																		.html(
																				cloneObj
																						.html());
															}

														})

											})

						})
	</script>
</body>
</html>