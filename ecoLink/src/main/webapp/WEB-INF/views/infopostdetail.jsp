<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dto.BoardCommentDTO"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/infopostdetail.css">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/7aca531ae5.js"
	crossorigin="anonymous"></script>

<script src="js/jquery-3.6.4.min.js"></script>
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
			<c:forEach var="imageUrl" items="${imageUrls}">
				<li><img src="${imageUrl}" alt=""></li>
			</c:forEach>
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
		<form id="boardComment">
			<input type="text" placeholder="댓글을 입력해 주세요." class="comment"
				name="comment">
			<button class="commentbtn">등록</button>
			<input type="hidden" name="boardId" value="${detaildto.boardId}" />
		</form>
	</div>
	<script>
		$(document).ready(function() {
			$('#boardComment ').submit(function(e) {
				e.preventDefault();
				var formData = $(this).serialize();
				$.ajax({
					url : '/insertBoardComment',
					type : 'POST',
					data : formData,
					success : function(response) {
						 $('.comment').val('');
						alert("댓글이 작성되었습니다.");
						
					}, error: function(xhr, status, error) {
	                    if (xhr.status === 401) {
	                        alert("로그인이 필요합니다. 로그인 페이지로 이동합니다.");
	                        window.location.href = '/login'; // Redirect to the login page
	                    } else {
	                        alert("댓글 작성에 실패하였습니다.");
	                    }
	                }
					
				});
			});
		});
	</script>
	<script src="js/infopostdetail.js"></script>





</body>
</html>