<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/infoboard.css">
<title>Insert title here</title>
<script src="js/infoarticle.js" ></script>
<script src="https://kit.fontawesome.com/7aca531ae5.js"
	crossorigin="anonymous"></script>
<script src="js/jquery-3.6.4.min.js"></script>
<%@ include file="header.jsp"%>
</head>
<script>
    function submitForm() {
        var selectElement = document.getElementById("search_form");
        var selectedValue = selectElement.options[selectElement.selectedIndex].value;

        var inputValue = document.getElementsByName("word")[0].value;

        var form = document.getElementById("search_form");
        var pageUrl = "infoboardsearch?page=1&item=" + selectedValue + "&word=" + inputValue;
        form.action = pageUrl;
    }
</script>
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
					<option value="seartch_all"<% if ("search_all".equals(request.getParameter("item"))) { %>selected="selected"<% } %>>모두</option>
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
		<a href="infowriting"><input type="button" value="글쓰기"></a>
	</div>
	
			<c:forEach items="${boardList }" var="dto">
				<div class="post_container ">
					<div class="post_image_wrap">
						<a class="post_link" href="#">
							<div class="post_image">
								<img src="images/kimbab.jpg">
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
		
	
	
<%
    int totalBoard = (Integer) request.getAttribute("totalBoard");
    int totalPage = 0;

    if (totalBoard % 5 == 0) {
        totalPage = totalBoard / 5;
    } else {
        totalPage = totalBoard / 5 + 1;
    }
%>

<div class="pagination">
    <%-- 페이지 링크 생성 --%>
    <% for (int i = 1; i <= totalPage; i++) { %>
        <%-- 페이지 URL 생성 --%>
        <% String selectedValue = request.getParameter("item"); %>
        <% String inputValue = request.getParameter("word"); %>
        <% String pageUrl = "infoboardsearch?page=" + i + "&item=" + selectedValue + "&word=" + inputValue; %>
        <a class="pagenumber" href="<%= pageUrl %>"><%= i %></a>
    <% } %>
</div>






</body>
</html>