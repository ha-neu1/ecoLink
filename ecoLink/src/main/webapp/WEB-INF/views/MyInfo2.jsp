<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>브랜드 북마크</title>
<link rel="stylesheet" href="css/MyInfo.css">
</head>
<script src="js/jquery-3.6.4.min.js"></script>
<body>
	<%@ include file="header.jsp"%>
	<aside id="sidebar">
		<div id='menus'>
			<ul id='menusList'>
				<li class='menuItem'><a href="/myInfo">MY 정보</a></li>
				<li class='menuItem'><a href="/myBrandLike">브랜드 북마크</a></li>
				<li class='menuItem'><a href="/myBoardLike">좋아요한 글</a></li>
				<li class='menuItem'><a href="/myBoard">내가 쓴 글</a></li>
			</ul>
		</div>
	</aside>
	<div class="containers">
		<article>
			<div class='menuDivs' id='menuDiv2'>
				<h2>브랜드 북마크</h2>
				<div class='container'>
					<c:forEach items="${movieDBlist}" var="movie">
						<span class='likebrand'> <span class='imgcontainer'>
								<a href="#"><img src="#"></a>
						</span>
						</span>
					</c:forEach>
				</div>
			</div>
		</article>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>