<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항</title>
    <script src="/js/board.js" defer type="module"></script>
    <link rel="stylesheet" href="/css/board.css">
</head>
<body>
    <header></header>
    <div class="board_area">
        <div class="board_title">
            <div class="page_name"><strong>공지사항</strong></div>
            <input type="button" onclick="location.href='/boardCreate'" value="글쓰기">
        </div>
        <hr class="hr_bold">
        <table id="list" class="post_list">
            <th>번호</th>
            <th>제목</th>
            <th>작성일</th>
            <th>조회수</th>
        </table>
        <br>
        <br>
        <div class="page_number">
            <a href="/board">&lt;</a>
            <a href="/board">1</a>
            <a href="/board">2</a>
            <a href="/board">3</a>
            <a href="/board">4</a>
            <a href="/board">5</a>
            <a href="/board">&gt;</a>
        </div>
    </div>
    <footer></footer>
</body>
</html>