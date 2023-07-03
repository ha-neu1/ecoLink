document.addEventListener("DOMContentLoaded", function() {

    const url = new URL(window.location.href);
    const postQueStr = url.searchParams;
    const i = postQueStr.get('id');

    let postJson = localStorage.getItem("post");

    let postInfo = JSON.parse(postJson);

    let listArea= document.getElementById("postModify");
    let postList = document.createElement("div")

    postList.innerHTML = '<input id="post_name" class="post_area" type="text" value="' + postInfo[i].post_name + '">'
    + '<textarea id="post_cont" class="post_area" id="post_writer" cols="30" rows="10">' + postInfo[i].post_cont + '</textarea>'

    listArea.appendChild(postList);
});

const post_save = document.getElementById("post_save");

post_save.addEventListener("click", function() {

    const post_name = document.getElementById("post_name");
    const post_cont = document.getElementById("post_cont");

    if (post_name.value == "") {
        alert("제목을 입력해주세요.");
        post_name.focus();
        return
    } else if (post_cont.value == "") {
        alert("내용을 입력해주세요.")
        post_cont.focus();
        return
    } 

    const url = new URL(window.location.href);
    const postQueStr = url.searchParams;
    const i = postQueStr.get('id');

    let postJson = localStorage.getItem("post");

    let postInfo = JSON.parse(postJson);

    postInfo[i].post_name = post_name.value;
    postInfo[i].post_cont = post_cont.value;

    localStorage.setItem("post", JSON.stringify(postInfo));

    location.href='/board';
});