<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시물 수정</title>
    <link rel="stylesheet" href="/css/board.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="board_area">
        <div class="board_title">
            <div class="page_name">
                <strong>게시물 수정</strong>
            </div>
        </div>
        <hr class="hr_bold">

        <div class="form_container">
            <form action="/updateBoard" method="post">
                <!-- 게시물 수정 내용 입력 폼 -->
                <input type="hidden" name="boardId" value="${boardDTO.boardId}">
                <label for="boardTitle">제목</label>
                <input type="text" name="boardTitle" value="${boardDTO.boardTitle}">
                <br>
                <label for="boardContents">내용</label>
                <textarea name="boardContents" rows="5">${boardDTO.boardContents}</textarea>
                <br>
                <input type="submit" value="수정">
            </form>
        </div>
    </div>

    <jsp:include page="footer.jsp" />

    <script src="/js/boardUpdate.js" defer type="module"></script>
</body>
</html>
