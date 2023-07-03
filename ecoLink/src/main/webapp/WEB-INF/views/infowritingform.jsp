<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
</head>
<body>
<div class="row">
    <!-- 포스트 추가하는 곳-->
    <!-- Naver SmartEditor 2.8.2를 사용하였습니다. -->
    <h3>Naver Smart Editor 2.0</h3>
    <form action="insertStudentInfoForm" method="post">
        <div id="smarteditor">
            <textarea
                name="editorTxt"
                id="editorTxt"
                rows="20"
                cols="10"
                placeholder="내용을 입력해주세요"
                style="width: 500px"></textarea>
        </div>
        <input type="button" />
    </form>
    <!-- 포스트 추가하는 곳-->   
</div>
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
	    smartEditor();
	})
	</script>
<script>
/* 버튼 클릭 이벤트 */
submitPost = function() {
    oEditors.getById["editorTxt"].exec("UPDATE_CONTENTS_FIELD", [])
    let content = document.getElementById("editorTxt").value
    
    //if(content == '') {
    if(content == '<p>&nbsp;</p>') { //비어있어도 기본 P태그가 붙더라.
        alert("내용을 입력해주세요.")
        oEditors.getById["editorTxt"].exec("FOCUS")
        return
    } else {
        console.log(content)
    }
}
</script>


</body>
</html>