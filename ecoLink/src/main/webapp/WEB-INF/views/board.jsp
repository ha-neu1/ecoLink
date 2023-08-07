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
<title>게시판</title>
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
				<strong>게시판</strong>
			</div>
		</div>
		<hr class="hr_bold">

		<div class="search_container">
			<div class="boardSort">
				<select id="sortSelect">
					<option value="latest">최신순</option>
					<option value="recommend">추천순</option>
				</select>
			</div>
			<div class="searchBox">
				<input type="text" placeholder="검색어를 입력하세요">
				<button class="button">검색</button>
				<a href="/boardCreate"><button class="button">글쓰기</button></a>
			</div>
		</div>
		<div class="boardMain">
			<c:forEach var="board" items="${boardlist}" varStatus="loop">
				<div class="boardList">
					<a href="http://localhost:8070/boardRead?boardId=${board.boardId}">
						<h3 class="memId">${board.memId}</h3>
						<h4 class="boardTitle">${board.boardTitle}</h4>
					</a>
					<button class="like_button"></button>
					<a href="http://localhost:8070/boardRead?boardId=${board.boardId}">
						<p class="boardDate">
							<fmt:parseDate value="${board.boardRegtime}"
								pattern="yyyy-MM-dd HH:mm:ss" var="parsedDate" />
							<fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd" />
						</p> <img class="boardImage" src="${board.filePath}"
						onerror="this.onerror=null; this.src='https://source.unsplash.com/300x225/?beach';">
						<p class="boardCont">${board.boardContents}</p>
					</a>
				</div>
			</c:forEach>
		</div>

		<br> <br>
		<div class="page_number">
			<a href="#" class="prev">&lt;</a> <a href="#" class="page">1</a> <a
				href="#" class="page">2</a> <a href="#" class="page">3</a> <a
				href="#" class="page">4</a> <a href="#" class="page">5</a> <a
				href="#" class="next">&gt;</a>
		</div>
	</div>

</body>
<footer>
	<%@ include file="footer.jsp"%>
</footer>
</html>
