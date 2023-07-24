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

        <div class="search_container">
            <div class="searchBox">
                <select>
                    <option value="latest">최신순</option>
                    <option value="recommend">추천순</option>
                </select> <input type="text" placeholder="검색어를 입력하세요">

                <button>검색</button>
            </div>
            <button id="addButton">글쓰기</button>
        </div>

        <div class="boardMain">
            <c:forEach var="post" items="${boardData}">
                <div class="boardList">
                    <h3>${post.boardTitle}</h3>
                    <p class="boardDate">${post.boardDate}</p>
                    <p>${post.boardContent}</p>
                    <button class="like_button"></button>
                </div>
            </c:forEach>
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
