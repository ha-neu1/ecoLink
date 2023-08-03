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
				$("#updateBtn").on('click',function() {
							$.ajax({
								url : '/updateUserInfo',
								data : {
									'memId' : "${loginUser.memId}",
									'memPw' : $("#memPw").val(),
									'memNick' : $("#memNick").val(),
									'entPhone' : $("entPhone").val(),
									'entdMainPic' : $("entdMainPic").val(),
									'entdShort' : $("entdShort").val(),
									'entdURL' : $("entdURL").val(),
									'entdIntro' : $("entdIntro").val(),
									'file1' : $("entdIntroPic").val(),
									'entdPic1' : $("entdPic1").val(),
									'entdPic2' : $("entdPic2").val(),
									'entdPic3' : $("entdPic3").val(),
									'entdExplain1' : $("entdExplain1").val(),
									'entdExplain2' : $("entdExplain2").val(),
									'entdExplain3' : $("entdExplain3").val()		
								},
								type : 'post',
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
			});
	
	function logout() {
		$.ajax({
			type: "POST",
			url: "/logout",
			success: function() {
				location.reload(); // 현재 페이지 리로드
			},
			error: function() {
				alert("로그아웃 도중 오류가 발생했습니다.");
			}
		});
	}
	
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
			<form name="signUpForm" enctype="multipart/form-data">
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
					<input type="file" name="entdMainPicImg" id="entdMainPic"
						accept='image/jpeg,image/gif,image/png' onchange='chk_file_type(this)'
						value="${loginEnt.entdMainPic}">
				</div>
				
				<div class='formindiv'>
					<p>간단한 회사 설명</p>
					<textarea type="text" name="entdShort" id="entdShort" class="inputTypeLong" maxlength="255"
						>${loginEnt.entdShort}</textarea>
						<div id="numCount">0/255</div>
				</div>
				
				<div class='formindiv'>
					<p>회사 홈페이지 URL</p>
					<input type="url" name="entdURL" id="entdURL"
						value="${loginEnt.entdURL}">
				</div>
				
				<div class='formindiv'>
					<p>자세한 회사 설명</p>
						<textarea id="entdIntro" name="entdIntro" class="inputTypeLong" maxlength="1000">${loginEnt.entdIntro}</textarea>
                        <div id="numCount">0/1000</div>
				</div>
				
				<div class='formindiv'>
					<p>회사 설명 이미지</p>
					<input type="file" name="entdIntroPicImg" id="entdIntroPic"
					accept='image/jpeg,image/gif,image/png' onchange='chk_file_type(this)'
						value="${loginEnt.entdIntroPic}" >
				</div>
				
				<div class='formindiv'>
					<p>제품 이미지1</p>
					<c:if test="${loginEnt.entdPic1 ne null}">
					<input type="text" name="entCrn" id="entCrn"
						value="${loginEnt.entdPic1}" disabled>
					</c:if>
					<input type="file" name="entdPic1Img" id="entdPic1"
					accept='image/jpeg,image/gif,image/png' onchange='chk_file_type(this)'
						value="${loginEnt.entdPic1}" required>
				</div>
				<div class='formindiv'>
					<p>제품 이미지2</p>
					<input type="file" name="entdPic2Img" id="entdPic2"
					accept='image/jpeg,image/gif,image/png' onchange='chk_file_type(this)'
						value="${loginEnt.entdPic2}">
				</div>
				<div class='formindiv'>
					<p>제품 이미지3</p>
					<input type="file" name="entdPic3Img" id="entdPic3"
					accept='image/jpeg,image/gif,image/png' onchange='chk_file_type(this)'
						value="${loginEnt.entdPic3}">
				</div>
				<div class='formindiv'>
					<p>제품 설명1</p>
					<textarea name="entdExplain1" id="entdExplain1" class="inputTypeLong" maxlength="500" required>
					${loginEnt.entdExplain1}</textarea>
					<div id="numCount">0/500</div>
				</div>
				<div class='formindiv'>
					<p>제품 설명2</p>
					<textarea name="entdExplain2" id="entdExplain2" class="inputTypeLong" maxlength="500">
					${loginEnt.entdExplain2}</textarea>
						<div id="numCount">0/500</div>
				</div>
				<div class='formindiv'>
					<p>제품 설명3</p>
					<textarea name="entdExplain3" id="entdExplain3" class="inputTypeLong" maxlength="500">
					${loginEnt.entdExplain3}</textarea>
						<div id="numCount">0/500</div>
				</div>

				<div class='formindiv'>
					<button type="button" id="updateBtn">수정하기</button>
				</div>
			</form>
		</article>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>