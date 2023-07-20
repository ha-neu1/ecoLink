<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<link rel="stylesheet" href="css/MyInfoUpdate.css">
</head>
<script src="js/jquery-3.6.4.min.js"></script>
<script>
	$(document).ready(
			function() {
				$("#updateBtn").on(
						'click',
						function() {
							$.ajax({
								url : 'updateMyInfo',
								data : {
									'memId' : "${loginUser.memId}",
									'pw' : $("#pw").val(),
									'email' : $("#email").val()
								},
								type : 'post',
								success : function(res) {
									alert("회원정보 수정이 완료되었습니다.");
									location.href = "/myInfo"
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
		<!-- <div id='menus'>
			<ul id='menusList'>
				<li class='menuItem'><a href="/myInfo">MY 정보</a></li>
				<li class='menuItem'><a href="/myBrandLike">브랜드 북마크</a></li>
				<li class='menuItem'><a href="/myBoardLike">좋아요한 글</a></li>
				<li class='menuItem'><a href="/myBoard">내가 쓴 글</a></li>
			</ul>
		</div> -->
	</aside>
	<div class="containers">
		<article>
			<form name="signUpForm">
				<h2>개인정보 수정</h2>
				<p>고객님의 개인정보에 포함된 닉네임과 비밀번호를 수정하실 수 있습니다.</p>
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
					<p>이름</p>
					<input type="text" name="name" id="name"
						value="${loginUser.memName}" disabled>
				</div>

				<div class='formindiv'>
					<p>닉네임</p>
					<input type="text" name="nickname" id="nickname"
						value="${loginUser.memNick}">
				</div>

				<div class='formindiv'>
					<p>E-mail</p>
					<input type="tel" name="email" id="email"
						value="${loginUser.memEmail}" disabled>
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