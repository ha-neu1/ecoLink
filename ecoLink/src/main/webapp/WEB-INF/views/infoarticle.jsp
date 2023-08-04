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
			<form action="infoboardsearch">
				<select class="search_form" name="item" >
					<option value="search_all"<c:if test="${param.selectValue == 'search_all'}">selected="selected"</c:if>>모두</option>
					<option value="boardTitle"<c:if test="${param.selectValue == 'boardTitle'}">selected="selected"</c:if>>제목</option>
					<option value="boardContents"<c:if test="${param.selectValue == 'boardContents'}">selected="selected"</c:if>>내용</option>
				</select> <input type="search" class="searchbar" name="word">
				<button type="submit" class="searchbtn">
					<i class="fa-solid fa-magnifying-glass"></i>
				</button>
			</form>
		</div>
	</div>
	<div class="writingform">
		<c:choose>
			<c:when test="${logininfo.memId eq 'admin'}">
				<a href="infowriting"><input type="button" value="글쓰기"></a>
			</c:when>
			
		</c:choose>
	</div>
	
			<c:forEach items="${boardList }" var="dto">
				<div class="post_container ">
					<div class="post_image_wrap">
						<a class="post_link" href="/infopostdetail?boardId=${dto.boardId}">
							<div class="post_image">
								<img src="${dto.firstImageUrl}" alt="Image">
							</div>
						</a>
					</div>
					<div class="post_text">
						<h3 class="post_tit">
							<a href="/infopostdetail?boardId=${dto.boardId}">${dto.boardTitle}</a>
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
		
	
	

<div class="pagination">
    <%
    Integer totalBoard = (Integer) request.getAttribute("totalBoard");
    if (totalBoard != null) {
        int totalPage = (totalBoard % 5 == 0) ? totalBoard / 5 : totalBoard / 5 + 1;

        for (int i = 1; i <= totalPage; i++) {
    %>
    <a class="pagenumber" href="infoboardlist?page=<%=i%>&selectValue=${param.selectValue}"><%=i%></a>
    <%
        }
    }
    %>
</div>







</body>
</html>