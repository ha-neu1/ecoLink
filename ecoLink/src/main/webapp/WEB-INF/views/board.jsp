<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판</title>
    <link rel="stylesheet" href="/css/board.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="board_area">
        <div class="board_title">
            <div class="page_name">
                <strong>게시판</strong>
            </div>
        </div>
        <hr class="hr_bold">
        <div class="searchBox">
            <input type="text" placeholder="검색어를 입력하세요">
            <button>검색</button>
        </div>
        <button id="addButton">글쓰기</button>
        <br> <br>
        <div class="boardMain">
            <div class="boardList">
                <h3 class="memId">user1</h3>
                <h4 class="boardTitle">첫 번째 게시물</h4>
                <span class="likeButton">좋아요 버튼</span>
                <img class="boardImage" src="/images/logo2.png" alt="게시물 이미지">
                <p class="boardCont">첫 번째 게시물의 내용입니다.</p>
            </div>
            <div class="boardList">
                <h3 class="memId">user2</h3>
                <h4 class="boardTitle">두 번째 게시물</h4>
                <span class="likeButton">좋아요 버튼</span>
                <img class="boardImage" src="/images/logo2.png" alt="게시물 이미지">
                <p class="boardCont">두 번째 게시물의 내용입니다.</p>
            </div>
            <div class="boardList">
                <h3 class="memId">user3</h3>
                <h4 class="boardTitle">세 번째 게시물</h4>
                <span class="likeButton">좋아요 버튼</span>
                <img class="boardImage" src="/images/logo2.png" alt="게시물 이미지">
                <p class="boardCont">세 번째 게시물의 내용입니다.</p>
            </div>
            <!-- 추가된 임시 게시물 데이터 -->
            <div class="boardList">
                <h3 class="memId">user4</h3>
                <h4 class="boardTitle">네 번째 게시물</h4>
                <span class="likeButton">좋아요 버튼</span>
                <img class="boardImage" src="/images/logo2.png" alt="게시물 이미지">
                <p class="boardCont">네 번째 게시물의 내용입니다.</p>
            </div>
            <div class="boardList">
                <h3 class="memId">user5</h3>
                <h4 class="boardTitle">다섯 번째 게시물</h4>
                <span class="likeButton">좋아요 버튼</span>
                <img class="boardImage" src="/images/logo2.png" alt="게시물 이미지">
                <p class="boardCont">다섯 번째 게시물의 내용입니다.</p>
            </div>
            <div class="boardList">
                <h3 class="memId">user6</h3>
                <h4 class="boardTitle">여섯 번째 게시물</h4>
                <span class="likeButton">좋아요 버튼</span>
                <img class="boardImage" src="/images/logo2.png" alt="게시물 이미지">
                <p class="boardCont">여섯 번째 게시물의 내용입니다.</p>
            </div>
        </div>

        <br> <br>
        <div class="page_number">
            <a href="#" class="prev">&lt;</a> <a href="#" class="page">1</a> <a
                href="#" class="page">2</a> <a href="#" class="page">3</a> <a
                href="#" class="page">4</a> <a href="#" class="page">5</a> <a
                href="#" class="next">&gt;</a>
        </div>
    </div>

    <jsp:include page="footer.jsp" />

    <script src="/js/board.js" defer type="module"></script>
</body>
</html>
