<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="css/join.css">
<link rel="stylesheet" href="css/common.css">
<script src="/js/jquery-3.6.4.min.js"></script>
<script>
$(document).ready(function() {
	  const join_form = document.getElementById("join_form");
	  const join_btn = document.getElementById("join_submit_btn");
	  const join_id = document.getElementById("memId");
	  const join_pw = document.getElementById("memPw");
	  const join_pw_confirm = document.getElementById("memPw_confirm");
	  const join_email = document.getElementById("memEmail");
	  const join_name = document.getElementById("memName");

	  // memberPw 입력란 클릭 시 이벤트 처리
	  $(document).on('click', '#memPw', function(e) {
	    e.stopPropagation();
	    $(this).next('.ec-base-tooltip').fadeIn(100);
	  });

	  // 다른 곳을 클릭했을 때 이벤트 처리
	  $(document).on('click', function(e) {
	    var target = $(e.target);
	    if (!target.closest('#memPw').length) {
	      $('.ec-base-tooltip').fadeOut(100);
	    }
	  });
	  
	 // 비밀번호 일치 여부 확인
     $(document).on('click', 'input:not(#memPw_confirm)', function(e) {
        var pwConfirmValue = $('#memPw_confirm').val();
        var pwValue = $('#memPw').val();
        if (pwValue !== '' && pwConfirmValue !== '' && pwConfirmValue !== pwValue) {
            alert("비밀번호가 일치하지 않습니다.");
        }
     });

	  // btnClose 클릭 시 이벤트 처리
	  $(document).on('click', '.btnClose', function(e) {
	    e.preventDefault();
	    $('.ec-base-tooltip').fadeOut(100);
	  });

	  // 회원유형 선택 시 처리
	  $(document).on('change', '#memType', function() {
	    var selected = $(this).val();
	    if (selected === "기업회원") {
	      $("#entPhone").show();
	      $("#entCrn").show();
	      $("#entd_MainPic").show();
	      $("#entd_Short").show();
	      $("#entd_URL").show();
	      $("#entd_Intro").show();
	      $("#entd_IntroPic").show();
	      $("#entd_Pic1").show();
	      $("#entd_Pic2").show();
	      $("#entd_Pic3").show();
	      $("#entd_Explain1").show();
	      $("#entd_Explain2").show();
	      $("#entd_Explain3").show();
	    } else if (selected === "일반회원") {
	      $("#entPhone").hide();
	      $("#entCrn").hide();
	      $("#entd_MainPic").hide();
	      $("#entd_Short").hide();
	      $("#entd_URL").hide();
	      $("#entd_Intro").hide();
	      $("#entd_IntroPic").hide();
	      $("#entd_Pic1").hide();
	      $("#entd_Pic2").hide();
	      $("#entd_Pic3").hide();
	      $("#entd_Explain1").hide();
	      $("#entd_Explain2").hide();
	      $("#entd_Explain3").hide();
	    }
	  });
	// join_submit_btn 클릭 시 이벤트 처리
	  $("#join_submit_btn").on('click', function(event) {
	      event.preventDefault();
	      var selected = $('#memType').val();
	      var emptyFields = []; // 빈칸인 필드들을 저장하는 배열

	      // 아이디, 비밀번호, 이메일, 이름에 빈칸 입력 시 배열에 추가
	      if (join_id.value.trim() === "") {
	          emptyFields.push("아이디");
	      }
	      if (join_pw.value.trim() === "") {
	          emptyFields.push("비밀번호");
	      }
	      if (join_pw_confirm.value.trim() === "") {
	          emptyFields.push("비밀번호 확인");
	      }
	      if (join_email.value.trim() === "") {
	          emptyFields.push("이메일");
	      }
	      if (join_name.value.trim() === "") {
	          emptyFields.push("이름");
	      }
	      if (join_name.value.trim() === "") {
	          emptyFields.push("닉네임");
	      }

	      // 기업회원인 경우 추가 필드에 대한 빈칸 확인
	      if (selected === "기업회원") {
	          if ($('#crn1').val().trim() === "" || $('#crn1').val().trim().length !== 3) {
	        	  emptyFields.push("사업자 등록번호(첫 번째 3자리)");
	          }
	          if ($('#crn2').val().trim() === "" || $('#crn2').val().trim().length !== 2) {
	        	  emptyFields.push("사업자 등록번호(두 번째 2자리)");
	          }
	          if ($('#crn3').val().trim() === "" || $('#crn3').val().trim().length !== 5) {
	        	  emptyFields.push("사업자 등록번호(세 번째 5자리)");
	          }
	          if ($('#mobile2').val().trim() === "" || $('#crn2').val().trim().length !== 2) {
	        	  emptyFields.push("기업 전화번호");
	          }
	          if ($('#mobile3').val().trim() === "" || $('#crn3').val().trim().length !== 5) {
	        	  emptyFields.push("기업 전화번호");
	          }
	          // 이미지 필드에 대한 확인
	          if (!$('#entdPic1')[0].files[0]) {
	        	  emptyFields.push("제품1 이미지");
	          }
	          if ($('#entdExplain1').val().trim() === "") {
	              emptyFields.push("제품1 설명");
	          }
	      }

	      // 빈칸인 필드가 있을 경우 메시지 출력
	      if (emptyFields.length > 0) {
	          var fieldsMessage = emptyFields.join(", ");
	          alert(fieldsMessage + "란을 입력해주세요.");
	      } else {
	    	  $.ajax({
		            url: 'ismemberexist',
		            type: 'post',
		            data: {
		                'inputId': $('#memId').val(),
		                'inputEmail': $('#memEmail').val()
		            },
		            dataType: 'json',
		            success: function(response) {
		                if ($.trim(response.result) === "ok") {
		                    if (selected === "일반회원") {
		                        // serchType이 normal일 경우 addMember 호출
		                        addMember();
		                    } else if (selected === "기업회원") {
		                        // serchType이 enter일 경우 addMember와 addEnterprise 호출
		                       	addMember();
		                        addEnterprise();
		                    }
		                    alert("가입이 완료되었습니다.");
		                    window.location.href = "/login";
		                } else if ($.trim(response.result) === "one_id") {
		                    alert("이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요.");
		                } else if ($.trim(response.result) === "one_email") {
		                    alert("이미 존재하는 이메일입니다. 다른 이메일을 입력해주세요.");
		                } else {
		                    alert("아이디와 이메일이 이미 존재합니다.");
		                }
		            },
		            error: function(request, status, e) {
		                alert("코드=" + request.status + "\n" + "메시지=" + request.responseText + "\n" + "error=" + e);
		            }
		        });
	      }
	  });


	  function addMember() {
    	  $.ajax({
	    	    url: 'join',
	    	    type: 'post',
	    	    data: {
	    	      'memId': $('#memId').val(),
	    	      'memPw': $('#memPw').val(),
	    	      'memEmail': $('#memEmail').val(),
	    	      'memType': $('#memType').val(),
	    	      'memName': $('#memName').val(),
	    	      'memNick': $('#memNick').val()
	    	    },
	    	    dataType: 'json',
	    	    success: function(response) {
	    	    	console.log("Member added successfully");
	    	    },
	    	    error: function(request, status, e) {
	    	    	console.log("코드=" + request.status + "\n" + "메시지=" + request.responseText + "\n" + "error=" + e);
	    	    }
	    	  });
	  }
 
	  function addEnterprise() {
		  //alert("addEnterprise");
		  $.ajax({
	    	    url: 'enterjoin',
	    	    type: 'post',
	    	    data: {
	    	      'entCrn': $('#crn1').val() + '-' + $('#crn2').val() + '-' + $('#crn3').val(),
	    	      'entPhone': $('#mobile1').val() + '-' + $('#mobile2').val() + '-' + $('#mobile3').val(),
	    	      'memId': $('#memId').val(),
	    	      'entdMainPic': $('#entdMainPic').val(),
	    	      'entdShort': $('#entdShort').val(),
	    	      'entdURL': $('#entdURL').val(),
	    	      'entdIntro': $('#entdIntro').val(),
	    	      'entdIntroPic': $('#entdIntroPic').val(),
	    	      'entdPic1': $('#entdPic1').val(),
	    	      'entdPic2': $('#entdPic2').val(),
	    	      'entdPic3': $('#entdPic3').val(),
	    	      'entdExplain1': $('#entdExplain1').val(),
	    	      'entdExplain2': $('#entdExplain2').val(),
	    	      'entdExplain3': $('#entdExplain3').val()
	    	    },
	    	    dataType: 'json',
	    	    success: function(response) {
	    	    	console.log("Enterprise added successfully");
	    	    },
	    	    error: function(request, status, e) {
	    	    	console.log("코드=" + request.status + "\n" + "메시지=" + request.responseText + "\n" + "error=" + e);
	    	    }
	    	  });
	  }
	  $(document.body).on('input', 'textarea', function() {
	        const textareaId = $(this).attr('id');
	        const divId = textareaId + 'charCount';
	        showCharacterCount(textareaId, divId);
	    });
      $(document).ready(function() {
	      // 새로운 함수: 문자 수 표시 함수
	      function showCharacterCount(textareaId, divId) {
	    	  const charCount = document.getElementById(divId);
	    	  const textarea = document.getElementById(textareaId);
	    	  const maxLength = textarea.getAttribute("maxlength");
	    	  const currentLength = textarea.value.length;
	          charCount.textContent = currentLength + "/" + maxLength;
	      }
	
	      // 새로운 기능: 각 textarea에 대한 문자 수 표시를 위한 이벤트 핸들러 추가
	      const textareaIds = ["entdShort", "entdIntro", "entdExplain1", "entdExplain2", "entdExplain3"]; // 문자 수를 표시할 textarea 요소들의 ID 목록
	      const divIds = ["shortCharCount", "IntrocharCount", "ExplaincharCount1", "ExplaincharCount2", "ExplaincharCount3"]; // 문자 수를 표시할 div 요소들의 ID 목록
	
	      for (let i = 0; i < textareaIds.length; i++) {
	    	  const textareaId = textareaIds[i];
	    	  const divId = divIds[i];
	    	  const textarea = document.getElementById(textareaId);
	    	  textarea.addEventListener('input', function() {
	    	      showCharacterCount(textareaId, divId);
	    	  });
	    	  showCharacterCount(textareaId, divId); // 최초 페이지 로드 시 문자 수 표시를 위해 호출
	      }
      });
      
   // 파일 선택(input type="file") 요소에 대한 이벤트 리스너를 추가하여 이미지 미리 보기 기능 구현
      function addImagePreviewListener(inputId, previewId) {
          document.getElementById(inputId).addEventListener("change", function(event) {
              const file = event.target.files[0]; // 선택된 파일 가져오기

              // FileReader 객체를 생성하여 파일을 읽어옴
              const reader = new FileReader();

              // 파일을 성공적으로 읽었을 때의 이벤트 처리
              reader.onload = function() {
                  // 미리 보기 영역에 이미지를 삽입하여 미리 보기
                  const previewArea = document.getElementById(previewId);
                  previewArea.innerHTML = ""; // 기존 이미지 초기화
                  const imgElement = document.createElement("img");
                  imgElement.src = reader.result;
                  imgElement.alt = "미리 보기 이미지";
                  imgElement.style.maxWidth = "100%"; // 미리 보기 이미지의 최대 너비 설정

                  // 이미지 삭제 버튼 생성
                  const deleteBtn = document.createElement("button");
                  deleteBtn.textContent = "등록된 이미지 삭제";
                  deleteBtn.addEventListener("click", function() {
                      // 이미지 삭제 버튼이 클릭되면 미리 보기 영역에서 이미지 제거
                      previewArea.innerHTML = "";
                      // 선택한 파일도 초기화
                      document.getElementById(inputId).value = "";
                  });

                  // 이미지와 삭제 버튼을 미리 보기 영역에 추가
                  previewArea.appendChild(imgElement);
                  previewArea.appendChild(deleteBtn);
              };

              // 파일 읽기 작업 실행
              if (file) {
                  reader.readAsDataURL(file);
              }
          });
      }

      // 각 파일 선택 요소에 대해 함수를 호출하여 이벤트 리스너를 추가
      addImagePreviewListener("entdMainPic", "preview1");
      addImagePreviewListener("entdIntroPic", "preview2");
      addImagePreviewListener("entdPic1", "preview3");
      addImagePreviewListener("entdPic2", "preview4");
      addImagePreviewListener("entdPic3", "preview5");

});

