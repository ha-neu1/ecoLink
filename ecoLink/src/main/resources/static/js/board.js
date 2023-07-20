document.addEventListener("DOMContentLoaded", function () {
    // 게시물 데이터
    const boardData = [
        {
            memId: "user1",
            boardTitle: "첫 번째 게시물",
            boardContents: "첫 번째 게시물의 내용입니다.",
            boardImage: "/images/logo2.png"
        },
        {
            memId: "user2",
            boardTitle: "두 번째 게시물",
            boardContents: "두 번째 게시물의 내용입니다.",
            boardImage: "/images/logo2.png"
        },
        {
            memId: "user3",
            boardTitle: "세 번째 게시물",
            boardContents: "세 번째 게시물의 내용입니다.",
            boardImage: "/images/logo2.png"
        },
        {
            memId: "user4",
            boardTitle: "네 번째 게시물",
            boardContents: "네 번째 게시물의 내용입니다.",
            boardImage: "/images/logo2.png"
        },
        {
            memId: "user5",
            boardTitle: "다섯 번째 게시물",
            boardContents: "다섯 번째 게시물의 내용입니다.",
            boardImage: "/images/logo2.png"
        },
        {
            memId: "user6",
            boardTitle: "여섯 번째 게시물",
            boardContents: "여섯 번째 게시물의 내용입니다.",
            boardImage: "/images/logo2.png"
        },
    ];

    const boardListContainer = document.getElementById("boardListContainer");

    function renderBoardList() {
        let boardListHTML = "";

        for (let i = 0; i < boardData.length; i++) {
            const board = boardData[i];

            const boardItemHTML = `
                <div class="boardList">
                    <h3 class="memId">${board.memId}</h3>
                    <h4 class="boardTitle">${board.boardTitle}</h4>
                    <span class="likeButton">좋아요 버튼</span>
                    <img class="boardImage" src="${board.boardImage}" alt="게시물 이미지">
                    <p class="boardCont">${board.boardContents}</p>
                </div>
            `;

            boardListHTML += boardItemHTML;
        }

        boardListContainer.innerHTML = boardListHTML;
    }

    // 페이지 로딩 시 게시물 리스트를 생성
    renderBoardList();
});
