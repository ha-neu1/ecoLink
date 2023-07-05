<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="smarteditor/js/HuskyEZCreator.js"
	charset="utf-8"></script>
</head>
<body>
	<h3>게시판</h3>
	<form action="insertStudentInfoForm" method="post">
		<input type="text" placeholder="제목을 입력하세요" id="title"
			style='width: 600px'>
		<div id="smarteditor">
			<textarea name="editorTxt" id="editorTxt" rows="20" cols="10"
				placeholder="내용을 입력해주세요" style="width: 500px"></textarea>
		</div>
		<input type="button" value="글작성하기" onclick="submitPost()" />
	</form>
	<script>
	/* 에디터 설정 */
let oEditors = [];

smartEditor = function() {
    console.log("Naver SmartEditor")
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: "editorTxt",
        sSkinURI: "smarteditor/SmartEditor2Skin.html",
        fCreator: "createSEditor2"
    })
}

$(document).ready(function() {
    smartEditor()
})
/* 버튼 클릭 이벤트 */
submitPost = function() {

    oEditors.getById["editorTxt"].exec("UPDATE_CONTENTS_FIELD", []);
    //content Text 가져오기
    let content = document.getElementById("editorTxt").value;
    console.log(content);
    if(content == '<p>&nbsp;</p>') { //비어있는 경우
        alert("내용을 입력해주세요.")
        oEditors.getById["editorTxt"].exec("FOCUS")
        return;
    } else {
        //console.log(content);
        let writePost = {
            title: $("#title")[0].value
            ,content: content
        }
        console.log(writePost);
    }
}
    
    

</script>
</body>
</html>