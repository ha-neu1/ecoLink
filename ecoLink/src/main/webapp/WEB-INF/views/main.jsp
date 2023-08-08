<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="js/jquery-3.6.4.min.js"></script>
<script src="js/banner.js" defer></script>
<link rel="stylesheet" href="css/main.css">
<title>ECOLINK</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%@ include file="chatbot.jsp"%>

	<div class="banner">
		<c:forEach var="banner" items="${banners}">
			<div class="mySlideDiv fade active">
				<img src="${banner.bannerPic}">
			</div>
		</c:forEach>

		<a class="prev" onclick="prevSlide()">&#10094;</a> <a class="next"
			onclick="nextSlide()">&#10095;</a>
	</div>

	<div class="mainIcon">
		<ul>
			<li><a href="/introboard">
					<div class="image-container">
						<img alt="업사이클링 정보" src="images/information.png"> <span
							class="image-text">업사이클링 정보</span>
					</div>
			</a></li>
			<li><a href="/brandpromolist">
					<div class="image-container">
						<img alt="브랜드 소개" src="images/promotion.png"> <span
							class="image-text">브랜드 소개</span>
					</div>
			</a></li>
			<li><a href="/sharepostdetail">
					<div class="image-container">
						<img alt="커뮤니티" src="images/community.png"> <span
							class="image-text">커뮤니티</span>
					</div>
			</a></li>
		</ul>
	</div>

	<div class="statistics">
		<ul>
			<li><strong>${memberCount}</strong> <em>명</em> <span>회원</span></li>
			<div></div>
			<li><strong>${enterCount}</strong> <em>개</em> <span>브랜드</span></li>
			<div></div>
			<li><strong>${boardCount}</strong> <em>개</em> <span>게시글</span></li>
		</ul>
	</div>




	<div class="board_area">
		<div class="board_title">BRANDS</div>
		<div class="boardMain">
			<c:forEach items="${brandlist}" var="brand" varStatus="loop">
				<c:if test="${loop.index < 3}">
					<div class="boardList">
						<div class="titlerate">
							<div class="titlenamediv">
								<a class="titlename"
									href="/brandpromodetail?entCrn=${brand.entCrn }">${brand.memNick}</a>
							</div>
						</div>
						<hr style="margin-top: 3px; margin-bottom: 5px;">
						<a href="/brandpromodetail?entCrn=${brand.entCrn}"><img
							class="boardImage" src="${brand.entdMainPic}"
							onerror="this.onerror=null; this.src='https://buntingmagnetics.com/wp-content/uploads/2015/04/400x300.gif';"></a>
						<hr style="margin-bottom: 5px;">
						<div class="entdShort">
							<p class="boardCont" onclick="opendetail('${brand.entCrn}')">${brand.entdShort}</p>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>

		<div class="board_title">REVIEW</div>
		<div class="boardMain">
			<c:set var="previousBoardId" value="" />
			<c:forEach items="${boardlist}" var="board">
				<c:set var="currentBoardId" value="${board.boardId}" />
				<c:if test="${currentBoardId ne previousBoardId}">
					<div class="boardList">
						<div class="titlerate">
							<div class="titlenamediv">
								<a class="titlename"
									href="/sharepostdetail?boardId=${board.boardId}">${board.boardTitle}</a>
							</div>
						</div>
						<hr style="margin-top: 3px; margin-bottom: 5px;">
						<a href="/sharepostdetail?boardId=${board.boardId}"> <img
							class="boardImage" src="${board.filePath}"
							onerror="this.onerror=null; this.src='https://buntingmagnetics.com/wp-content/uploads/2015/04/400x300.gif';">
						</a>
						<hr style="margin-bottom: 5px;">
						<div class="entdShort">
							<p class="boardCont" onclick="opendetail('${board.boardId}')">${board.boardContents}</p>
						</div>
					</div>
				</c:if>
				<c:set var="previousBoardId" value="${currentBoardId}" />
			</c:forEach>
		</div>
	</div>
	<script type="text/javascript">
		function opendetail(e) {
			location.href = "/brandpromodetail?entCrn=" + e;
		}
		function opendetail(b) {
			location.href = "/sharepostdetail?boardId=" + b;
		}
	</script>



	<%@ include file="footer.jsp"%>
</body>
</html>