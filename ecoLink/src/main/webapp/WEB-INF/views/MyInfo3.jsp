<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>좋아요한 글</title>
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
			<div class='menuDivs' id='menuDiv3'>
				<div class='myinfoheader'>
					<h2>좋아요한 글</h2>
					<p>고객님의 좋아요한 글을 확인하실 수 있습니다.</p>
				</div>
				<div class="board_list">
					<div class="top">
						<div class="num">No.</div>
						<div class="title">제목</div>
						<div class="writer">작성자</div>
						<div class="date">작성일</div>
					</div>
					<div class='likeboard'>
						<c:choose>
							<c:when test="${empty Boardlike}">
								<p>좋아요한 글이 없습니다.</p>
							</c:when>
							<c:otherwise>
								<c:forEach items="${Boardlike}" var="board" varStatus="status">
									<div class="likeboardlist">
										<div class="num" id="boardid">${status.index + 1}</div>
										<div class="title" id="title">
											<c:choose>
												<c:when test="${board.boardType eq 'share'}">
													<a
														href="http://localhost:8070/boardRead?boardId=${board.boardId}"
														style="text-decoration: none; color: black">${board.boardTitle}</a>
												</c:when>
												<c:when test="${board.boardType eq 'review'}">
													<a
														href="http://localhost:8070/reviewRead?boardId=${board.boardId}"
														style="text-decoration: none; color: black">${board.boardTitle}</a>
												</c:when>
											</c:choose>
										</div>
										<div class="writer" id="nickname">${board.memNick }</div>
										<div class="date" id="creatAt">
											<fmt:parseDate value="${board.boardRegtime}"
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
		</article>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>