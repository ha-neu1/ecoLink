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

// 글쓰기 버튼 클릭 이벤트 처리 함수
function handleWriteButtonClick() {
	window.location.href = "/boardCreate";
}

// 페이지 로드 시 게시물 목록 렌더링
document.addEventListener("DOMContentLoaded", function() {

	renderBoardList();

	// 좋아요 버튼 클릭 이벤트 리스너 추가
	const likeButtons = document.querySelectorAll(".like_button");
	likeButtons.forEach((button) => {
		button.addEventListener("click", toggleLike);
	});

	// 글쓰기 버튼 클릭 이벤트 리스너 추가
	const writeButton = document.getElementById("addButton");
	writeButton.addEventListener("click", handleWriteButtonClick);
});
