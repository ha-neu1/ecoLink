/*document.addEventListener("DOMContentLoaded", function() {
    // 현재 URL에서 'id' 파라미터 값을 가져옴
    const url = new URL(window.location.href);
    const postQueStr = url.searchParams;
    const i = postQueStr.get('id');

    // 로컬 스토리지에서 게시물 정보 가져오기
    let postJson = localStorage.getItem("post");
    let postInfo = JSON.parse(postJson);

    // 해당 게시물의 조회수 증가
    postInfo[i].viewCnt++;

    // 수정된 게시물 정보를 로컬 스토리지에 저장
    localStorage.setItem("post", JSON.stringify(postInfo));

    // 게시물이 없을 경우, 빈 배열로 초기화
    if (postInfo == null) {
        postInfo = [];
    }

    // 게시물을 표시할 영역 선택
    let listArea = document.getElementById("postRead");
    let postList = document.createElement("div");

    // 게시물 화면 구성
    postList.innerHTML = '<div class="board_title">'
    + '<div class="page_name"><strong>공지사항</strong></div>'
    + '<input type="button" onclick="location.href=' + "'/board'" + '" value="목록">' // "목록" 버튼
    + '<input type="button" onclick="location.href=' + "'/boardUpdate" + '?id=' + i + "'" + '" value="수정"></input>' // "수정" 버튼
    + '</div>'
    + '<hr class="hr_bold"></hr>'
    + '<div class="post_area">'
    + '<div class="post_name">' + postInfo[i].post_name + '</div>' // 게시물 제목
    + '<div class="post_info">' + postInfo[i].postDate + ' ' + postInfo[i].postTime + ' / 조회 ' + postInfo[i].viewCnt + '</div>' // 작성일, 조회수
    + '<hr class="hr_light">'
    + '<div class="post_content">' + postInfo[i].post_cont + '</div>' // 게시물 내용
    + '</div>'

    // 게시물 화면을 페이지에 추가
    listArea.appendChild(postList);
});
*/