<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/common.css">
<script src="/js/jquery-3.6.4.min.js"></script>
<script>
//아이디 저장 체크박스
window.addEventListener("DOMContentLoaded", function() {
  var checkbox = document.getElementById("member_check_save_id0");
  var backgroundUrlOff = "/images/icon_input_checkbox_off.svg";
  var backgroundUrlOn = "/images/icon_input_checkbox_on.svg";

  checkbox.addEventListener("change", function() {
    if (checkbox.checked) {
      checkbox.style.backgroundImage = "url(" + backgroundUrlOn + ")";
      // 아이디 정보를 저장하는 로직 추가
    } else {
      checkbox.style.backgroundImage = "url(" + backgroundUrlOff + ")";
      // 저장된 아이디 정보를 제거하는 로직 추가
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
                    <form id="login_form" name="" action="login" method="post">
                        <div class="xans-element- xans-member xans-member-login ec-base-box typeThin">
                            <div class="login">
                                <table class="type-full">
                                    <tbody>
                                        <tr>
                                            <th>아이디</th>
                                            <td><input id="memId" name="memId" class="inputTypeText" placeholder="" value="" type="text"></td>
                                        </tr>
                                        <tr>
                                            <th>비밀번호</th>
                                            <td><input id="memPw" name="memPw" autocomplete="off" value="" type="password"></td>
                                        </tr>
                                        <tr class="checkbox">
                                            <td class="id-pw-td">
                                                <div class="id-save">
                                                    <input id="member_check_save_id0" name="check_save_id" value="T" type="checkbox">
                                                    <label for="member_check_save_id0">아이디 저장</label>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <a href="#none" class="btn-primary full" id="login_submit_btn">로그인</a>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                                <div>
                                    <ul class="find">
                                        <li><a href="/join">회원가입</a></li>
                                        <li><a href="/findId">아이디</a>/<a href="/findPw">비밀번호 </a>찾기</li>
                                    </ul>
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