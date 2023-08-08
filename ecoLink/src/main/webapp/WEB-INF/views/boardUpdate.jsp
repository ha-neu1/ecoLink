<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시물 수정</title>
<link rel="stylesheet" href="/css/board.css">
<script src="https://kit.fontawesome.com/7aca531ae5.js"
	crossorigin="anonymous"></script>
<script src="/js/boardUpdate.js" defer type="module"></script>
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="board_area">
		<div class="board_title">
			<div class="page_name">
				<strong>게시물 수정</strong>
			</div>
		</div>
		<hr class="hr_bold">

		<div class="form_container">
			<form id="update_form"
				th:action="@{/boardUpdate/{boardId}(boardId=${board.boardId})}"
				method="post">
				<!-- 이미지 미리보기 관련 코드 -->
				<div class="image_wrap">
					<label for="image_file" class="image_file_zone"
						id="image_file_zone">
						<div class="image_fileholder" id="image_fileholder">사진추가</div>
					</label> <input type="file" id="image_file" class="image_file" name="files"
						multiple="multiple" accept="image/*" hidden>

				</div>
				<div class="post_tit">
					<input type="text" class="tit" name="boardTitle"
						value="${board.boardTitle}">
				</div>
				<div class="post_contents">
					<textarea class="contents" name="boardContents">${board.boardContents}</textarea>
				</div>
				<input type="hidden" name="boardType" value="share">
				<div class="post_btn">
					<button class="button" type="submit" id="submit_btn">수정하기</button>
				</div>
			</form>
		</div>

		<script>
		
		 $(document).ready(function() {
		        $("#submit_btn").click(function() {
		            var formData = $("#update_form").serialize(); // 폼 데이터 수집

		            $.ajax({
		                type: "POST",
		                url: "/boardUpdate",
		                data: formData,
		                success: function(response) {
		                    alert("게시물이 성공적으로 수정되었습니다.");
		                    // 게시물 읽기 페이지로 이동
		                    window.location.href = "/share/boardRead?boardId=" + response.boardId;
		                },
		                error: function(xhr, status, error) {
		                    alert("게시물 수정 오류: " + error);
		                }
		            });
		        });
		    });
		
		
    // 이미지 미리보기 관련 스크립트
    (function imagePreview(image_fileholder, image_file) {
        var fileHolder = document.getElementById(image_fileholder);
        var imageFile = document.getElementById(image_file);
        var sel_files = [];

        // 이미지와 체크 박스를 감싸고 있는 div 속성
        var div_style =
            'display:inline-block;position:relative;' +
            'width:600px;height:400px;margin:5px;border:1px solid #00f;z-index:3';
        // 미리보기 이미지 속성
        var img_style = 'width:100%;height:100%;z-index:2';
        // 이미지 안에 표시되는 체크박스의 속성
        var chk_style =
            'width:30px;height:30px;position:absolute;font-size:24px;' +
            'right:0px;bottom:0px;z-index:999;background-color:rgba(255,255,255,0.1);color:#f00;z-index:1';

        imageFile.onchange = function(e) {
            var files = e.target.files;
            sel_files = [];
            for (var f of files) {
                imageLoader(f);
            }
        };

        // 파일 드래그앤 드롭 사용
        fileHolder.addEventListener('dragenter', function(e) {
            e.preventDefault();
            e.stopPropagation();
        }, false);

        fileHolder.addEventListener('dragover', function(e) {
            e.preventDefault();
            e.stopPropagation();
        }, false);

        fileHolder.addEventListener('drop', function(e) {
            e.preventDefault();
            e.stopPropagation();
            fileHolder.classList.remove('dragover');

            var files = e.dataTransfer.files;
            imageFile.files = files;

            var formData = new FormData();
            for (var i = 0; i < files.length; i++) {
                formData.append('files', files[i]);
            }

            for (var f of files) {
                imageLoader(f);
            }
        }, false);

        function imageLoader(file) {
            var reader = new FileReader();
            reader.onload = function(ee) {
                let img = document.createElement('img');
                img.setAttribute('style', img_style);
                img.src = ee.target.result;
                if (fileHolder.textContent == '사진추가') {
                    fileHolder.textContent = '';
                }
                fileHolder.appendChild(makeDiv(img, file));
            };
            reader.readAsDataURL(file);
        }

        function makeDiv(img, file) {
            var div = document.createElement('div');
            div.setAttribute('style', div_style);

            var btn = document.createElement('input');
            btn.setAttribute('type', 'button');
            btn.setAttribute('value', 'x');
            btn.setAttribute('data-del-file', file.name || '');
            btn.setAttribute('style', chk_style);
            btn.onclick = function(ev) {
                ev.preventDefault();
                ev.stopPropagation();
                var ele = ev.target;
                var delFile = ele.dataset.delFile || '';
                for (var i = 0; i < sel_files.length; i++) {
                    if (delFile == sel_files[i].name) {
                        sel_files.splice(i, 1);
                        break;
                    }
                }
                var dt = new DataTransfer();
                for (var i = 0; i < sel_files.length; i++) {
                    dt.items.add(new File([sel_files[i]], sel_files[i].name, { type: sel_files[i].type }));
                }
                imageFile.files = dt.files;
                var p = ele.parentNode;
                fileHolder.removeChild(p);
            };
            div.appendChild(img);
            div.appendChild(btn);
            return div;
        }
    })('image_fileholder', 'image_file');
</script>
</body>
<footer>
	<%@ include file="footer.jsp"%>
</footer>
</html>
