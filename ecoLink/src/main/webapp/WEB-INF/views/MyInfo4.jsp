<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 쓴 글</title>
<link rel="stylesheet" href="css/MyInfo.css">
</head>
<script src="js/jquery-3.6.4.min.js"></script>
<script>

</script>
<body>
<%@ include file="header.jsp" %>
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
		<div class='menuDivs' id='menuDiv4'>
		<h2>내가 쓴 글</h2>
			<div class="board_list2">
               <div class="top">
					<div class="num">No.</div>
                   	<div class="title">제목</div>
                   	<div class="date">작성일</div>
               </div>
               <div>
					<div class="num" id="boardid">1</div>
                   	<div class="title" id="title"><a href="#" style="text-decoration:none; color:black">제목</a></div>
                   	<div class="date" id="creatAt">2023-06-30</div>
               </div>
             </div>
		</div>
</article>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>