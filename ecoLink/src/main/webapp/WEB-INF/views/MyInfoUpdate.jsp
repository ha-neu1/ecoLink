<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/MyInfoUpdate.css">
</head>
<script src="resources/js/jquery-3.6.4.min.js"></script>
<body>
<%@ include file="header.jsp" %>
<aside id="sidebar">
	<div>
		<p>MY 정보</p>
		<p>브랜드 북마크</p>
		<p>좋아요한 글</p>
		<p>내가 쓴 글</p>
	</div>
</aside>
<article>
		<form name="signUpForm">
		<h2>개인정보 수정</h2>
		<p>고객님의 닉네임과 연락처 등 개인정보를 수정하실 수 있습니다.</p>
			<div class='formindiv'>
				<p>아이디(수정불가)</p>
				<div id=id_div>
					<input type="text" name="userid" id="userid" value="아이디" maxlength="16" readonly>
				</div>
			</div>

			<div class='formindiv'>
				<p>비밀번호</p>
				<label><input type="password" name="pw" id="pw" value="비밀번호" maxlength="16"></label>
			</div>

			<div class='formindiv'>
				<p>이름(수정불가)</p>
				<input type="text" name="name" id="name" value="이름" readonly>
			</div>

			<div class='formindiv'>
				<p>닉네임</p>
				<input type="text" name="nickname" id="nickname" value="닉네임">
			</div>
			
			<div class='formindiv'>
				<p>휴대폰번호</p>
				<input type="tel" name="phone" id="phone" value="전화번호">
			</div>

			<div class='formindiv'>
				<p>이메일(수정불가)</p>
				<input type="tel" name="email" id="email" value="이메일" readonly>
			</div>

			<div class='formindiv'>
				<button type="button" id="UserUpdateButton">나의정보 수정</button>
			</div>

		</form>
	</article>
</body>
</html>