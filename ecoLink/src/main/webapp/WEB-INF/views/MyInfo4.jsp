<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 쓴 글</title>
<link rel="stylesheet" href="css/MyInfo.css">
</head>
<script src="js/jquery-3.6.4.min.js"></script>
<body>
	<%@ include file="header.jsp"%>

	<div class="containers">
		<article>
			<aside id="sidebar">
				<div id='menus'>
					<ul id='menusList'>
						<li class='menuItem'><a href="/userInfo">MY 정보</a></li>
						<li class='menuItem'><a href="/myBrandLike">브랜드 북마크</a></li>
						<li class='menuItem'><a href="/myBoardLike">좋아요한 글</a></li>
						<li class='menuItem'><a href="/myBoard">내가 쓴 글</a></li>
					</ul>
				</div>
			</aside>
			<div class='menuDivs' id='menuDiv4'>
				<div class='myinfoheader'>
					<h2>내가 쓴 글</h2>
					<p>고객님의 작성글을 확인하실 수 있습니다.</p>
				</div>
				<div class="board_list2">
					<div class="top">
						<div class="num">No.</div>
						<div class="title">제목</div>
						<div class="date">작성일</div>
					</div>
					<div class='myboard'>
						<c:choose>
							<c:when test="${empty MyBoard}">
								<p>내가 쓴 글이 없습니다.</p>
							</c:when>
							<c:otherwise>
								<c:forEach items="${MyBoard}" var="myboard" varStatus="status">
									<div class="myboardlist">
										<div class="num" id="boardid">${status.index + 1 + (currentpPage - 1) * 10}</div>
										<div class="title" id="title">
											<c:choose>
												<c:when test="${myboard.boardType eq 'share'}">
													<a href="http://localhost:8070/boardRead?boardId=${myboard.boardId}">${myboard.boardTitle}</a>
												</c:when>
												<c:when test="${myboard.boardType eq 'review'}">
													<a href="http://localhost:8070/reviewRead?boardId=${myboard.boardId}">${myboard.boardTitle}</a>
												</c:when>
												<c:when test="${myboard.boardType eq 'news'}">
													<a href="http://localhost:8070/infopostdetail?boardId=${myboard.boardId}">${myboard.boardTitle}</a>
												</c:when>
												<c:when test="${myboard.boardType eq 'tip'}">
													<a href="http://localhost:8070/infopostdetail?boardId=${myboard.boardId}">${myboard.boardTitle}</a>
												</c:when>
											</c:choose>
										</div>
										<div class="date" id="creatAt">
											<fmt:parseDate value="${myboard.boardRegtime}"
												pattern="yyyy-MM-dd HH:mm:ss" var="parsedDate" />
											<fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd" />
										</div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>

			<br> <br>
			<div class="page_number">
				<c:choose>
					<c:when test="${currentpPage == 1}">
						<a class="prev disabled" href="/myBoard?page=1" tabindex="-1"
							aria-disabled="true">이전</a>
					</c:when>
					<c:otherwise>
						<a class="prev" href="/myBoard?page=${currentpPage - 1}">이전</a>
					</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${startpage}" end="${endpage}">
					<c:choose>
						<c:when test="${i == currentpPage}">
							<a class="page active" href="/myBoard?page=${i}">${i}</a>
						</c:when>
						<c:otherwise>
							<a class="page" href="/myBoard?page=${i}">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${totalPage != currentpPage}">
						<a class="next" href="/myBoard?page=${currentpPage + 1}">다음</a>
					</c:when>
					<c:otherwise>
						<a class="next disabled" href="/myBoard?page=${endpage + 1}">다음</a>
					</c:otherwise>
				</c:choose>
			</div>
		</article>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>