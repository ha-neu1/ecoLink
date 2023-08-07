<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시물 상세보기</title>
<link rel="stylesheet" href="/css/board.css">
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="board_area">
		<div class="board_title">
			<div class="page_name">
				<strong>게시물 상세보기</strong>
			</div>
		</div>
		<hr class="hr_bold">

		<div class="boardMain">
			<!-- <h3 class="memId">user1</h3>
			<h4 class="boardTitle">첫 번째 게시물의 제목</h4>
			<button class="like_button"></button>
			<img class="boardImage" src="/images/logo2.png" alt="게시물 이미지">
			<p class="boardCont">첫 번째 게시물의 내용입니다.첫 번째 게시물의 내용입니다.첫 번째 게시물의
				내용입니다.첫 번째 게시물의 내용입니다.첫 번째 게시물의 내용입니다.첫 번째 게시물의 내용입니다.</p> -->

			<h3 class="memId">${board.memId}</h3>
			<h4 class="boardTitle">${board.boardTitle}</h4>
			<button class="like_button"></button>
			<p class="boardDate">
				<fmt:parseDate value="${board.boardRegtime}"
					pattern="yyyy-MM-dd HH:mm:ss" var="parsedDate" />
				<fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd" />
			</p>
			<div class="viewCnt">조회수${board.boardViewCount}</div>
			<img class="boardImage" src="${board.filePath}"
				onerror="this.onerror=null; this.src='https://source.unsplash.com/300x225/?beach';">
			<p class="boardCont">${board.boardContents}</p>
		</div>

	</div>

	<jsp:include page="footer.jsp" />

	<script src="/js/boardRead.js" defer type="module"></script>
</body>
</html>
