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
	$(document).ready(function() {
		
		// 파일 선택(input type="file") 요소에 대한 이벤트 리스너를 추가하여 이미지 미리 보기 기능 구현
	      function addImagePreviewListener(inputId, previewId) {
	          document.getElementById(inputId).addEventListener("change", function(event) {
	              const file = event.target.files[0]; // 선택된 파일 가져오기

	              // FileReader 객체를 생성하여 파일을 읽어옴
	              const reader = new FileReader();

	              // 파일을 성공적으로 읽었을 때의 이벤트 처리
	              reader.onload = function() {
	                  // 미리 보기 영역에 이미지를 삽입하여 미리 보기
	                  const previewArea = document.getElementById(previewId);
	                  previewArea.innerHTML = ""; // 기존 이미지 초기화
	                  const imgElement = document.createElement("img");
	                  imgElement.src = reader.result;
	                  imgElement.alt = "미리 보기 이미지";
	                  imgElement.style.maxWidth = "100%"; // 미리 보기 이미지의 최대 너비 설정

	                  // 이미지 삭제 버튼 생성
	                  const deleteBtn = document.createElement("button");
	                  deleteBtn.textContent = "등록된 이미지 삭제";
	                  deleteBtn.addEventListener("click", function() {
	                      // 이미지 삭제 버튼이 클릭되면 미리 보기 영역에서 이미지 제거
	                      previewArea.innerHTML = "";
	                      // 선택한 파일도 초기화
	                      document.getElementById(inputId).value = "";
	                  });

	                  // 이미지와 삭제 버튼을 미리 보기 영역에 추가
	                  previewArea.appendChild(imgElement);
	                  previewArea.appendChild(deleteBtn);
	              };

	              // 파일 읽기 작업 실행
	              if (file) {
	                  reader.readAsDataURL(file);
	              }
	          });
	      }

	      // 각 파일 선택 요소에 대해 함수를 호출하여 이벤트 리스너를 추가
	      addImagePreviewListener("entdMainPic", "preview1");
	      addImagePreviewListener("entdIntroPic", "preview2");
	      addImagePreviewListener("entdPic1", "preview3");
	      addImagePreviewListener("entdPic2", "preview4");
	      addImagePreviewListener("entdPic3", "preview5");
		
	   // 비밀번호 일치 여부 확인
	      $(document).on('click', 'input:not(#memPw_confirm)', function(e) {
	         var pwConfirmValue = $('#memPw_confirm').val();
	         var pwValue = $('#memPw').val();
	         if (pwValue !== '' && pwConfirmValue !== '' && pwConfirmValue !== pwValue) {
	             alert("비밀번호가 일치하지 않습니다.");
	         }
	      });
		
				$("#updateBtn").on('click', function(e) {
					e.preventDefault();
					
					var memId = $("#memId").val();
					var memPw = $("#memPw").val();
					var memPw_confirm = $('#memPw_confirm').val();
					var memNick = $("#memNick").val();
					var entCrn = $("#entCrn").val();
					var entPhone = $("#entPhone").val();
					var entdMainPic = $("#entdMainPic").val();
					var entdShort = $("#entdShort").val();
					var entdURL = $("#entdURL").val();
					var entdIntro = $("#entdIntro").val();
					var entdIntroPic = $("#entdIntroPic").val();
					var entdPic1 = $("#entdPic1").val();
					var entdPic2 = $("#entdPic2").val();
					var entdPic3 = $("#entdPic3").val();
					var entdExplain1 = $("#entdExplain1").val();
					var entdExplain2 = $("#entdExplain2").val();
					var entdExplain3 = $("#entdExplain3").val();
					
							
					if (memPw === "" || memNick === "" || entPhone === "" || entdExplain1 === "") {
		                alert("비밀번호, 브랜드 이름, 연락처, 제품1 이미지, 제품1 설명을 필수로 입력해 주세요.");
		            } else if(memPw !== memPw_confirm) {
		            	alert("비밀번호가 일치하지 않습니다.");
	            	} else {
					
							let param1 = {
									'memId' : memId,
									'memPw' : memPw,
									'memNick' : memNick
							};
							console.log(param1);
							
							let param2 = {
									'memId' : memId,
									'entCrn' : entCrn,
									'entPhone' : entPhone,
									'entdMainPic' : entdMainPic,
									'entdShort' : entdShort,
									'entdURL' : entdURL,
									'entdIntro' : entdIntro,
									'entdIntroPic' : entdIntroPic,
									'entdPic1' : entdPic1,
									'entdPic2' : entdPic2,
									'entdPic3' : entdPic3,
									'entdExplain1' : entdExplain1,
									'entdExplain2' : entdExplain2,
									'entdExplain3' : entdExplain3
							};
							console.log(param2);
							
							let fileImg = $('#logoPic')[0].files[0];
							let fileImg2 = $('#introPic')[0].files[0];
							let fileImg3 = $('#dPic1')[0].files[0];
							let fileImg4 = $('#dPic2')[0].files[0];
							let fileImg5 = $('#dPic3')[0].files[0];
							
							console.log(fileImg);
							console.log(fileImg2);
							console.log(fileImg3);
							console.log(fileImg4);
							console.log(fileImg5);

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
									location.href="/userInfo";
								},
								error : function(request, status, e) {
									alert("코드=" + request.status + "\n메시지="
											+ request.responseText + "\nerror="
											+ e);
								}
							});
		            }
						});
				
			});
		
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
						<input type="text" name="memId" id="memId" value="${loginUser.memId}" disabled>
					</div>
				</div>

				<div class='formindiv'>
					<p>비밀번호*</p>
					<label><input type="password" name="memPw" id="memPw" value="${loginUser.memPw}" autocomplete="off" maxlength="16"></label>
					<a class="help">(영문 대소문자/숫자/특수문자 중 2가지 이상 조합, 8자~16자)</a>
				</div>
				
				<div class='formindiv'>
					<p>비밀번호 확인*</p>
					<label><input type="password" name="memPw_confirm" id="memPw_confirm" value="" autocomplete="off" maxlength="16"></label>
				</div>

				<div class='formindiv'>
					<p>대표 사업자 이름</p>
					<input type="text" name="memName" id="memName"
						value="${loginUser.memName}" disabled>
				</div>

				<div class='formindiv'>
					<p>브랜드 이름*</p>
					<input type="text" name="memNick" id="memNick"
						value="${loginUser.memNick}">
					<a class="help">상호명 혹은 기업명을 입력해주세요.</a>
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
					<p>사업자 연락처*</p>
					<input type="tel" name="entPhone" id="entPhone"
						value="${loginEnt.entPhone}">
				</div>
				
				<div class='formindiv'>
					<p>회사 로고 이미지</p>
					<c:if test="${loginEnt.entdMainPic ne null}">
					<input type="text" name="entdMainPic" id="entdMainPic" value="${loginEnt.entdMainPic}" disabled>
					</c:if>
					<input type="file" name="logoPic" id="logoPic" accept="image/*">
						<div id="preview1"></div>
						<a class="help">대표 이미지 해상도 (680 x  280) 권장</a>
				</div>
				
				<div class='formindiv'>
					<p>간단한 회사 설명</p>
					<textarea type="text" name="entdShort" id="entdShort" class="inputTypeLong" maxlength="255">${loginEnt.entdShort}</textarea>
						<div id="shortCharCount" class="numCount" >0/255</div>
				</div>
				
				<div class='formindiv'>
					<p>회사 홈페이지 URL</p>
					<input type="url" name="entdURL" id="entdURL" value="${loginEnt.entdURL}">
				</div>
				
				<div class='formindiv'>
					<p>자세한 회사 설명</p>
						<textarea id="entdIntro" name="entdIntro" class="inputTypeLong" maxlength="1000">${loginEnt.entdIntro}</textarea>
                        <div id="IntrocharCount" class="numCount">0/1000</div>
				</div>
				
				<div class='formindiv'>
					<p>회사 설명 이미지</p>
					<c:if test="${loginEnt.entdIntroPic ne null}">
					<input type="text" name="entdIntroPic" id="entdIntroPic" value="${loginEnt.entdIntroPic}" disabled>
					</c:if>
					<input type="file" name="introPic" id="introPic" accept="image/*" >
					<div id="preview2"></div>
				</div>
				
				<div class='formindiv'>
					<p>제품 이미지1*</p>
					<c:if test="${loginEnt.entdPic1 ne null}">
					<input type="text" name="entdPic1" id="entdPic1" value="${loginEnt.entdPic1}" disabled>
					</c:if>
					<input type="file" name="dPic1" id="dPic1" accept="image/*" required>
					<div id="preview3"></div>
				</div>
				<div class='formindiv'>
					<p>제품 이름1*</p>
					<textarea name="entdExplain1" id="entdExplain1" class="inputType" maxlength="15" required>${loginEnt.entdExplain1}</textarea>
					<div id="ExplaincharCount1" class="numCount">0/15</div>
				</div>
				<div class='formindiv'>
					<p>제품 이미지2</p>
					<c:if test="${loginEnt.entdPic2 ne null}">
					<input type="text" name="entdPic2" id="entdPic2" value="${loginEnt.entdPic2}" disabled>
					</c:if>
					<input type="file" name="dPic2" id="dPic2" accept="image/*" >
					<div id="preview4"></div>
				</div>
				<div class='formindiv'>
					<p>제품 이름2</p>
					<textarea name="entdExplain2" id="entdExplain2" class="inputType" maxlength="15">${loginEnt.entdExplain2}</textarea>
						<div id="ExplaincharCount2" class="numCount">0/15</div>
				</div>
				<div class='formindiv'>
					<p>제품 이미지3</p>
					<c:if test="${loginEnt.entdPic3 ne null}">
					<input type="text" name="entdPic3" id="entdPic3" value="${loginEnt.entdPic3}" disabled>
					</c:if>
					<input type="file" name="dPic3" id="dPic3" accept="image/*" >
					<div id="preview5"></div>
				</div>
				<div class='formindiv'>
					<p>제품 이름3</p>
					<textarea name="entdExplain3" id="entdExplain3" class="inputType" maxlength="15">${loginEnt.entdExplain3}</textarea>
						<div id="ExplaincharCount3" class="numCount">0/15</div>
				</div>
				<div class='formindiv'>
					<button type="button" id="updateBtn">수정하기</button>
				</div>
		</article>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>