<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시판</title>
<link rel="stylesheet" href="/css/board.css">
</head>
<body>
	<jsp:include page="header.jsp" />

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
				<button>검색</button>
				<a href="/boardCreate" id="addButton"><button>글쓰기</button></a>
			</div>
		</div>

		<div class="boardMain"></div>
		<c:forEach items="${boardList}" var="board">
			<div class="boardList">
				<h3 class="memId">${board.memId}</h3>
				<h4 class="boardTitle">${board.boardTitle}</h4>
				<p class="boardDate">${board.boardDate}</p>
				<button class="like_button" onclick="toggleLike(event)"></button>
				<img class="boardImage" src="${board.boardImageUrl}" alt="게시물 이미지">
				<p class="boardCont">${board.boardContents}</p>
			</div>
		</c:forEach>

		<br> <br>
		<div class="page_number">
			<a href="#" class="prev">&lt;</a> <a href="#" class="page">1</a> <a
				href="#" class="page">2</a> <a href="#" class="page">3</a> <a
				href="#" class="page">4</a> <a href="#" class="page">5</a> <a
				href="#" class="next">&gt;</a>
		</div>
	</div>

	<jsp:include page="footer.jsp" />

	<script src="/js/board.js" defer type="module"></script>
</body>
</html>
