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
<title>게시물 상세보기</title>
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
			<c:if test="${board.memId eq user.memId}">
			<!-- 로그인한 사용자와 게시물 작성자가 같을 경우에만 표시 -->
			<button class="button" onclick="boardUpdate(${board.boardId})">수정</button>
			<button class="button" onClick="deleteConfirmation(${board.boardId})">삭제</button>
			</c:if>
			<img class="boardImage" src="${board.filePath}"
				onerror="this.onerror=null; this.src='https://source.unsplash.com/300x225/?beach';">
			<p class="boardCont">${board.boardContents}</p>
		</div>

	</div>

	<script src="/js/board.js" defer type="module"></script>
	<script>
	function boardUpdate(boardId) {
	    if (boardId != 0) {
	    	window.location.href = "/boardUpdate/" + boardId;
	    } else {
	        alert("게시물 ID가 유효하지 않습니다.");
	    }
	}

	function deleteConfirmation(boardId) {
	    if (confirm("게시물을 삭제하시겠습니까?")) {
	        deleteBoard(boardId);
	    }
	}

	function deleteConfirmation(boardId) {
	    if (confirm("게시물을 삭제하시겠습니까?")) {
	        $.ajax({
	            type: "POST",
	            url: "/deleteBoard/" + boardId,
	            success: function () {
	                alert("게시물이 삭제되었습니다.");
	                location.href = "/board";
	            },
	            error: function () {
	                alert("게시물 삭제에 실패했습니다.");
	            }
	        });
	    }
	}

</script>
</body>
<footer>
	<%@ include file="footer.jsp"%>
</footer>
</html>
