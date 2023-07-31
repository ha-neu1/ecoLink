// 가상의 게시물 데이터 배열
/*const boardData = [
	{
		memId: "user1",
		boardTitle: "첫 번째 게시물",
		boardDate: "2023-07-20",
		boardCont: "첫 번째 게시물의 내용입니다.첫 번째 게시물의 내용입니다.첫 번째 게시물의 내용입니다.첫 번째 게시물의 내용입니다.첫 번째 게시물의 내용입니다."
	},
	{
		memId: "user2",
		boardTitle: "두 번째 게시물",
		boardDate: "2023-07-19",
		boardCont: "두 번째 게시물의 내용입니다.두 번째 게시물의 내용입니다.두 번째 게시물의 내용입니다.두 번째 게시물의 내용입니다.두 번째 게시물의 내용입니다."
	},
	{
		memId: "user3",
		boardTitle: "세 번째 게시물",
		boardDate: "2023-07-18",
		boardCont: "세 번째 게시물의 내용입니다.세 번째 게시물의 내용입니다.세 번째 게시물의 내용입니다.세 번째 게시물의 내용입니다.세 번째 게시물의 내용입니다."
	},
	{
		memId: "user4",
		boardTitle: "네 번째 게시물",
		boardDate: "2023-07-17",
		boardCont: "네 번째 게시물의 내용입니다.네 번째 게시물의 내용입니다.네 번째 게시물의 내용입니다.네 번째 게시물의 내용입니다.네 번째 게시물의 내용입니다."
	},
	{
		memId: "user5",
		boardTitle: "다섯 번째 게시물",
		boardDate: "2023-07-16",
		boardCont: "다섯 번째 게시물의 내용입니다.다섯 번째 게시물의 내용입니다.다섯 번째 게시물의 내용입니다.다섯 번째 게시물의 내용입니다.다섯 번째 게시물의 내용입니다."
	},
	{
		memId: "user6",
		boardTitle: "여섯 번째 게시물",
		boardDate: "2023-07-15",
		boardCont: "여섯 번째 게시물의 내용입니다.여섯 번째 게시물의 내용입니다.여섯 번째 게시물의 내용입니다.여섯 번째 게시물의 내용입니다.여섯 번째 게시물의 내용입니다."
	},
];*/

// 게시물 목록 렌더링 함수
function renderBoardList() {
  const boardMain = document.querySelector(".boardMain");
  boardMain.innerHTML = ""; 

  boardData.forEach((item, index) => {
    const boardList = document.createElement("div");
    boardList.className = "boardList";

    const memId = document.createElement("h3");
    memId.className = "memId";
    memId.textContent = item.memId;

    const boardTitle = document.createElement("h4");
    boardTitle.className = "boardTitle";
    boardTitle.textContent = item.boardTitle;

    const boardDate = document.createElement("p");
    boardDate.className = "boardDate";
    boardDate.textContent = item.boardDate; 

    const likeButton = document.createElement("button");
    likeButton.className = "like_button";
    likeButton.addEventListener("click", toggleLike);

    const boardImage = document.createElement("img");
    boardImage.className = "boardImage";
    boardImage.src = "/images/logo2.png";
    boardImage.alt = "게시물 이미지";

    const boardCont = document.createElement("p");
    boardCont.className = "boardCont";
    boardCont.textContent = item.boardCont;

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

// 검색 버튼 클릭 이벤트 처리 함수
function searchBoard() {
  // 검색 기능 구현
}

// 페이지 로드 시 게시물 목록 렌더링
document.addEventListener("DOMContentLoaded", function () {
  renderBoardList();

  // 좋아요 버튼 클릭 이벤트 리스너 추가
  const likeButtons = document.querySelectorAll(".like_button");
  likeButtons.forEach((button) => {
    button.addEventListener("click", toggleLike);
  });

  // 검색 버튼 클릭 이벤트 리스너 추가
  const searchButton = document.querySelector(".searchBox button");
  searchButton.addEventListener("click", searchBoard);
});

