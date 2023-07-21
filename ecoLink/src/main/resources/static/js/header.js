// 로그아웃 기능(현재 페이지에 남기)
function logout() {
	$.ajax({
		type: "POST",
		url: "/logout",
		success: function() {
			location.reload(); // 현재 페이지 리로드
		},
		error: function() {
			alert("로그아웃 도중 오류가 발생했습니다.");
		}
	});
}
