<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dto.BoardCommentDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/infopostdetail.css">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/7aca531ae5.js"
	crossorigin="anonymous"></script>


<%@ include file="header.jsp"%>
</head>
<body>
	<div class="head_wrap">
		<div class="left_wrap">
			<div class="user_image">
				<i class="fa-regular fa-user"></i>
			</div>
			<div class="nickname_date_views">
				<div class="nickname">닉네임</div>
				<div class="date_views">
					<div class="date" name="boardRegtime">${detaildto.boardRegtime}</div>
					<div class="views">
						<i class="fa-regular fa-eye"></i>${detaildto.boardViewCount}</div>
				</div>
			</div>
		</div>
		<div class="right_wrap">
			<div class="like">
				<i class="fa-regular fa-heart"></i>2
			</div>
		</div>
	</div>
	<div class="post_image">
		<ul class="slides">
			<li><img src="images/kimbab.jpg" alt=""></li>
			<li><img src="images/logo1.png" alt=""></li>
			<li><img src="images/promotion.png" alt=""></li>
		</ul>
		<p class="controller">

			<!-- &lang: 왼쪽 방향 화살표
      &rang: 오른쪽 방향 화살표 -->
			<span class="prev">&lang;</span> <span class="next">&rang;</span>
		</p>
	</div>
	<div class="post_tit" name="boardTitle">${detaildto.boardTitle }</div>
	<div class="post_contents" name="boardContents">${detaildto.boardContents }</div>
	<div class="post_comment_header">댓글</div>
	<div class="post_comment">
		<input type="text" placeholder="댓글을 입력해 주세요." class="comment">
		<button class="commentbtn" onclick="submitComment()">등록</button>
	</div>
	<div class="comment_list">
		<!-- 댓글 목록이 표시될 영역 -->
		
	</div>
	
	<script src="js/infopostdetail.js"></script>
</body>
</html>