</script>

</head>
<body>
<div id="container">
    <div id="header">
        <div class="logo">
            <a href="/main">
                <img src="/images/logo1.png" alt="" class="max">
            </a>
        </div>
    </div>
    <div id="contents">
        <div class="page">
            <div class="container-mypage">
                <div class="row">
                    <div class="col-lg-4">
                        <form id="join_form" name="join_form" action="/join" method="post">
                            <div class="ec-base-table typeWrite">
                                <table class="type-full">
                                    <tbody>
                                        <tr>
                                            <th>회원유형</th>
                                            <td>
                                                <p class="member">
                                                    <select id="memType" name="memType" >
                                                        <option id="normal" value="일반회원" selected="selected">일반회원</option>
                                                        <option id="enter" value="기업회원">기업회원</option>
                                                    </select>
                                                </p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">아이디*</th>
                                            <td>
                                                <input id="memId" name="memId" class="inputTypeText" placeholder="" value="" type="text">
                                                <p class="help">(영문 소문자/숫자, 4~16자)</p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">비밀번호*</th>
                                            <td>
                                                <div class="eTooltip">
                                                    <input id="memPw" name="memPw" autocomplete="off" maxlength="16" 0="disabled" value="" type="password">
                                                    <div class="ec-base-tooltip typeUpper">
                                                        <div class="content">
                                                            <strong class="txtWarn">※ 비밀번호 입력 조건</strong>
                                                            <ul class="ec-base-help typeDash gBlank10 txtWarn">
                                                                - 대소문자/숫자/특수문자 중 2가지 이상 조합, 8자~16자<br>
                                                                - 입력 가능 특수문자<br>
                                                                &nbsp;&nbsp;&nbsp;~ ` ! @ # $ % ^ ( ) * _ - = { } [ ] | ; : &lt; &gt; , . ? /<br>
                                                                - 공백 입력 불가능<br>
                                                                - 연속된 문자, 숫자 사용 불가능<br>
                                                                - 동일한 문자, 숫자를 반복해서 사용 불가능<br>
                                                                - 아이디 포함 불가능
                                                            </ul>
                                                        </div>
                                                        <a href="#none" class="btnClose" tabindex="-1"><img src="images/btn_close_tip.gif" alt="닫기"></a>
                                                        <span class="edge"></span>
                                                    </div>
                                                </div>
                                                <p class="help">(영문 대소문자/숫자/특수문자 중 2가지 이상 조합, 8자~16자)</p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">비밀번호 확인*</th>
                                            <td>
                                                <input id="memPw_confirm" name="memPw_confirm" autocomplete="off" maxlength="16" value="" type="password">
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row" id="nameTitle">이름*</th>
                                            <td>
                                                <input id="memName" name="memName" class="ec-member-name" placeholder="" maxlength="30" value="" type="text">
                                                <p class="help">기업회원은 대표자 이름을 입력해주세요.</p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">닉네임*</th>
                                            <td>
                                                <input id="memNick" name="memNick" class="ec-member-name" placeholder="" maxlength="30" value="" type="text">
                                                <p class="help">기업회원은 상호명 혹은 기업명을 입력해주세요.</p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">이메일*</th>
                                            <td>
                                                <input id="memEmail" name="memEmail" value="" type="text">
                                            </td>
                                        </tr>
                                        <tr class="mobile" id="entCrn" style="display: none">
                                            <th scope="row">사업자등록번호*</th>
                                            <td class="input-btn-box">
                                            	<input id="crn1" name="crn[]" maxlength="3" value="" type="text">
                                                -
                                                <input id="crn2" name="crn[]" maxlength="2" value="" type="text">
                                                -
                                                <input id="crn3" name="crn[]" maxlength="5" value="" type="text">
                                            </td>
                                        </tr>
                                        <tr class="mobile" id="entPhone" style="display: none">
                                            <th scope="row">사업자 연락처*</th>
                                            <td class="input-btn-box">
                                                <select id="mobile1" name="mobile[]">
													<option value="02">02</option>
													<option value="031">031</option>
													<option value="032">032</option>
													<option value="033">033</option>
													<option value="041">041</option>
													<option value="042">042</option>
													<option value="043">043</option>
													<option value="044">044</option>
													<option value="051">051</option>
													<option value="052">052</option>
													<option value="053">053</option>
													<option value="054">054</option>
													<option value="055">055</option>
													<option value="061">061</option>
													<option value="062">062</option>
													<option value="063">063</option>
													<option value="064">064</option>
													<option value="0502">0502</option>
													<option value="0503">0503</option>
													<option value="0504">0504</option>
													<option value="0505">0505</option>
													<option value="0506">0506</option>
													<option value="0507">0507</option>
													<option value="070">070</option>
													<option value="010">010</option>
													<option value="011">011</option>
													<option value="016">016</option>
													<option value="017">017</option>
													<option value="018">018</option>
													<option value="019">019</option>
													<option value="0508">0508</option>
                                                </select>
                                                -
                                                <input id="mobile2" name="mobile[]" maxlength="4" value="" type="text">
                                                -
                                                <input id="mobile3" name="mobile[]" maxlength="4" value="" type="text">
                                            </td>
                                        </tr>
                                        <tr id="entd_MainPic" style="display: none">
                                            <th>대표 이미지</th>
                                            <td>
                                                <input id="entdMainPic" name="entdMainPic" class="inputTypeImage" type="file" accept="image/*">
                                                <div id="preview1"></div>
                                            </td>
                                        </tr>
                                        <tr id="entd_Short" style="display: none">
                                            <th>간단한 회사 설명</th>
                                            <td>
                                                <textarea id="entdShort" name="entdShort" class="inputTypeLong" maxlength="255"></textarea>
                                                <div id="shortCharCount">0/255</div>
                                            </td>
                                        </tr>
                                        <tr id="entd_URL" style="display: none">
                                            <th>회사 홈페이지 URL</th>
                                            <td>
                                                <input id="entdURL" name="entdURL" class="inputTypeText" placeholder="" value="" type="text">
                                            </td>
                                        </tr>
                                        <tr id="entd_Intro" style="display: none">
                                            <th>자세한 회사 설명</th>
                                            <td>
                                                <textarea id="entdIntro" name="entdIntro" class="inputTypeLong" maxlength="1000"></textarea>
                                                <div id="IntrocharCount">0/1000</div>
                                            </td>
                                        </tr>
                                        <tr id="entd_IntroPic" style="display: none">
                                            <th>회사 로고 이미지</th>
                                            <td>
                                                <input id="entdIntroPic" name="entdIntroPic" class="inputTypeImage" type="file" accept="image/*">
                                                <div id="preview2"></div>
                                            </td>
                                        </tr>
                                        <tr id="entd_Pic1" style="display: none">
                                            <th>제품 이미지1*</th>
                                            <td>
                                                <input id="entdPic1" name="entdPic1" class="inputTypeImage" type="file" accept="image/*" required>
                                                <div id="preview3"></div>
                                            </td>
                                        </tr>
                                        <tr id="entd_Pic2" style="display: none">
                                            <th>제품 이미지2</th>
                                            <td>
                                                <input id="entdPic2" name="entdPic2" class="inputTypeImage" type="file" accept="image/*">
                                                <div id="preview4"></div>
                                            </td>
                                        </tr>
                                        <tr id="entd_Pic3" style="display: none">
                                            <th>제품 이미지3</th>
                                            <td>
                                                <input id="entdPic3" name="entdPic3" class="inputTypeImage" type="file" accept="image/*">
                                                <div id="preview5"></div>
                                            </td>
                                        </tr>
                                        <tr id="entd_Explain1" style="display: none">
                                            <th>제품 설명1*</th>
                                            <td>
                                                <textarea id="entdExplain1" name="entdExplain1" class="inputTypeLong" maxlength="500" required></textarea>
                                                <div id="ExplaincharCount1">0/500</div>
                                            </td>
                                        </tr>
                                        <tr id="entd_Explain2" style="display: none">
                                            <th>제품 설명2</th>
                                            <td>
                                                <textarea id="entdExplain2" name="entdExplain2" class="inputTypeLong" maxlength="500"></textarea>
                                                <div id="ExplaincharCount2">0/500</div>
                                            </td>
                                        </tr>
                                        <tr id="entd_Explain3" style="display: none">
                                            <th>제품 설명3</th>
                                            <td>
                                                <textarea id="entdExplain3" name="entdExplain3" class="inputTypeLong" maxlength="500"></textarea>
                                                <div id="ExplaincharCount3">0/500</div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div id="join_btn" class="ec-base-button">
                                <a href="#none" class="btnSubmitFix sizeM btn-primary full btn" id="join_submit_btn">회원가입</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>