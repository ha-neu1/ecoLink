<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
				<li><a href="#">업사이클링</a>
					<ul class="navbarSub">
						<li><a href="#">정보</a></li>
						<li><a href="#">뉴스</a></li>
					</ul>
				</li>
				<li><a href="#">브랜드</a>
					<ul class="navbarSub">
						<li><a href="#">브랜드 소개</a></li>
					</ul>
				</li>
				<li><a href="#">커뮤니티</a>
					<ul class="navbarSub">
						<li><a href="#">본인제품공유</a></li>
						<li><a href="#">후기</a></li>
					</ul>
				</li>
			</ul>
		<ul class="navbarProfile">
			<c:choose>
				<c:when test="${not empty user}">
					<li><a href="#">My page</a></li>
					<li><a href="#">로그아웃</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="#">로그인</a></li>
					<li><a href="#">회원가입</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
</body>
</html>