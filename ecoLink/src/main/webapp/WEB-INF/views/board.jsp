<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시판</title>
<%@ include file="header.jsp" %>
<script src="/js/board.js" defer type="module"></script>
<link rel="stylesheet" href="/css/board.css">
</head>
<body>
	<header></header>
	<div class="board_area">
		<div class="board_title">
			<div class="page_name">
				<strong>게시판</strong>
			</div>
		</div>
		<hr class="hr_bold">
		<div class="searchBox">
			<input type="text" placeholder="검색어를 입력하세요">
			<button>검색</button>
		</div>
		<button id="addButton">글쓰기</button>
		<br> <br>
		<div class="boardMain">
			<div class="boardList">
				<h3 class="memId">작성자 이름</h3>
				<h4 class="boardTitle">게시물 제목</h4>
				<span class="likeButton"></span> <img class="boardImage"
					src="/images/logo2.png" alt="게시물 이미지">
				<p class="boardCont">본문 내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.본문
					내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.</p>
			</div>

			<div class="boardList">
				<h3 class="memId">작성자 이름</h3>
				<h4 class="boardTitle">게시물 제목</h4>
				<span class="likeButton"></span> <img class="boardImage"
					src="/images/logo2.png" alt="게시물 이미지">
				<p class="boardCont">본문 내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.본문
					내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.</p>
			</div>

			<div class="boardList">
				<h3 class="memId">작성자 이름</h3>
				<h4 class="boardTitle">게시물 제목</h4>
				<span class="likeButton"></span> <img class="boardImage"
					src="/images/logo2.png" alt="게시물 이미지">
				<p class="boardCont">본문 내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.본문
					내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.</p>
			</div>

			<div class="boardList">
				<h3 class="memId">작성자 이름</h3>
				<h4 class="boardTitle">게시물 제목</h4>
				<span class="likeButton"></span> <img class="boardImage"
					src="/images/logo2.png" alt="게시물 이미지">
				<p class="boardCont">본문 내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.본문
					내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.</p>
			</div>
		</div>

		<br> <br>
		<div class="page_number">
			<a href="#" class="prev">&lt;</a>
			<a href="#" class="page">1</a>
			<a href="#" class="page">2</a>
			<a href="#" class="page">3</a>
			<a href="#" class="page">4</a>
			<a href="#" class="page">5</a>
			<a href="#" class="next">&gt;</a>
		</div>
	</div>
	<footer></footer>
</body>
</html>