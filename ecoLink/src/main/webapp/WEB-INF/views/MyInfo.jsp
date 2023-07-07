<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<link rel="stylesheet" href="css/MyInfo.css">
</head>
<script src="js/jquery-3.6.4.min.js"></script>
<script>
$(document).ready(function() {
	
});

$(document).ready(function(){
	$("#updateUser").on('click',function(){
		location.href = "/myInfo"		
	});
</script>
<body>
<%@ include file="header.jsp" %>
<aside id="sidebar">
	<div id='menus'>
	<ul id='menusList'>
		<li class='menuItem' id='mItem1' value='menuDiv1'>MY 정보</li>
		<li class='menuItem' id='mItem2' value='menuDiv2'>브랜드 북마크</li>
		<li class='menuItem' id='mItem3' value='menuDiv3'>좋아요한 글</li>
		<li class='menuItem' id='mItem4' value='menuDiv4'>내가 쓴 글</li>
	</ul>	
	</div>
</aside>
	<div class="containers">
<article>
		<div class='menuDivs' id='menuDiv1'>
		<h2>MY 정보</h2>
		<table id='myinfo'>
 			<tr>
				<td>아이디</td>
				<td>id</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>********</td>
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
				<td><button type="button" id="updateUser"><a href="/updateMyInfo">개인정보 수정</a></button>
				<button id="deleteUser">회원 탈퇴</button></td>
			</tr>
		</table>
		</div>
		
		<div class='menuDivs' id='menuDiv2'>
			<h2>브랜드 북마크</h2>
			<div class='container'>
			<span class='likebrand'>
				<span class='imgcontainer'>
				<a href="#"><img src="#"></a>
				</span>
			</span>
			</div>
		</div>
		
		<div class='menuDivs' id='menuDiv3'>
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
		
		<div class='menuDivs' id='menuDiv4'>
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
</article>
</div>
<%-- <%@ include file="footer.jsp" %> --%>
</body>
</html>