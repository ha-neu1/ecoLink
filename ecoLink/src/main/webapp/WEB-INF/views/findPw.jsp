<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
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
                    <form id="find_form" name="find_form" action="findPw" method="post" target="_self" enctype="multipart/form-data">
                        <!-- <input id="returnUrl" name="returnUrl" value="/member/id/find_pw_result.html" type="hidden" /> -->
                        <div class="xans-element- xans-member xans-member-findpw ec-base-box typeThin ">
                            <div class="findPw">
                                <table class="type-full">
                                    <tbody>
                                        <tr>
                                            <th>회원유형</th>
                                            <td>
                                                <p class="member">
                                                    <select id="memType" name="memType" >
                                                        <option id="indi" value="일반회원" selected="selected">일반회원</option>
                                                        <option id="buis" value="기업회원">기업회원</option>
                                                    </select>
                                                </p>
                                            </td>
                                        </tr>
                                        <tr class="radio">
                                            <th>비밀번호 찾기</th>
                                            <td>
                                                <p class="check">
                                                    <input id="check_method1" name="check_method" value="1" type="radio" checked="checked" />
                                                    <label for="check_method1">이메일로 찾기</label>
                                                    <input id="check_method2" name="check_method" value="2" type="radio" />
                                                    <label for="check_method2">
                                                        <span id="search_type_mobile_lable" style="display:inline;">휴대폰 번호로 찾기</span>
                                                    </label>
                                                </p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th id="name_lable">이름</th>
                                            <td id="name_view" class="name">
                                                <input id="name" name="name" class="lostInput" placeholder="" value="" type="text" />
                                            </td>
                                        </tr>
                                        <tr id="email_view" class="email" style="display:none;">
                                            <th>이메일로 찾기</th>
                                            <td>
                                                <input id="email" name="email" class="lostInput" placeholder="" value="" type="text" />
                                            </td>
                                        </tr>
                                        <tr id="mobile_view" class="mobile" style="display:none;">
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
                                    <a href="#none" class="btnSubmit btn btn-primary full" onclick="findPw.action('upcyclist' , 'kcp'); return false;">확인</a>
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