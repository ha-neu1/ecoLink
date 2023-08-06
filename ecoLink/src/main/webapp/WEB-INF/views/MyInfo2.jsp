<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>브랜드 북마크</title>
<link rel="stylesheet" href="css/MyInfo.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" />
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
			<div class='menuDivs' id='menuDiv2'>
				<div class='myinfoheader'>
					<h2>브랜드 북마크</h2>
					<p>고객님의 북마크한 브랜드를 확인하실 수 있습니다.</p>
				</div>
				<div class='container'>
					<c:choose>
						<c:when test="${empty Bookmark}">
							<p>북마크가 없습니다.</p>
						</c:when>
						<c:otherwise>
							<c:forEach items="${Bookmark}" var="enterprise">
								<a
									href="http://localhost:8070/brandpromodetail?entCrn=${enterprise.entCrn}">
									<div class="likebrand">
										<div class="brandTitle">
											<div class='brandName'>${enterprise.memNick}</div>
											<div class="brandStar">
												<span class="fa fa-star star-active"></span>
												${enterprise.avgRate}
											</div>
										</div>
										<img class="brandImage" src="${enterprise.entdMainPic}"
											onerror="this.onerror=null; this.src='https://buntingmagnetics.com/wp-content/uploads/2015/04/400x300.gif';" />
										<div class="entdShort">
											<p class="brandCont">${enterprise.entdShort}
											<p>
										</div>
									</div>
								</a>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<br> <br>
			<div class="page_number">
				<c:choose>
					<c:when test="${currentpPage == 1}">
						<a class="prev disabled" href="/myBrandLike?page=1" tabindex="-1"
							aria-disabled="true">이전</a>
					</c:when>
					<c:otherwise>
						<a class="prev" href="/myBrandLike?page=${currentpPage - 1}">이전</a>
					</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${startpage}" end="${endpage}">
					<c:choose>
						<c:when test="${i == currentpPage}">
							<a class="page active" href="/myBrandLike?page=${i}">${i}</a>
						</c:when>
						<c:otherwise>
							<a class="page" href="/myBrandLike?page=${i}">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${totalPage != currentpPage}">
						<a class="next" href="/myBrandLike?page=${currentpPage + 1}">다음</a>
					</c:when>
					<c:otherwise>
						<a class="next disabled" href="/myBrandLike?page=${endpage + 1}">다음</a>
					</c:otherwise>
				</c:choose>
			</div>

		</article>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>