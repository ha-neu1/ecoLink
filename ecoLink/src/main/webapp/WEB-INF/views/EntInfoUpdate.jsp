<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업정보 수정</title>
<link rel="stylesheet" href="css/EntInfoUpdate.css">
</head>
<script src="js/jquery-3.6.4.min.js"></script>
<script>
	$(document).ready(
			function() {
				$("#updateBtn").on(
						'click',
						function() {
							let param1 = {
									'memId' : $("#memId").val(),
									'memPw' : $("#memPw").val(),
									'memNick' : $("#memNick").val()
							};
							console.log(param1);
							
							let param2 = {
									'entPhone' : $("#entPhone").val(),
									'entdMainPic' : $("#entdMainPic").val(),
									'entdShort' : $("#entdShort").val(),
									'entdURL' : $("#entdURL").val(),
									'entdIntro' : $("#entdIntro").val(),
									'entdIntroPic' : $("#entdIntroPic").val(),
									'entdPic1' : $("#entdPic1").val(),
									'entdPic2' : $("#entdPic2").val(),
									'entdPic3' : $("#entdPic3").val(),
									'entdExplain1' : $("#entdExplain1").val(),
									'entdExplain2' : $("#entdExplain2").val(),
									'entdExplain3' : $("#entdExplain3").val()
							};
							console.log(param2);
							
							let fileImg = $('#logoPic')[0].files[0];
							let fileImg2 = $('#introPic')[0].files[0];
							let fileImg3 = $('#dPic1')[0].files[0];
							let fileImg4 = $('#dPic2')[0].files[0];
							let fileImg5 = $('#dPic3')[0].files[0];
							
							console.log(fileImg);

							let form = new FormData();

							form.append("mem", new Blob([ JSON.stringify(param1) ], {type : "application/json"}))
							form.append("ent", new Blob([ JSON.stringify(param2) ], {type : "application/json"}))
							form.append("img1", fileImg);
							form.append("img2", fileImg2);
							form.append("img3", fileImg3);
							form.append("img4", fileImg4);
							form.append("img5", fileImg5);

							$.ajax({
								type : "POST",
								url : "/updateEntInfo",
								contentType : false, //중요 : false 로 선언 시 content-type 헤더가 multipart/form-data로 전송되게 함
								processData : false, //중요 : false로 선언 시 formData를 string으로 변환하지 않음
								enctype : 'multipart/form-data',//중요
								data : form,
								success : function(res) {
									alert("기업정보 수정이 완료되었습니다. 승인을 기다려 주세요.");
									logout();
								},
								error : function(request, status, e) {
									alert("코드=" + request.status + "\n메시지="
											+ request.responseText + "\nerror="
											+ e);
								}
							});
						});
				
				function logout() {
					alert("로그아웃");
					$.ajax({
						type : "get",
						url : "/logout",
						success : function() {
							alert("로그아웃에 성공했습니다.");
						},
						error : function() {
							alert("로그아웃 도중 오류가 발생했습니다.");
						}
					});
				}
			});



	/* function chk_file_type(obj) {
		 var file_kind = obj.value.lastIndexOf('.');
		 var file_name = obj.value.substring(file_kind+1,obj.length);
		 var file_type = file_name.toLowerCase();

		 var check_file_type=new Array();​
		 check_file_type=['jpg','gif','png','jpeg','bmp'];

		 if(check_file_type.indexOf(file_type)==-1){
		  alert('이미지 파일만 선택할 수 있습니다.');
		  var parent_Obj=obj.parentNode
		  var node=parent_Obj.replaceChild(obj.cloneNode(true),obj);
		  return false;
		 }
		} */
		
		$(document).ready(function() {
		      // 새로운 함수: 문자 수 표시 함수
		      function showCharacterCount(textareaId, divId) {
		    	  const charCount = document.getElementById(divId);
		    	  const textarea = document.getElementById(textareaId);
		    	  const maxLength = textarea.getAttribute("maxlength");
		    	  const currentLength = textarea.value.length;
		          charCount.textContent = currentLength + "/" + maxLength;
		      }
		
		      // 새로운 기능: 각 textarea에 대한 문자 수 표시를 위한 이벤트 핸들러 추가
		      const textareaIds = ["entdShort", "entdIntro", "entdExplain1", "entdExplain2", "entdExplain3"]; // 문자 수를 표시할 textarea 요소들의 ID 목록
		      const divIds = ["shortCharCount", "IntrocharCount", "ExplaincharCount1", "ExplaincharCount2", "ExplaincharCount3"]; // 문자 수를 표시할 div 요소들의 ID 목록
		
		      for (let i = 0; i < textareaIds.length; i++) {
		    	  const textareaId = textareaIds[i];
		    	  const divId = divIds[i];
		    	  const textarea = document.getElementById(textareaId);
		    	  textarea.addEventListener('input', function() {
		    	      showCharacterCount(textareaId, divId);
		    	  });
		    	  showCharacterCount(textareaId, divId); // 최초 페이지 로드 시 문자 수 표시를 위해 호출
		      }
	      });
