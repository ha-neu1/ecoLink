<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/infopostdetail.css">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/7aca531ae5.js" crossorigin="anonymous"></script>
<%@ include file="header.jsp" %>
</head>
<body>
	<div class="head_wrap">
        <div class="left_wrap">
            <div class="user_image"><i class="fa-regular fa-user"></i></div>
            <div class="nickname_date_views">
                <div class="nickname">닉네임</div>
                <div class="date_views">
                    <div class="date">몇분전</div>
                    <div class="views"><i class="fa-regular fa-eye"></i>2</div>
                </div>
            </div>            
        </div>
        <div class="right_wrap">
            <div class="like"> <i class="fa-regular fa-heart"></i>2</div>
        </div>
    </div>
    <div class="post_image"> <img src="images/kimbab.jpg"> </div>
    <div class="post_tit">제목제목제목제목제목제목제목제목제목제목제목제목</div>
    <div class="post_contents">내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용</div>
    <div class="post_comment_header">댓글</div>
    <div class="post_comment"><input type="text" placeholder="댓글을 입력해 주세요." class="comment"> <button disabled="disabled" class="commentbtn">등록</button></div>
</body>
</html>