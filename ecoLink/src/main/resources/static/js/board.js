// 가상의 게시물 데이터 배열
const boardData = [
  {
    memId: "user1",
    boardTitle: "첫 번째 게시물",
  },
  {
    memId: "user2",
    boardTitle: "두 번째 게시물",
  },
  {
    memId: "user3",
    boardTitle: "세 번째 게시물",
  },
  {
    memId: "user4",
    boardTitle: "네 번째 게시물",
  },
  {
    memId: "user5",
    boardTitle: "다섯 번째 게시물",
  },
  {
    memId: "user6",
    boardTitle: "여섯 번째 게시물",
  },
];

// 게시물 목록 렌더링 함수
function renderBoardList() {
  const boardContainer = document.querySelector(".boardMain");
  boardContainer.innerHTML = ""; // 이전에 렌더링된 요소들 초기화

  // 각 게시물을 순회하면서 게시물 항목들을 생성하여 boardContainer에 추가
  boardData.forEach((item) => {
    const boardItem = document.createElement("div");
    boardItem.classList.add("boardList");

    const memId = document.createElement("h3");
    memId.classList.add("memId");
    memId.innerText = item.memId;

    const boardTitle = document.createElement("h4");
    boardTitle.classList.add("boardTitle");
    boardTitle.innerText = item.boardTitle + '의 제목';

    const likeButton = document.createElement("button");
    likeButton.classList.add("like_button");
    likeButton.style.backgroundImage = "url('/images/likebutton_off.png')";

    const boardImage = document.createElement("img");
    boardImage.classList.add("boardImage");
    boardImage.src = "/images/logo2.png";
    boardImage.alt = "게시물 이미지";

    const boardCont = document.createElement("p");
    boardCont.classList.add("boardCont");
    boardCont.innerText = "게시물 내용입니다. 게시물 내용입니다. 게시물 내용입니다. 게시물 내용입니다. ";

    boardItem.appendChild(memId);
    boardItem.appendChild(boardTitle);
    boardItem.appendChild(likeButton);
    boardItem.appendChild(boardImage);
    boardItem.appendChild(boardCont);

    boardContainer.appendChild(boardItem);
  });
}

// 페이지 로드 시 게시물 목록 렌더링
document.addEventListener("DOMContentLoaded", renderBoardList);