</script>
<body>
	<%@ include file="header.jsp"%>
	<div class="containers">
		<article>
			<aside id="sidebar">
				<div id='menus'>
					<ul id='menusList'>
						<li class='menuItem' id=''><a href="/userInfo">기업 정보</a></li>
						<li class='menuItem' id=''><a href="/brandpromodetail?entCrn=${loginEnt.entCrn}">내 브랜드 조회</a></li>
					</ul>
				</div>
			</aside>
			<div class='forminheader'>
				<h2>기업정보 수정</h2>
				<p>고객님이 등록한 기업정보를 수정하실 수 있습니다.</p>
				</div>
				<div class='formindiv'>
					<p>아이디</p>
					<div id=id_div>
						<input type="text" name="memId" id="memId"
							value="${loginUser.memId}" disabled>
					</div>
				</div>

				<div class='formindiv'>
					<p>비밀번호</p>
					<label><input type="password" name="memPw" id="memPw"
						value="${loginUser.memPw}" maxlength="16"></label>
				</div>

				<div class='formindiv'>
					<p>대표 사업자 이름</p>
					<input type="text" name="memName" id="memName"
						value="${loginUser.memName}" disabled>
				</div>

				<div class='formindiv'>
					<p>브랜드 이름</p>
					<input type="text" name="memNick" id="memNick"
						value="${loginUser.memNick}">
				</div>

				<div class='formindiv'>
					<p>E-mail</p>
					<input type="tel" name="memEmail" id="memEmail"
						value="${loginUser.memEmail}" disabled>
				</div>
				
				<div class='formindiv'>
					<p>사업자 등록번호</p>
					<input type="text" name="entCrn" id="entCrn"
						value="${loginEnt.entCrn}" disabled>
				</div>
				
				<div class='formindiv'>
					<p>사업자 연락처</p>
					<input type="tel" name="entPhone" id="entPhone"
						value="${loginEnt.entPhone}">
				</div>
				
				<div class='formindiv'>
					<p>회사 로고 이미지</p>
					<c:if test="${loginEnt.entdMainPic ne null}">
					<input type="text" name="entdMainPic" id="entdMainPic"
						value="${loginEnt.entdMainPic}" disabled>
					</c:if>
					<input type="file" name="logoPic" id="logoPic"
						accept='image/jpeg,image/gif,image/png' >
				</div>
				
				<div class='formindiv'>
					<p>간단한 회사 설명</p>
					<textarea type="text" name="entdShort" id="entdShort" class="inputTypeLong" maxlength="255"
						>${loginEnt.entdShort}</textarea>
						<div id="shortCharCount" class="numCount" >0/255</div>
				</div>
				
				<div class='formindiv'>
					<p>회사 홈페이지 URL</p>
					<input type="url" name="entdURL" id="entdURL"
						value="${loginEnt.entdURL}">
				</div>
				
				<div class='formindiv'>
					<p>자세한 회사 설명</p>
						<textarea id="entdIntro" name="entdIntro" class="inputTypeLong" maxlength="1000">${loginEnt.entdIntro}</textarea>
                        <div id="IntrocharCount" class="numCount">0/1000</div>
				</div>
				
				<div class='formindiv'>
					<p>회사 설명 이미지</p>
					<c:if test="${loginEnt.entdIntroPic ne null}">
					<input type="text" name="entdIntroPic" id="entdIntroPic"
						value="${loginEnt.entdIntroPic}" disabled>
					</c:if>
					<input type="file" name="introPic" id="introPic"
					accept='image/jpeg,image/gif,image/png' >
				</div>
				
				<div class='formindiv'>
					<p>제품 이미지1</p>
					<c:if test="${loginEnt.entdPic1 ne null}">
					<input type="text" name="entdPic1" id="entdPic1"
						value="${loginEnt.entdPic1}" disabled>
					</c:if>
					<input type="file" name="dPic1" id="dPic1"
					accept='image/jpeg,image/gif,image/png' required>
				</div>
				<div class='formindiv'>
					<p>제품 이미지2</p>
					<c:if test="${loginEnt.entdPic2 ne null}">
					<input type="text" name="entdPic2" id="entdPic2"
						value="${loginEnt.entdPic2}" disabled>
					</c:if>
					<input type="file" name="dPic2" id="dPic2"
					accept='image/jpeg,image/gif,image/png' >
				</div>
				<div class='formindiv'>
					<p>제품 이미지3</p>
					<c:if test="${loginEnt.entdPic3 ne null}">
					<input type="text" name="entdPic3" id="entdPic3"
						value="${loginEnt.entdPic3}" disabled>
					</c:if>
					<input type="file" name="dPic3" id="dPic3"
					accept='image/jpeg,image/gif,image/png' >
				</div>
				<div class='formindiv'>
					<p>제품 설명1</p>
					<textarea name="entdExplain1" id="entdExplain1" class="inputTypeLong" maxlength="500" required>
					${loginEnt.entdExplain1}</textarea>
					<div id="ExplaincharCount1" class="numCount">0/500</div>
				</div>
				<div class='formindiv'>
					<p>제품 설명2</p>
					<textarea name="entdExplain2" id="entdExplain2" class="inputTypeLong" maxlength="500">
					${loginEnt.entdExplain2}</textarea>
						<div id="ExplaincharCount2" class="numCount">0/500</div>
				</div>
				<div class='formindiv'>
					<p>제품 설명3</p>
					<textarea name="entdExplain3" id="entdExplain3" class="inputTypeLong" maxlength="500">
					${loginEnt.entdExplain3}</textarea>
						<div id="ExplaincharCount3" class="numCount">0/500</div>
				</div>

				<div class='formindiv'>
					<button type="button" id="updateBtn">수정하기</button>
				</div>
		</article>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>