<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="css/infoboard.css">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/7aca531ae5.js" crossorigin="anonymous"></script>
</head>
<body>

    <div class="info_title">NEWS</div>
    <div class="info_cata">
        <div class="view_recent_wrap">
            <select class="view_recent">
                <option value="recent">최신순</option>
                <option value="views">조회순</option>
            </select>
        </div>
        <div class="info_search">
            <form action="#">
	            <select class="search_form">
	                <option value="seartch_tit">제목</option>
	                <option value="seartch_content">내용</option>
	            </select>
                <input type="search" class="searchbar">
                <button type="submit" class="searchbtn">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </button>
            </form>
        </div>    
    </div>
    <div class="writingform">
    	<a href="infoboardwrite"><input type="button" value="글쓰기" ></a>
    </div>
    <c:forEach items="${boardList }" var="dto">
    <div class="post_container ">
        <div class="post_image_wrap">
            <a class="post_link" href="#"> <div class="post_image"> <img src="images/kimbab.jpg"></div></a>
        </div>
        <div class="post_text">
            <h3 class="post_tit"><a href="#"> 	${dto.boardTitle } </a></h3>
            <div class="post_date_wrap">
                <span class="post_date"> ${dto.boardRegtime }</span>
            </div>
            <div class="post_contents">
                <p>
                    ${dto.boardContents }
                </p>
            </div>
        </div>

    </div>
    </c:forEach>
    
<div class="pagination">
<%
	int totalBoard = (Integer)request.getAttribute("totalBoard");
	int totalPage = 0;
	if(totalBoard % 5 == 0){
		totalPage = totalBoard / 5;
	}
	else{
		totalPage = totalBoard / 5 + 1;
	}
	for(int i= 1; i <= totalPage; i++){
		%>
		
		<a class="pagenumber" href="infoboardlist?page=<%=i%>"><%=i %></a>
		
<% 	}
%>
</div>





 

</body>
</html>