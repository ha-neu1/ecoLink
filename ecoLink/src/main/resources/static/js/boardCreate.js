const post_save = document.getElementById("post_save");

post_save.addEventListener("click", function() {

    const post_name = document.getElementById("post_name");
    const post_cont = document.getElementById("post_cont");

    let date = new Date();
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();
    let hour = date.getHours();
    let minute = date.getMinutes();
    let second = date.getSeconds();
    let viewCnt = 0;

    if(day < 10){
        day = "0" + day;
    }

    if(month < 10){
        month = "0" + month;
    }

    if(hour < 10){
        hour = "0" + hour;
    }

    if(minute < 10){
        minute = "0" + minute;
    }
    
    if(second < 10){
        second = "0" + second;
    }

    let postDate = year + "-" + month + "-" + day;
    let postTime = hour + ":" + minute + ":" + second;

    if (post_name.value == "") {
        alert("제목을 입력해주세요.");
        post_name.focus();
        return
    } else if (post_cont.value == "") {
        alert("내용을 입력해주세요.")
        post_cont.focus();
        return
    } 

    const postJson = localStorage.getItem("post");

    let postInfo = JSON.parse(postJson);

    if (postInfo == null) {
        postInfo = [];
    }

    const post = {
        post_name : post_name.value,
        post_cont : post_cont.value,
        postDate : postDate,
        postTime : postTime,
        viewCnt : viewCnt
    }

    postInfo.push(post);

    const newPostJson = JSON.stringify(postInfo);
    localStorage.setItem("post", newPostJson);
    
    location.href='/board';
});