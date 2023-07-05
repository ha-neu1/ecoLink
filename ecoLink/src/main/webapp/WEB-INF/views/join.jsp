<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/join.css">
<link rel="stylesheet" href="css/common.css">
<script src="/js/jquery-3.6.4.min.js"></script>
<script>
document.addEventListener("DOMContentLoaded", function(event) {
	var inputElement = document.getElementById("passwd");
	var tooltipElement = document.querySelector(".ec-base-tooltip");

	//비밀번호 입력란을 클릭하면 툴팁 열기
	document.getElementById('passwd').addEventListener('click', function() {
	    var tooltip = document.querySelector('.ec-base-tooltip.typeUpper');
	    tooltip.style.display = 'block';
	});
	
	//툴팁 닫기 버튼을 클릭하면 툴팁 닫기
	document.querySelector('.btnClose').addEventListener('click', function() {
	    var tooltip = document.querySelector('.ec-base-tooltip.typeUpper');
	    tooltip.style.display = 'none';
	});
	
	// 아무 곳이나 클릭하면 툴팁 닫기
	  document.addEventListener('click', function(event) {
	    var tooltip = document.querySelector('.ec-base-tooltip.typeUpper');
	    // 클릭된 요소가 비밀번호 입력란이거나 툴팁 내부 요소인 경우 닫지 않음
	    if (event.target === inputElement) {
	      return;
	    }
	    tooltip.style.display = 'none';
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
        <div class="page">
            <div class="container-mypage">
                <div class="row">
                    <div class="col-lg-4">
                        <form id="join_form" name="join_form" action="join" method="post" target="_self" enctype="multipart/form-data">
                            <div class="ec-base-table typeWrite">
                                <table class="type-full">
                                    <tbody>
                                        <tr>
                                            <th scope="row">아이디*</th>
                                            <td>
                                                <input id="member_id" name="member_id" class="inputTypeText" placeholder="" value="" type="text">
                                                <span id="idMsg"></span>
                                                <p class="help">(영문 소문자/숫자, 4~16자)</p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">비밀번호*</th>
                                            <td>
                                                <div class="eTooltip">
                                                    <input id="passwd" name="passwd" autocomplete="off" maxlength="16" 0="disabled" value="" type="password">
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
                                                <input id="user_passwd_confirm" name="user_passwd_confirm" autocomplete="off" maxlength="16" 0="disabled" value="" type="password">
                                                <span id="pwConfirmMsg"></span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row" id="nameTitle">이름*</th>
                                            <td>
                                                <input id="name" name="name" class="ec-member-name" placeholder="" maxlength="30" value="" type="text">
                                                <p class="help">14세 미만 사용자는 법정대리인 동의가 필요합니다.</p>
                                            </td>
                                        </tr>
                                        <tr class=" mobile">
                                            <th scope="row">휴대전화*</th>
                                            <td class="input-btn-box">
                                                <select id="mobile1" name="mobile[]">
                                                    <option value="010">010</option>
                                                    <option value="011">011</option>
                                                    <option value="016">016</option>
                                                    <option value="017">017</option>
                                                    <option value="018">018</option>
                                                    <option value="019">019</option>
                                                </select>
                                                -
                                                <input id="mobile2" name="mobile[]" maxlength="4" value="" type="text">
                                                -
                                                <input id="mobile3" name="mobile[]" maxlength="4" value="" type="text">
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">이메일*</th>
                                            <td>
                                                <input id="email1" name="email1" value="" type="text">
                                                <span id="emailMsg"></span>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="ec-base-button">
                                <a href="#none" class="btnSubmitFix sizeM btn-primary full btn" onclick="memberJoinAction()">회원가입</a>
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