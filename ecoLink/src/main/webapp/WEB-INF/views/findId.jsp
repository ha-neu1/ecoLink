<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" href="css/find.css">
<link rel="stylesheet" href="css/common.css">
<script src="resources/js/jquery-3.6.4.min.js"></script>
<script>
window.addEventListener("DOMContentLoaded", function() {
	  var radiobox1 = document.getElementById("check_method1");
	  var radiobox2 = document.getElementById("check_method2");
	  var backgroundUrlOff = "/images/icon_input_radio_off.svg";
	  var backgroundUrlOn = "/images/icon_input_radio_on.svg";

	  // 기본적으로 check_method1의 radio 버튼이 클릭되어 있도록 설정
	  radiobox1.checked = true;
	  document.getElementById("email_view").style.display = "";
	  document.getElementById("mobile_view").style.display = "none";
	  radiobox1.style.backgroundImage = "url(" + backgroundUrlOn + ")";
	  radiobox2.style.backgroundImage = "url(" + backgroundUrlOff + ")";

	  // check_method1의 radio 버튼 클릭 시 email_view 보이도록 설정
	  radiobox1.addEventListener("click", function() {
		// check_method2의 radio 버튼 클릭 해제
		radiobox2.checked = false;
		radiobox2.style.backgroundImage = "url(" + backgroundUrlOff + ")";
		radiobox1.style.backgroundImage = "url(" + backgroundUrlOn + ")";
	    document.getElementById("email_view").style.display = "";
	    document.getElementById("mobile_view").style.display = "none";
	  });

	  // check_method2의 radio 버튼 클릭 시 mobile_view 보이도록 설정
	  radiobox2.addEventListener("click", function() {
	    // check_method1의 radio 버튼 클릭 해제
	    radiobox1.checked = false;
	    radiobox1.style.backgroundImage = "url(" + backgroundUrlOff + ")";
	    radiobox2.style.backgroundImage = "url(" + backgroundUrlOn + ")";
	    document.getElementById("mobile_view").style.display = "";
	    document.getElementById("email_view").style.display = "none";
	  });
	  
	  // 확인 버튼 클릭 시 처리
	  document.getElementById("btn_submit").addEventListener("click", function() {
	    var checkMethod = document.querySelector("input[name='check_method']:checked").value;
	    var name = document.getElementById("memName").value;

	    // 이메일로 찾기
	    if (checkMethod === "1") {
	      var email = document.getElementById("memEmail").value;

	      // AJAX 요청을 이용하여 서버로 데이터 전송
	      $.ajax({
	        type: "POST",
	        url: "findIdByEmail", // 이메일로 아이디 찾기를 담당하는 컨트롤러의 URL
	        data: { name: name, email: email },
	        success: function(response) {
	          // 서버에서 반환한 결과(response)를 팝업(alert)으로 띄워줌
	          alert(response);
	        },
	        error: function(xhr, status, error) {
	          alert("아이디 찾기에 실패했습니다. 다시 시도해주세요.");
	        }
	      });
	    }
	    // 휴대폰 번호로 찾기
	    else if (checkMethod === "2") {
	      var mobile1 = document.getElementById("mobile1").value;
	      var mobile2 = document.getElementById("mobile2").value;
	      var mobile3 = document.getElementById("mobile3").value;
	      var phoneNumber = mobile1 + "-" + mobile2 + "-" + mobile3;

	      // AJAX 요청을 이용하여 서버로 데이터 전송
	      $.ajax({
	        type: "POST",
	        url: "findIdByPhoneNumber", // 휴대폰 번호로 아이디 찾기를 담당하는 컨트롤러의 URL
	        data: { name: name, phoneNumber: phoneNumber },
	        success: function(response) {
	          // 서버에서 반환한 결과(response)를 팝업(alert)으로 띄워줌
	          alert(response);
	        },
	        error: function(xhr, status, error) {
	          alert("아이디 찾기에 실패했습니다. 다시 시도해주세요.");
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
                <a href="/">
                    <img src="/images/logo1.png" alt="" class="max">
                </a>
            </div>
</div>
<div id="contents">
    <div class="page page-account">
        <div class="container-mypage">
            <div class="row">
                <div class="col-lg-4">
                    <form id="find_form" name="find_form" action="findId" method="post" target="_self" enctype="multipart/form-data">
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
                                                        <option id="normal" value="일반회원" selected="selected">일반회원</option>
                                                        <option id="enter" value="기업회원">기업회원</option>
                                                    </select>
                                                </p>
                                            </td>
                                        </tr>
                                        <tr class="radio">
                                            <th>아이디 찾기</th>
                                            <td>
                                                <p class="check">
                                                    <input id="check_method1" name="check_method" value="1" type="radio" checked="checked" />
                                                    <label id="check_label1" for="check_method1">이메일로 찾기</label>
                                                    <input id="check_method2" name="check_method" value="2" type="radio"/>
                                                    <label id="check_label2" for="check_method2">
                                                        <span id="search_type_mobile_lable">휴대폰 번호로 찾기</span>
                                                    </label>
                                                </p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th id="name_lable">이름</th>
                                            <td id="name_view" class="name">
                                                <input id="memName" name="memName" class="lostInput" placeholder="" value="" type="text" />
                                            </td>
                                        </tr>
                                        <tr id="email_view" class="email">
                                            <th>이메일로 찾기</th>
                                            <td>
                                                <input id="memEmail" name="memEmail" class="lostInput" placeholder="" value="" type="text" />
                                            </td>
                                        </tr>
                                        <tr id="mobile_view" class="mobile">
                                            <th>휴대폰 번호로 찾기</th>
                                            <td>
                                                <select id="mobile1" name="mobile[]">
                                                    <option value="010">010</option>
                                                    <option value="011">011</option>
                                                    <option value="016">016</option>
                                                    <option value="017">017</option>
                                                    <option value="018">018</option>
                                                    <option value="019">019</option>
                                                </select>
                                                 -
                                                <input id="mobile2" name="mobile2" class="mobile2" placeholder="" maxlength="4" value="" type="text" />
                                                 -
                                                <input id="mobile3" name="mobile3" class="mobile2" placeholder="" maxlength="4" value="" type="text" />
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                                <div class="ec-base-button gColumn">
                                    <a href="#none" id="btn_submit" class="btnSubmit btn btn-primary full" onclick="findId.action('upcyclist' , 'kcp'); return false;">확인</a>
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