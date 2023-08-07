<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시물 수정</title>
<link rel="stylesheet" href="/css/board.css">
<script src="https://kit.fontawesome.com/7aca531ae5.js"
	crossorigin="anonymous"></script>
<%@ include file="header.jsp"%>
<%@ include file="chatbot.jsp"%>
</head>
<body>

	<div class="board_area">
		<div class="board_title">
			<div class="page_name">
				<strong>게시물 수정</strong>
			</div>
		</div>
		<hr class="hr_bold">

		<div class="form_container">
			<%-- <form action="/updateBoard" method="post">
				<!-- 게시물 수정 내용 입력 폼 -->
				<input type="hidden" name="boardId" value="${boardDTO.boardId}">
				<label for="boardTitle">제목</label> <input type="text"
					name="boardTitle" value="${boardDTO.boardTitle}"> <br>
				<label for="boardContents">내용</label>
				<textarea name="boardContents" rows="5">${boardDTO.boardContents}</textarea>
				<br> <input type="submit" value="수정">
			</form> --%>
			<form action="/boardUpdate/${boardId}" method="post">
				<input class="tit" type="text" name="title" value="${board.title}" /><br>
				<textarea class="contents" name="content">${board.content}</textarea>
				<br> <input class="button" type="submit" value="수정">
			</form>
		</div>
	</div>

	<script src="/js/boardUpdate.js" defer type="module"></script>
</body>
<footer>
	<%@ include file="footer.jsp"%>
</footer>
</html>
