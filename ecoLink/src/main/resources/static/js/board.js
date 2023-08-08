/*// 백엔드와의 통신을 위한 함수
function sendRequest(url, method, body) {
    return fetch(url, {
        method: method,
        body: JSON.stringify(body),
        headers: {
            "Content-Type": "application/json",
        },
    })
    .then(response => response.json())
    .catch(error => {
        console.error("에러 발생:", error);
    });
}

// 좋아요 버튼 클릭 이벤트 처리 함수
async function toggleLike(event) {
    const likeButton = event.target;
    const postId = likeButton.dataset.postId;

    const user = await getUserInfo(); // 로그인 정보를 받아오는 함수 호출

    if (!checkLoginStatus(user)) {
        alert("로그인이 필요합니다.");
    } else {
        const liked = likeButton.classList.toggle("liked");
        const likedStatus = liked ? "like" : "unlike";
        
        sendRequest(`/api/posts/${postId}/like`, "POST", { liked: likedStatus })
        .then(data => {
            if (data.success) {
                console.log("좋아요 상태 업데이트 완료");
            } else {
                console.error("좋아요 상태 업데이트 실패");
            }
        });
    }
}

// 로그인 상태를 확인하는 함수
function checkLoginStatus(user) {
    return user !== undefined && user !== null;
}

// 사용자 정보를 받아오는 함수 (가정)
async function getUserInfo() {
    try {
        const response = await sendRequest("/api/user/info", "GET");
        return response.user;
    } catch (error) {
        console.error("로그인 정보 확인 실패:", error);
        return null; // 로그인 정보가 없으면 null 반환
    }
}

// 페이지 로드 시 좋아요 버튼 클릭 이벤트 리스너 추가
document.addEventListener("DOMContentLoaded", function () {
    const likeButtons = document.querySelectorAll(".like_button");
    likeButtons.forEach((button) => {
        button.addEventListener("click", toggleLike);
    });
});
*/