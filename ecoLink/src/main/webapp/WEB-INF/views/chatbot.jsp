<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/chatbot.css">
<script src="js/jquery-3.6.4.min.js"></script>
<script src="js/chatbot.js"></script>
</head>
<body>
	<a href="#" class="chatIcon"> <img alt="챗봇"
		src="images/chatbot.png">
	</a>

	<div class="chatbox">
		<div class="chatheader">
			<img alt="챗로고" src="images/logo3.png">
		</div>
		<div class="chatlogs" id="response">
			<div class="message bot">
				<div class="message-content">
					안녕하세요! 무엇을 도와드릴까요?<br>ex)메뉴,업사이클링,브랜드,커뮤니티
				</div>
			</div>
			<div class="message user">
				
			</div>
		</div>
		<div class="user-input">
			<input type="text" id="request"> <input type="button"
				value="질문" id="event">
			<input type="button" value="웰컴메시지" id="welcomemsg">
				
		</div>
	</div>
</body>
</html>