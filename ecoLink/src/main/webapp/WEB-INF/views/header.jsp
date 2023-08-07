<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="../js/jquery-3.6.4.min.js"></script>
<script src="js/header.js" defer></script>
<link rel="stylesheet" href="css/header.css">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar">
		<div class="navbarLogo">
			<a href="/main"> <img alt="로고" src="images/logo1.png">
			</a>
		</div>
		<ul class="navbarMenu">
			<li><a href="/introboard">업사이클링</a>
				<ul class="navbarSub">
					<li><a href="/introboard">정 보</a></li>
					<li><a href="/infoboardlist">뉴 스</a></li>
					<li><a href="/infoboardlist">꿀 팁</a></li>
				</ul></li>
			<li><a href="/brandpromolist">브랜드</a>
				<ul class="navbarSub">
					<li><a href="/brandpromolist">브랜드 소개</a></li>
				</ul></li>
			<li><a href="/board">커뮤니티</a>
				<ul class="navbarSub">
					<li><a href="/board">본인제품공유</a></li>
					<li><a href="/board">후기</a></li>
				</ul></li>
		</ul>
		<ul class="navbarProfile">
			<c:choose>
				<c:when test="${not empty user}">
					<li><a href="/userInfo">My page</a></li>
					<li><a href="#" onclick="logout()">로그아웃</a></li>
				</c:when>
				<c:when test="${empty user}">
					<li><a href="/login">로그인</a></li>
					<li><a href="/join">회원가입</a></li>
				</c:when>
			</c:choose>
		</ul>
	</nav>
</body>
</html>