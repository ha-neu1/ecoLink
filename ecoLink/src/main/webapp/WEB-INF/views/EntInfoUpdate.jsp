<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<link rel="stylesheet" href="css/EntInfoUpdate.css">
</head>
<script src="js/jquery-3.6.4.min.js"></script>
<script>
	$(document).ready(
			function() {
				$("#updateBtn").on(
						'click',
						function() {
							$.ajax({
								url : '/updateUserInfo',
								data : {
									'memId' : "${loginUser.memId}",
									'pw' : $("#pw").val(),
									'email' : $("#email").val()
								},
								type : 'post',
								success : function(res) {
									alert("기업정보 수정이 완료되었습니다. 승인을 기다려 주세요.");
									location.href = "/entInfo"
								},
								error : function(request, status, e) {
									alert("코드=" + request.status + "\n메시지="
											+ request.responseText + "\nerror="
											+ e);
								}
							});
						});
			});
</script>
<body>
	<%@ include file="header.jsp"%>
	<aside id="sidebar">
			<ul id='menusList'>
				<li class='menuItem' id=''><a href="/entInfo">기업 정보</a></li>
				<li class='menuItem' id=''><a href="#">내 브랜드 조회</a></li>
			</ul>
	</aside>
	<div class="containers">
		<article>
			<form name="signUpForm">
				<h2>기업정보 수정</h2>
				<p>고객님이 등록한 기업정보를 수정하실 수 있습니다.</p>
				<div class='formindiv'>
					<p>아이디</p>
					<div id=id_div>
						<input type="text" name="userid" id="userid"
							value="${loginUser.memId}" maxlength="16" disabled>
					</div>
				</div>

				<div class='formindiv'>
					<p>비밀번호</p>
					<label><input type="password" name="pw" id="pw"
						value="${loginUser.memPw}" maxlength="16"></label>
				</div>

				<div class='formindiv'>
					<p>대표 사업자 이름</p>
					<input type="text" name="name" id="name"
						value="${loginUser.memName}" disabled>
				</div>

				<div class='formindiv'>
					<p>브랜드 이름</p>
					<input type="text" name="nickname" id="nickname"
						value="${loginUser.memNick}">
				</div>

				<div class='formindiv'>
					<p>E-mail</p>
					<input type="tel" name="email" id="email"
						value="${loginUser.memEmail}" disabled>
				</div>
				
				<div class='formindiv'>
					<p>사업자 등록번호</p>
					<input type="text" name="entnum" id="entnum"
						value="${loginEnt.entCrn}" disabled>
				</div>
				
				<div class='formindiv'>
					<p>사업자 연락처</p>
					<input type="text" name="phonenum" id="phonenum"
						value="${loginEnt.entPhone}">
				</div>
				
				<div class='formindiv'>
					<p>대표 사진</p>
					<input type="text" name="nickname" id="nickname"
						value="${loginEnt.entdMainPic}">
				</div>
				
				<div class='formindiv'>
					<p>회사 간략 소개</p>
					<input type="text" name="nickname" id="nickname"
						value="${loginEnt.entdShort}">
				</div>
				
				<div class='formindiv'>
					<p>회사 홈페이지</p>
					<input type="text" name="nickname" id="nickname"
						value="${loginEnt.entdURL}">
				</div>
				
				<div class='formindiv'>
					<p>회사 소개</p>
					<input type="text" name="nickname" id="nickname"
						value="${loginEnt.entdIntro}">
				</div>
				
				<div class='formindiv'>
					<p>회사 소개 사진</p>
					<input type="text" name="nickname" id="nickname"
						value="${loginEnt.entdIntroPic}">
				</div>
				
				<div class='formindiv'>
					<p>제품 사진1</p>
					<input type="text" name="nickname" id="nickname"
						value="${loginEnt.entdPic1}">
				</div>
				<div class='formindiv'>
					<p>제품 사진2</p>
					<input type="text" name="nickname" id="nickname"
						value="${loginEnt.entdPic2}">
				</div>
				<div class='formindiv'>
					<p>제품 사진3</p>
					<input type="text" name="nickname" id="nickname"
						value="${loginEnt.entdPic3}">
				</div>
				<div class='formindiv'>
					<p>제품 설명1</p>
					<input type="text" name="nickname" id="nickname"
						value="${loginEnt.entdExplain1}">
				</div>
				<div class='formindiv'>
					<p>제품 설명2</p>
					<input type="text" name="nickname" id="nickname"
						value="${loginEnt.entdExplain2}">
				</div>
				<div class='formindiv'>
					<p>제품 설명3</p>
					<input type="text" name="nickname" id="nickname"
						value="${loginEnt.entdExplain3}">
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