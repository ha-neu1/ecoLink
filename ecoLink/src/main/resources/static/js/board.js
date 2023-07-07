document.addEventListener("DOMContentLoaded", function() {

    const postJson = localStorage.getItem("post");

    let postInfo = JSON.parse(postJson);

    if (postInfo == null) {
        postInfo = [];
    }

/*    for(let i = 0; i < postInfo.length; i++) {
        let listArea= document.getElementById("list");
        let postList = document.createElement("tr")
    
        postList.innerHTML = '<td>' + (i + 1) + '</td>'
        + '<td class="post_list_name"><a href="/boardRead?id=' + i + '">' + postInfo[i].post_name + '</a></td>'
        + '<td>' + postInfo[i].postDate + '</td>'
        + '<td>' + postInfo[i].viewCnt + '</td>';
    
        listArea.appendChild(postList);
        console.log(i);
    }*/
    
    
    for(let i = 0; i < postInfo.length; i++) {
        let listArea= document.getElementById("list");
        let postList = document.createElement("tr")
    
        postList.innerHTML = '<div id="boardMain" class="boardList">'
			+ '<h3 class="memId">작성자 이름</h3>'
			+ '<h4 class="boardTitle">게시물 제목</h4>'
			+ '<img class="boardImage" src="/images/logo2.png" alt="게시물 이미지">'
			+ '<p class="boardCont">본문 내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.본문 내용입니다.</p>'
			+ '</div>';
    
        listArea.appendChild(postList);
        console.log(i);
    }
});