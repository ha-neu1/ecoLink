<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<link rel="stylesheet" href="css/MyInfoUpdate.css">
</head>
<script src="js/jquery-3.6.4.min.js"></script>
<script>
	$(document).ready(function() {
		const pw = document.getElementById("memPw");
		
		function isPasswordValid(password) {
	         // 영문 대소문자/숫자/특수문자 중 2가지 이상 조합, 8자~16자
	         const regex = /^(?=.*[a-zA-Z])(?=.*\d|\W).{8,16}$/;
	         return regex.test(password);
	     }
		
		// 다른 곳 클릭 시 비밀번호 유효성 검사
		$(pw).on('blur', function() {
		    var pwValue = $(this).val();
		    if (!isPasswordValid(pwValue)) {
		        alert("비밀번호 입력 조건을 만족해주세요.");
		    }
		});
		
		// 비밀번호 일치 여부 확인
	      $(document).on('click', 'input:not(#memPw_confirm)', function(e) {
	         var pwConfirmValue = $("#memPw_confirm").val();
	         var pwValue = $("#memPw").val();
	         if (pwValue !== '' && pwConfirmValue !== '' && pwConfirmValue !== pwValue) {
	             alert("비밀번호가 일치하지 않습니다.");
	         }
	      });
		
				$("#updateBtn").on(
						'click',
						function(e) {
							e.preventDefault();
							
							var memId = $("#memId").val();
							var memPw =  $("#memPw").val();
							var memPw_confirm = $("#memPw_confirm").val();
				            var memNick = $("#memNick").val();
				            
				            if (memPw === "") {
				                alert("비밀번호를 입력해 주세요.");
				            } else if(memPw_confirm === ""){
				            	alert("비밀번호를 확인해 주세요.");
				            } else if(memNick === ""){
				            	alert("닉네임을 입력해 주세요.");
				            } else if(memPw !== memPw_confirm) {
				            	alert("비밀번호가 일치하지 않습니다.");
			            	} else {
				            
							$.ajax({
								url : '/updateUserInfo',
								data : {
									'memId' : memId,
									'memPw' : memPw,
									'memNick' : memNick
								},
								type : 'post',
								success : function(res) {
									alert("회원정보 수정이 완료되었습니다.");
									location.href = "/userInfo";
								},
								error : function(request, status, e) {
									alert("코드=" + request.status + "\n메시지="
											+ request.responseText + "\nerror="
											+ e);
								}
							});
				            }
						});
			});
</script>
<body>
	<%@ include file="header.jsp"%>

	<div class="containers">
		<article>
			<aside id="sidebar">
				<div id='menus'>
					<ul id='menusList'>
						<li class='menuItem'><a href="/userInfo">MY 정보</a></li>
						<li class='menuItem'><a href="/myBrandLike">브랜드 북마크</a></li>
						<li class='menuItem'><a href="/myBoardLike">좋아요한 글</a></li>
						<li class='menuItem'><a href="/myBoard">내가 쓴 글</a></li>
					</ul>
				</div>
			</aside>
			<form name="signUpForm">
				<div class='forminheader'>
					<h2>개인정보 수정</h2>
					<p>고객님의 개인정보에 포함된 닉네임과 비밀번호를 수정하실 수 있습니다.</p>
				</div>
				<div class='formindiv'>
					<p>아이디</p>
					<div id=id_div>
						<input type="text" name="memId" id="memId" value="${loginUser.memId}" disabled>
					</div>
				</div>

				<div class='formindiv'>
					<p>비밀번호*</p>
					<label><input type="password" name="memPw" id="memPw" value="${loginUser.memPw}" maxlength="16"></label>
					<a class="help">(영문 대소문자/숫자/특수문자 중 2가지 이상 조합, 8자~16자)</a>
				</div>
				
				<div class='formindiv'>
					<p>비밀번호 확인*</p>
					<label><input type="password" name="memPw_confirm" id="memPw_confirm" value="" autocomplete="off" maxlength="16"></label>
				</div>

				<div class='formindiv'>
					<p>이름</p>
					<input type="text" name="memName" id="memName" value="${loginUser.memName}" disabled>
				</div>

				<div class='formindiv'>
					<p>닉네임*</p>
					<input type="text" name="memNick" id="memNick" value="${loginUser.memNick}" maxlength="10">
				</div>

				<div class='formindiv'>
					<p>E-mail</p>
					<input type="tel" name="memEmail" id="memEmail" value="${loginUser.memEmail}" disabled>
				</div>

				<div class='formindiv'>
					<button type="button" id="updateBtn">수정하기</button>
				</div>
			</form>
		</article>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>