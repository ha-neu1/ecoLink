let boardData = []; // 게시물 데이터를 저장할 배열

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

    // 좋아요 버튼 클릭 이벤트 리스너 추가
    const likeButtons = document.querySelectorAll(".like_button");
    likeButtons.forEach((button) => {
        button.addEventListener("click", toggleLike);
    });
});
