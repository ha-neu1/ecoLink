document.addEventListener("DOMContentLoaded", function() {

    const postJson = localStorage.getItem("post");

    let postInfo = JSON.parse(postJson);

    if (postInfo == null) {
        postInfo = [];
    }

    for(let i = 0; i < postInfo.length; i++) {
        let listArea= document.getElementById("list");
        let postList = document.createElement("tr")
    
        postList.innerHTML = '<td>' + (i + 1) + '</td>'
        + '<td class="post_list_name"><a href="/boardRead?id=' + i + '">' + postInfo[i].post_name + '</a></td>'
        + '<td>' + postInfo[i].postDate + '</td>'
        + '<td>' + postInfo[i].viewCnt + '</td>';
    
        listArea.appendChild(postList);
        console.log(i);
    }
});