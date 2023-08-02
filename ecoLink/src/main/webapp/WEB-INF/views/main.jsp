<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<li>
				<a href="http://localhost:8070/introboard">
					<div class="image-container">
						<img alt="업사이클링 정보" src="images/information.png">
						<span class="image-text">업사이클링 정보</span>
					</div>
				</a>
			</li>
			<li>
				<a href="http://localhost:8070/brandpromolist">
					<div class="image-container">
						<img alt="브랜드 소개" src="images/promotion.png">
						<span class="image-text">브랜드 소개</span>
					</div>
				</a>
			</li>
			<li>
				<a href="http://localhost:8070/board">
					<div class="image-container">
						<img alt="커뮤니티" src="images/community.png">
						<span class="image-text">커뮤니티</span>
					</div>
				</a>
			</li>
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

	<div class="mainPromo">
		<c:forEach var="brand" items="${brandlist}" varStatus="loop">
    <c:if test="${loop.index < 3}">
        <a href="http://localhost:8070/brandpromodetail?entCrn=${brand.entCrn }">
            <div class="card">
                <div class="card__image-holder">
                    <img class="card__image"
                        src="${brand.entdMainPic }" onerror="this.onerror=null; this.src='https://source.unsplash.com/300x225/?beach';" />
                </div>
                <div class="card-title">
                    <h2>
                        ${brand.memNick } <small>${brand.entdURL }</small>
                    </h2>
                </div>
            </div>
        </a>
    </c:if>
</c:forEach>
	</div>

	<div class="boardMain">
		<c:forEach var="board" items="${boardlist}">
			<a href="http://localhost:8070/boardRead?boardId=${board.boardId }">
				<div class="boardList">
					<h3 class="memId">${board.memId}</h3>
					<h4 class="boardTitle">${board.boardTitle}</h4>
					<p class="boardDate">${board.boardRegtime}</p>
					<img class="boardImage"
						src="${board.filePath }" onerror="this.onerror=null; this.src='https://source.unsplash.com/300x225/?beach';">
					<p class="boardCont">${board.boardContents}</p>
				</div>
			</a>
		</c:forEach>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>