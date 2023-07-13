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
// 일반회원, 기업회원 입력 구분
$(document).ready(function() {
	const join_form = document.getElementById("join_form");	
    const join_btn = document.getElementById("join_submit_btn");
    const join_id = document.getElementById("memId");
    const join_pw = document.getElementById("memPw");
    const join_pw_confirm = document.getElementById("memPw_confirm");
    const join_email = document.getElementById("memEmail");
    const join_name = document.getElementById("memName");
    
    // 회원유형 선택 시 처리
    $(document).on('change', '#searchType', function() {
        var selected = $(this).val();
        if (selected === "buis") {
            $("#entPhone").show();
            $("#entCrn").show();
        } else if (selected === "indi") {
            $("#entPhone").hide();
            $("#entCrn").hide();
        }
    });
    
    // memType에 따라 memNick을 설정
    var count = $("select#searchType option:selected").index() + 1;
    var memNick = (selected === "enter" ? "기업회원" : "일반회원") + count;
    $("input#memNick").val(memNick);

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

    // btnClose 클릭 시 이벤트 처리
    $(document).on('click', '.btnClose', function(e) {
        e.preventDefault();
        $('.ec-base-tooltip').fadeOut(100);
    });
    
    // memberPw_confirm 입력란에서 값 변경 시 이벤트 처리
    $(document).on('click', 'input:not(#memPw_confirm)', function(e) {
        var pwConfirmValue = $('#memPw_confirm').val();
        var pwValue = $('#memPw').val();
        if (pwValue !== '' && pwConfirmValue !== '' && pwConfirmValue !== pwValue) {
            alert("비밀번호가 일치하지 않습니다.");
        }
    });

    $("#join_submit_btn").on('click', function(event) {
  	  event.preventDefault();
  	  // 아이디, 비밀번호, 이메일, 이름에 빈칸 입력 시 경고창 표시
  	  if (join_id.value.trim() === "" || join_pw.value.trim() === "" || join_pw_confirm.value.trim() === "" || join_email.value.trim() === "" || join_name.value.trim() === "") {
  	    alert("빈칸을 입력해주세요");
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
  	          join_form.submit();
  	          alert("가입이 완료되었습니다.");
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
  	    }); //ajax
  	  } //else
  	}); //joinBtn click
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
                        <form id="join_form" name="join_form" action="/join" method="post">
                            <div class="ec-base-table typeWrite">
                                <table class="type-full">
                                    <tbody>
                                        <tr>
                                            <th>회원유형</th>
                                            <td>
                                                <p class="member">
                                                    <select id="searchType" name="searchType" >
                                                        <option id="normal" value="normal" selected="selected">일반회원</option>
                                                        <option id="enter" value="enter">기업회원</option>
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
                                                <input id="memPw_confirm" name="memPw_confirm" autocomplete="off" maxlength="16" 0="disabled" value="" type="password">
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row" id="nameTitle">이름*</th>
                                            <td>
                                                <input id="memName" name="memName" class="ec-member-name" placeholder="" maxlength="30" value="" type="text">
                                                <p class="help">14세 미만 사용자는 법정대리인 동의가 필요합니다.</p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">이메일*</th>
                                            <td>
                                                <input id="memEmail" name="memEmail" value="" type="text">
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
                                        <tr class="mobile" id="entCrn" style="display: none">
                                            <th scope="row">사업자등록번호*</th>
                                            <td class="input-btn-box">
                                                <input id="crn" name="crn" value="" type="text">
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