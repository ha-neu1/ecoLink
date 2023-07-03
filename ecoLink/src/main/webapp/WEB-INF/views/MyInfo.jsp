<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<link rel="stylesheet" href="css/MyInfo.css">
</head>
<script src="resources/js/jquery-3.6.4.min.js"></script>
<script>
$(document).ready(function(){
	$("#updateUser").on('click',function(){
		location.href = "#"		
	});
</script>
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
	<div class>
		<div>
		<h2>MY 정보</h2>
		<table id='myinfo'>
 			<tr>
				<td>아이디</td>
				<td>id</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>password</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>name</td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td>nickname</td>
			</tr>
			<tr>
				<td>휴대폰번호</td>
				<td>phone</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>email</td>
			</tr>
			<tr>
				<td><button type="button" id="updateUser"><a href="/updateUser">개인정보 수정</a></button>
				<button id="deleteUser">회원 탈퇴</button></td>
			</tr>
		</table>
		</div>
		
		<div>
			<h2>브랜드 북마크</h2>
			<div class='container'>
			<span class='likebrand'>
				<span class='imgcontainer'>
				<a href="#"><img src="#"></a>
				</span>
			</span>
			</div>
		</div>
		
		<div>
			<h2>좋아요한 글</h2>
			<div class="board_list">
               <div class="top">
					<div class="num">No.</div>
                   	<div class="title">제목</div>
                   	<div class="writer">작성자</div>
                   	<div class="date">작성일</div>
               </div>
               <div>
					<div class="num" id="boardid">1</div>
                   	<div class="title" id="title"><a href="#" style="text-decoration:none; color:black">제목</a></div>
                   	<div class="writer" id="nickname">닉네임</div>
                   	<div class="date" id="creatAt">2023-06-30</div>
               </div>
             </div>
		</div>
		
		<div>
		<h2>내가 쓴 글</h2>
			<div class="board_list2">
               <div class="top">
					<div class="num">No.</div>
                   	<div class="title">제목</div>
                   	<div class="date">작성일</div>
               </div>
               <div>
					<div class="num" id="boardid">1</div>
                   	<div class="title" id="title"><a href="#" style="text-decoration:none; color:black">제목</a></div>
                   	<div class="date" id="creatAt">2023-06-30</div>
               </div>
             </div>
		</div>
	</div>
</article>
<%-- <%@ include file="footer.jsp" %> --%>
</body>
</html>