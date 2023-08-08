<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/boardWrite.css">
    <title>Document</title>
    <script src="https://kit.fontawesome.com/7aca531ae5.js" crossorigin="anonymous"></script>
    <%@ include file="header.jsp"%>
<%@ include file="chatbot.jsp"%>
</head>
<body>
<div class="info_title">게시글 작성하기</div>
    <form id="post_form" action="reviewwriting" method="post" enctype="multipart/form-data">
    <div class="image_wrap">
    <div class="hasImages">	
    </div>
         <label for="image_file" class="image_file_zone" id="image_file_zone">
            <div class="image_fileholder" id="image_fileholder">사진추가</div>
         </label>
         <input type="file" id="image_file" class="image_file" name="files" multiple="multiple" accept="image/*" hidden>
    </div>
    <div class="post_tit">
                <p>제목</p>
                <input type="text" class="tit" name="boardTitle"
                   placeholder="제목을 입력해주세요.">
            </div>
            <div class="post_contents">
                <p>본문 내용</p>
                <textarea class="contents" name="boardContents" placeholder="내용을 입력해주세요."></textarea>
            </div>
            <div class="post_btn">
                <button type="submit" id="submit_btn">작성하기</button>
            </div> 
    </form>
   
    <script>
    
        (function imageView(image_fileholder, image_file) {
  var fileHolder = document.getElementById(image_fileholder);
  var imageFile = document.getElementById(image_file);
  var sel_files = [];

  // 이미지와 체크 박스를 감싸고 있는 div 속성
  var div_style =
    'display:inline-block;position:relative;' +
    'width:600px;height:380px;margin:5px;border:1px solid #c5c5c5;z-index:3;  border-radius: 8px;';
  // 미리보기 이미지 속성
  var img_style = 'width:100%;height:100%;z-index:2';
  // 이미지안에 표시되는 체크박스의 속성
  var chk_style =
    'width:30px;height:30px;position:absolute;font-size:24px;' +
    'right:0px;bottom:0px;z-index:999;background-color:rgba(255,255,255,0.1);color:#f00;z-index:1';

  imageFile.onchange = function(e) {
	  var files = e.target.files;
	  sel_files = []; // Clear the sel_files array before adding new files
	  for (var f of files) {
	    imageLoader(f);
	  }
	};

  // 탐색기에서 드래그앤 드롭 사용
  fileHolder.addEventListener(
    'dragenter',
    function (e) {
      e.preventDefault();
      e.stopPropagation();
    },
    false
  );

  fileHolder.addEventListener(
    'dragover',
    function (e) {
      e.preventDefault();
      e.stopPropagation();
    },
    false
  );

  fileHolder.addEventListener(
		  'drop',
		  function (e) {
		    e.preventDefault();
		    e.stopPropagation();
		    fileHolder.classList.remove('dragover'); // Remove the "dragover" style

		    var files = e.dataTransfer.files;
		    imageFile.files = files; // Add the dropped files to the file input field

		    // Update the hidden input field with the dropped files
		    var formData = new FormData();
		    for (var i = 0; i < files.length; i++) {
		      formData.append('files', files[i]);
		    }

		    // Optional: You can send the formData to the server via AJAX if needed.
		    // However, the form submission should automatically include the updated file input.

		    for (var f of files) {
		      imageLoader(f);
		    }
		  },
		  false
		);

  /* 첨부된 이미지를 배열에 넣고 미리보기 */
 function imageLoader(file) {
  var reader = new FileReader();
  reader.onload = function (ee) {
    let img = document.createElement('img');
    img.setAttribute('style', img_style);
    img.src = ee.target.result;
    if (fileHolder.textContent == '사진추가') {
      fileHolder.textContent = '';
    }
    fileHolder.appendChild(makeDiv(img, file));
  };
  reader.readAsDataURL(file);

  var formData = new FormData();
  formData.append('files', file);
  for (var f of formData.getAll('files')) {
    console.log("Files added to FormData:", f.name);
  }
}

  /* 첨부된 파일이 있는 경우 checkbox와 함께 fileHolder에 추가할 div를 만들어 반환 */
  function makeDiv(img, file) {
    var div = document.createElement('div');
    div.setAttribute('style', div_style);

    var btn = document.createElement('input');
    btn.setAttribute('type', 'button');
    btn.setAttribute('value', 'x');
    btn.setAttribute('data-del-file', file.name || '');
    btn.setAttribute('style', chk_style);
    btn.onclick = function (ev) {
      ev.preventDefault(); 
      ev.stopPropagation(); // 이벤트 전파 중지
      var ele = ev.target;
      var delFile = ele.dataset.delFile || '';
      for (var i = 0; i < sel_files.length; i++) {
        if (delFile == sel_files[i].name) {
          sel_files.splice(i, 1);
          break; // 파일 찾았으면 반복문 종료
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
  function uploadFiles() {
      var formData = new FormData();
      var imageFiles = document.getElementById('image_file').files;
      for (var i = 0; i < imageFiles.length; i++) {
          formData.append('files', imageFiles[i]);
      }

      var xhr = new XMLHttpRequest();
      xhr.open('POST', 'reviewwriting', true);
      xhr.onreadystatechange = function () {
          if (xhr.readyState === XMLHttpRequest.DONE) {
              if (xhr.status === 200) {
                  // Handle the response from the server if needed
                  var response = xhr.responseText;
                  console.log('Response:', response);
              } else {
                  // Handle errors if any
                  console.error('Error:', xhr.statusText);
              }
          }
      };
      xhr.send(formData);
  }
})('image_fileholder', 'image_file');
    </script>
</body>
<footer>
	<%@ include file="footer.jsp"%>
</footer>
</html>