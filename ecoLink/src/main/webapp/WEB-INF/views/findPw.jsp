<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link rel="stylesheet" href="css/find.css">
<link rel="stylesheet" href="css/common.css">
<script src="/js/jquery-3.6.4.min.js"></script>
<script>
window.addEventListener("DOMContentLoaded", function() {
	 var radiobox1 = document.getElementById("check_method1");
	 var radiobox2 = document.getElementById("check_method2");
	 var backgroundUrlOff = "/images/icon_input_radio_off.svg";
	 var backgroundUrlOn = "/images/icon_input_radio_on.svg";
	
	 // 기본적으로 check_method1의 radio 버튼이 클릭되어 있도록 설정
	 radiobox1.checked = true;
	 document.getElementById("email_view").style.display = "";
	 document.getElementById("id_view").style.display = "none";
	 radiobox1.style.backgroundImage = "url(" + backgroundUrlOn + ")";
	 radiobox2.style.backgroundImage = "url(" + backgroundUrlOff + ")";
	 
	 // check_method1의 radio 버튼 클릭 시 email_view 보이도록 설정
	 radiobox1.addEventListener("click", function() {
	// check_method2의 radio 버튼 클릭 해제
	radiobox2.checked = false;
	radiobox2.style.backgroundImage = "url(" + backgroundUrlOff + ")";
	radiobox1.style.backgroundImage = "url(" + backgroundUrlOn + ")";
	   document.getElementById("email_view").style.display = "";
	   document.getElementById("id_view").style.display = "none";
	 });
	
	 // check_method2의 radio 버튼 클릭 시 mobile_view 보이도록 설정
	 radiobox2.addEventListener("click", function() {
	   // check_method1의 radio 버튼 클릭 해제
	   radiobox1.checked = false;
	   radiobox1.style.backgroundImage = "url(" + backgroundUrlOff + ")";
	   radiobox2.style.backgroundImage = "url(" + backgroundUrlOn + ")";
	   document.getElementById("id_view").style.display = "";
	   document.getElementById("email_view").style.display = "none";
	 });
	  
	// 확인 버튼 클릭 시 처리
	$("#btn_submit").click(function() {
		var selected = $("input[name='check_method']:checked").val();
		if (selected === "1") {
		    $.ajax({
		        url: 'foundPw_Email',
		        type: 'post',
		        data: {
		        	'memType': $('#memType').val(),
		        	'memEmail': $('#memEmail').val()
		        },
		        dataType: 'text',
		        success: function(response) {
		            if (response !== "") {
		                alert("찾은 비밀번호: " + response);
		                window.location.href = "/login";
		            } else {
		                alert("해당 정보로 등록된 비밀번호를 찾을 수 없습니다.");
		            }
		        },
		        error: function(request, status, e) {
		            alert("비밀번호 찾기에 실패했습니다. 다음에 다시 시도해주세요.");
		            console.log("코드=" + request.status + "\n" + "메시지=" + request.responseText + "\n" + "error=" + e);
		        }
		    });
		} else if (selected === "2"){
		    $.ajax({
		        url: 'foundPw_Id',
		        type: 'post',
		        data: {
		        	'memType': $('#memType').val(),
		        	'memId': $('#memId').val()
		        },
		        dataType: 'text',
		        success: function(response) {
		            if (response !== "") {
		                alert("찾은 비밀번호: " + response);
		                window.location.href = "/login";
		            } else {
		                alert("해당 정보로 등록된 비밀번호를 찾을 수 없습니다.");
		            }
		        },
		        error: function(request, status, e) {
		            alert("비밀번호를 찾을 수 없습니다. 다시 시도해주세요.");
		            console.log("코드=" + request.status + "\n" + "메시지=" + request.responseText + "\n" + "error=" + e);
		        }
		    });
		}
	});
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
    <div class="page page-account">
        <div class="container-mypage">
            <div class="row">
                <div class="col-lg-4">
                    <form id="find_form" name="find_form" action="findPw" method="post" target="_self" enctype="multipart/form-data">
                        <!-- <input id="returnUrl" name="returnUrl" value="/member/id/find_id_result.html" type="hidden" /> -->
                        <div class="xans-element- xans-member xans-member-findid ec-base-box typeThin ">
                            <div class="findId">
                                <table class="type-full">
                                    <tbody>
                                        <tr>
                                            <th>회원유형</th>
                                            <td>
                                                <p class="member">
                                                    <select id="memType" name="memType" >
                                                        <option id="normal" value="normal" selected="selected">일반회원</option>
                                                        <option id="enter" value="enter">기업회원</option>
                                                    </select>
                                                </p>
                                            </td>
                                        </tr>
                                        <tr class="radio">
                                            <th>비밀번호 찾기</th>
                                            <td>
                                                <p class="check">
                                                    <input id="check_method1" name="check_method" value="1" type="radio" checked="checked" />
                                                    <label id="check_label1" for="check_method1">이메일로 찾기</label>
                                                    <input id="check_method2" name="check_method" value="2" type="radio"/>
                                                    <label id="check_label2" for="check_method2">
                                                        <span id="search_type_mobile_lable">아이디로 찾기</span>
                                                    </label>
                                                    
                                                </p>
                                            </td>
                                        </tr>
                                        <!--
                                        <tr>
                                            <th id="name_lable">이름</th>
                                            <td id="name_view" class="name">
                                                <input id="memName" name="memName" class="lostInput" placeholder="" value="" type="text" />
                                            </td>
                                        </tr>
                                        -->
                                        <tr id="email_view" class="email">
                                            <th>이메일로 찾기</th>
                                            <td>
                                                <input id="memEmail" name="memEmail" class="lostInput" placeholder="" value="" type="text" />
                                            </td>
                                        </tr>
                                        <tr id="id_view" class="email">
                                            <th>아이디로 찾기</th>
                                            <td>
                                                <input id="memId" name="memId" class="lostInput" placeholder="" value="" type="text" />
                                            </td>
                                        </tr> 
                                    </tbody>
                                </table>
                                <div class="ec-base-button gColumn">
                                    <a href="#none" id="btn_submit" class="btnSubmit btn btn-primary full">확인</a>
                                </div>
                            </div>
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