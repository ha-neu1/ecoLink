<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항</title>
    <script src="/js/boardCreate.js" defer type="module"></script>
    <link rel="stylesheet" href="/css/board.css">
    <script>
        
    </script>
</head>
<body>
    <header></header>
    <div class="board_area">
        <div class="board_title">
            <div class="page_name"><strong>공지사항</strong></div>
            <input id="post_save" type="button" value="저장">
        </div>
        <hr class="hr_bold">
        <input id="post_name" class="post_area" type="text" placeholder="제목을 입력해 주세요.">
        <textarea id="post_cont" class="post_area" id="post_writer" cols="30" rows="10" placeholder="내용을 입력해 주세요."></textarea>
    </div>
    <footer></footer>
</body>
</html>

