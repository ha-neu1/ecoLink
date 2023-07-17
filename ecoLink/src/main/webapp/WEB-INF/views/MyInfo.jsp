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
$(document).ready(function(){
	$("#updateUser").on('click',function(){
		location.href = "/updateMyInfo"		
	});
	
	$("#deleteUser").on('click',function(){
		if(confirm("정말로 탈퇴하시겠습니까?")){
			$.ajax({
				url:'deleteMember',
				data: {
					'memId':"${memId}"
				},
				type:'get',
				success:function(res){
					alert("회원 탈퇴 되었습니다.");
					location.href = "/logout";
				},
				error:function(request,status,e){
					alert("코드="+request.status+"\n메시지="+request.responseText+"\nerror="+e);
				}
			});
		}else{
			alert("회원탈퇴를 취소합니다.");
		}
		
	});
	
});
</script>
<body>
<%@ include file="header.jsp" %>
<aside id="sidebar">
	<div id='menus'>
	<ul id='menusList'>
		<li class='menuItem'><a href="/myInfo">MY 정보</a></li>
		<li class='menuItem'><a href="/myBrandLike">브랜드 북마크</a></li>
		<li class='menuItem'><a href="/myBoardLike">좋아요한 글</a></li>
		<li class='menuItem'><a href="/myBoard">내가 쓴 글</a></li>
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
				<td>${loginUser.memId}</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>********</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${loginUser.memName}</td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td>${loginUser.memNick}</td>
			</tr>
			<tr>
				<td>E-mail</td>
				<td>${loginUser.memEmail}</td>
			</tr>
			<tr>
				<td><button type="button" id="updateUser">개인정보 수정</button>
				<button id="deleteUser">회원 탈퇴</button></td>
			</tr>
		</table>
		</div>
</article>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>