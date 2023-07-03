document.addEventListener("DOMContentLoaded", function() {

    const url = new URL(window.location.href);
    const postQueStr = url.searchParams;
    const i = postQueStr.get('id');

    let postJson = localStorage.getItem("post");

    let postInfo = JSON.parse(postJson);

    postInfo[i].viewCnt++;

    localStorage.setItem("post", JSON.stringify(postInfo));

    if (postInfo == null) {
        postInfo = [];
    }

    let listArea= document.getElementById("postRead");
    let postList = document.createElement("div")

    postList.innerHTML = '<div class="board_title">'
    + '<div class="page_name"><strong>공지사항</strong></div>'
    //+ '<input id="post_del" type="button" onclick="location.href=' + "'/board'" + '" value="삭제">'
    + '<input type="button" onclick="location.href=' + "'/board'" + '" value="목록">'
    + '<input type="button" onclick="location.href=' + "'/boardUpdate" + '?id=' + i + "'" + '" value="수정"></input>'
    + '</div>'
    + '<hr class="hr_bold"></hr>'
    + '<div class="post_area">'
    + '<div class="post_name">' + postInfo[i].post_name + '</div>'
    + '<div class="post_info">' + postInfo[i].postDate + ' ' + postInfo[i].postTime + ' / 조회 ' + postInfo[i].viewCnt + '</div>'
    + '<hr class="hr_light">'
    + '<div class="post_content">' + postInfo[i].post_cont + '</div>'
    + '</div>'

    listArea.appendChild(postList);
});

/* const post_del = document.getElementById("post_del");

post_del.addEventListener("click", function() {

    const url = new URL(window.location.href);
    const postQueStr = url.searchParams;
    const i = postQueStr.get('id');

    let postJson = localStorage.getItem("post");

    let postInfo = JSON.parse(postJson);

    postInfo.splice(i, 1);

    localStorage.setItem("post", JSON.stringify(postInfo));

    location.href='/board';
}); */