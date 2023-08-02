<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시물 작성</title>
    <link rel="stylesheet" href="/css/board.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="board_area">
        <div class="board_title">
            <div class="page_name">
                <strong>게시물 작성</strong>
            </div>
        </div>
        <hr class="hr_bold">

        <form id="createBoardForm" enctype="multipart/form-data">
            <div class="input_box">
                <label for="boardTitle" hidden>제목</label>
                <input type="text" id="boardTitle" name="boardTitle" class="input_text" placeholder="제목을 입력해주세요">
            </div>
            <div class="input_box">
                <label for="boardImage">이미지 첨부</label>
                <br>
                <input type="file" id="boardImage" name="boardImage" accept="image/*">
                <br>
                <img id="preview" src="/images/logo2.png" alt="미리보기 이미지" class="preview_image">
            </div>
            <div class="input_box">
                <label for="boardContents" hidden>내용</label>
                <textarea id="boardContents" name="boardContents" class="input_textarea" placeholder="내용을 입력해주세요"></textarea>
            </div>
            <div class="input_box">
                <button type="submit" id="submitBoardButton" class="submit_button">작성</button>
            </div>
        </form>
    </div>

    <jsp:include page="footer.jsp" />

    <script src="/js/boardCreate.js" defer type="module"></script>
</body>
</html>
