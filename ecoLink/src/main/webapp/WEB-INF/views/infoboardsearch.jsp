<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/infoboard.css">
<title>Insert title here</title>

<script src="https://kit.fontawesome.com/7aca531ae5.js"
	crossorigin="anonymous"></script>
<script src="js/jquery-3.6.4.min.js"></script>
<%@ include file="header.jsp"%>
<%@ include file="chatbot.jsp"%>
</head>
<body>
	<div class="info_title">NEWS</div>
	<div class="info_cata">
		<div class="view_recent_wrap">
			<select id="select_value" class="view_recent" onchange="ChangeValue(this.value);">
				<option disabled="disabled">정렬기준</option>
				<option value="recent"<c:if test="${param.selectValue == 'recent'}">selected="selected"</c:if>>최신순</option>
				<option value="view"<c:if test="${param.selectValue == 'view'}">selected="selected"</c:if>>조회순</option>
			</select>
		</div>
		
		<div class="info_search">
			<form id="search_form" onsubmit="submitForm(); return false;">
				<select class="search_form" name="item">
					<option value="search_all"<% if ("search_all".equals(request.getParameter("item"))) { %>selected="selected"<% } %>>모두</option>
					<option value="boardTitle"<% if ("boardTitle".equals(request.getParameter("item"))) { %>selected="selected"<% } %>>제목</option>
					<option value="boardContents" <% if ("boardContents".equals(request.getParameter("item"))) { %>selected="selected"<% } %>>내용</option>
				</select> <input type="search" class="searchbar" name="word">
				<button type="submit" class="searchbtn" id="searchbtn">
					<i class="fa-solid fa-magnifying-glass"></i>
				</button>
			</form>
		</div>
	</div>
	<div class="writingform">
		<c:choose>
			<c:when test="${logininfo.memId eq 'admin'}">
				<a href="infowriting"><input class="writing"type="button" value="글쓰기"></a>
			</c:when>
			
		</c:choose>
	</div>
	
			<c:forEach items="${boardList }" var="dto">
				<div class="post_container ">
					<div class="post_image_wrap">
						<a class="post_link" href="infopostdetail?boardId=${dto.boardId }" >
							<div class="post_image">
								<img src="${dto.firstImageUrl}" alt="Image">
							</div>
						</a>
					</div>
					<div class="post_text">
						<h3 class="post_tit">
							<a href="infopostdetail?boardId=${dto.boardId }"> ${dto.boardTitle } </a>
						</h3>
						<div class="post_date_wrap">
							<span class="post_date"> ${dto.boardRegtime }</span>
						</div>
						<div class="post_contents">
							<p>${dto.boardContents }</p>
						</div>
					</div>

				</div>
			</c:forEach>
		
	
	
<ul class="pagination">
		<c:choose>
			<c:when test="${currentCpage == 1}">
				<li class="page-item disabled"><a class="page-link"
					href="//infoboardsearch?page=${currentCpage - 1}&item=${param.item}&word=${param.word}"
					tabindex="-1" aria-disabled="true">이전</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item active">
				<a class="page-link" id="nextPageLink" href="/infoboardsearch?page=${currentCpage - 1}&item=${param.item}&word=${param.word}">이전</a></li>
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${startpage}" end="${endpage}">
			<c:choose>
				<c:when test="${i == currentCpage}">
					<li class="page-item activeNumber"><a class="page-link" id="nextPageLink" href="/infoboardsearch?page=${i}&item=${param.item}&word=${param.word}">${i}</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" id="nextPageLink" href="/infoboardsearch?page=${i}&item=${param.item}&word=${param.word}">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${totalPage == currentCpage}">
				<li class="page-item disabled" id="endNextPage"><a class="page-link" id="nextPageLink" href="/infoboardsearch?page=${currentCpage + 1}&item=${param.item}&word=${param.word}">다음</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item active"><a class="page-link" href="/infoboardsearch?page=${currentCpage + 1}&item=${param.item}&word=${param.word}">다음</a></li>
			</c:otherwise>
		</c:choose>

	</ul>

<script src="js/infoarticle.js" ></script>
</body>
<footer>
	<%@ include file="footer.jsp"%>
</footer>
</html>