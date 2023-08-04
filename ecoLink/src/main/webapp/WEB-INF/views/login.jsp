<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">
<title>로그인</title>
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/common.css">
<script src="/js/jquery-3.6.4.min.js"></script>
<script>
$(document).ready(function(){
	var logininfo = '<%=session.getAttribute("logininfo")%>';
	if (logininfo != "null") {
		window.history.forward();
	}
	
	const loginForm = document.getElementById("login_form");	
	const loginBtn = document.getElementById("login_submit_btn");
	const loginId = document.getElementById("memId");
	const loginPassword= document.getElementById("memPw");
	//const loginErrorMsg ="아이디 또는 비밀번호를 잘못입력했습니다. 다시확인해주세요. ";
	/* const loginLink= "http://localhost:8081/team_running/team1.html"; */
	
	$("#login_submit_btn").on('click', function(event){
		    //서브밋 우선 막기.
		 	event.preventDefault();

			//아이디, 비번에 빈칸 입력시 경고창. + 둘 다 입력시 submit
		    if (loginId.value.trim() === "" || loginPassword.value.trim() === "") {
		      alert("빈칸을 입력해주세요"); 
		    } else {
		      loginForm.submit(); 
		    }
		});//loginBtn click

	  //엔터로 다음칸 가기 : (순서) 아이디-> pw-> 로그인버튼
	  loginId.addEventListener("keydown", function(event) {
	    if (event.keyCode === 13) {
	      event.preventDefault(); 
	      loginPassword.focus();
	    }
	  });//keydown

	  loginPassword.addEventListener("keydown", function(event) {
	    if (event.keyCode === 13) {
	      event.preventDefault(); 
	      loginBtn.click(); 
	    }
	    });//keydown
	    
});//ready end

//아이디 저장 체크박스
window.addEventListener("DOMContentLoaded", function() {
  var checkbox = document.getElementById("member_check_save_id0");
  var backgroundUrlOff = "/images/icon_input_checkbox_off.svg";
  var backgroundUrlOn = "/images/icon_input_checkbox_on.svg";
  var inputId = document.getElementById("memId");

  checkbox.addEventListener("change", function() {
    if (checkbox.checked) {
      checkbox.style.backgroundImage = "url(" + backgroundUrlOn + ")";
      // 아이디 정보를 저장
      var savedId = inputId.value;
      localStorage.setItem("savedId", savedId);
    } else {
      checkbox.style.backgroundImage = "url(" + backgroundUrlOff + ")";
      // 저장된 아이디 정보를 제거
      localStorage.removeItem("savedId");
    }
  });

  // 페이지 로드 시 저장된 아이디 정보가 있으면 입력란에 설정
  var savedId = localStorage.getItem("savedId");
  if (savedId) {
    inputId.value = savedId;
    checkbox.checked = true;
    checkbox.style.backgroundImage = "url(" + backgroundUrlOn + ")";
  }
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
                    <form id="login_form" name="" action="/login" method="post">
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
                                            	<h5 style="color:red">${loginfail}</h5>
                                                <a href="#" class="btn-primary full" id="login_submit_btn" onclick="return login()">로그인</a>
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
<%
String sessionid = (String) session.getAttribute("sessionid");
if (sessionid != null && (sessionid.equals("비밀번호가 다릅니다.") || sessionid.equals("가입되지 않은 아이디입니다."))) {
%>
<script>
window.alert("${sessionid}");
</script>
<% session.setAttribute("sessionid", null);} %>
</html>