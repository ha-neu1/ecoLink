let boardData = []; // 게시물 데이터를 저장할 배열

// 게시물 목록 렌더링 함수
function renderBoardList() {
    const boardMain = document.querySelector(".boardMain");
    boardMain.innerHTML = "";

    boardData.forEach((item, index) => {
        const boardList = document.createElement("div");
        boardList.className = "boardList";

        const memId = document.createElement("h3");
        memId.className = "memId";
        memId.textContent = "작성자: " + item.memId; // 작성자 정보 출력

        const boardTitle = document.createElement("h4");
        boardTitle.className = "boardTitle";
        boardTitle.textContent = item.boardTitle; // 게시물 제목 출력

        const boardDate = document.createElement("p");
        boardDate.className = "boardDate";
        boardDate.textContent = item.boardRegtime; // 게시물 등록일 출력

        const likeButton = document.createElement("button");
        likeButton.className = "like_button";
        likeButton.addEventListener("click", toggleLike);

        const boardImage = document.createElement("img");
        boardImage.className = "boardImage";
        boardImage.src = item.firstImageUrl;
        boardImage.alt = "게시물 이미지";

        const boardCont = document.createElement("p");
        boardCont.className = "boardCont";
        boardCont.textContent = item.boardContents;

        boardList.appendChild(memId);
        boardList.appendChild(boardTitle);
        boardList.appendChild(boardDate);
        boardList.appendChild(likeButton);
        boardList.appendChild(boardImage);
        boardList.appendChild(boardCont);

        boardMain.appendChild(boardList);
    });
}

// 좋아요 버튼 클릭 이벤트 처리 함수
function toggleLike(event) {
    const likeButton = event.target;
    const liked = likeButton.classList.toggle("liked");
    if (liked) {
        likeButton.style.backgroundImage = "url('/images/likebutton_on.png')";
    } else {
        likeButton.style.backgroundImage = "url('/images/likebutton_off.png')";
    }
}

// 페이지 로드 시 게시물 목록 렌더링
document.addEventListener("DOMContentLoaded", function () {

    boardData = [
        /*{
            memId: "user1",
            boardTitle: "게시물 1",
            boardRegtime: "2023-07-20",
            firstImageUrl: "image1.jpg",
            boardContents: "게시물 1 내용입니다.",
        },
        {
            memId: "user2",
            boardTitle: "게시물 2",
            boardRegtime: "2023-07-21",
            firstImageUrl: "image2.jpg",
            boardContents: "게시물 2 내용입니다.",
        },*/

    ];

    renderBoardList();

    // 좋아요 버튼 클릭 이벤트 리스너 추가
    const likeButtons = document.querySelectorAll(".like_button");
    likeButtons.forEach((button) => {
        button.addEventListener("click", toggleLike);
    });
});
