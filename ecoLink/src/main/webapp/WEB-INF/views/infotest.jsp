<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	 <textarea rows="20" name="form-control" id="form-control"></textarea>
	 <!-- SmartEditor2 텍스트편집기 -->
<script src="smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
	<script>
	$(document).ready(function () {
			
		<!-- SmartEditor2 텍스트편집기 -->
		var oEditors = [];
		function smartEditorIFrame() {
			
			nhn.husky.EZCreator.createInIFrame({
				oAppRef : oEditors,
				elPlaceHolder : "form-control",
				sSkinURI : "smarteditor/SmartEditor2Skin.html",
				fCreator : "createSEditor2"
			});
	      }
		smartEditorIFrame();
	});
</script>
</body>
</html>