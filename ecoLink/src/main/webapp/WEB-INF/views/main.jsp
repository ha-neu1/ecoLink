<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/main.css">
<title>ECOLINK</title>
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="banner">
		<img alt="배너" src="${bannerPic}">
	</div>
	<div class="mainIcon">
		<ul>
			<li><a href="#"><img alt="정보" src="images/information.png"></a></li>
			<li><a href="#"><img alt="정보" src="images/promotion.png"></a></li>
			<li><a href="#"><img alt="정보" src="images/community.png"></a></li>
		</ul>
	</div>
	<div class="statistics">
	통계
	</div>
	<div class="mainPromo">
	브랜드홍보
	</div>
	<div class="mainReview">
	후기
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>