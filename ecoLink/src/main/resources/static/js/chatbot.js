
$(document).ready(function() {
	const chatIcon = $(".chatIcon");
	const chatBox = $(".chatbox");

	// chatbox를 처음에는 숨깁니다.
	chatBox.hide();

	// chatIcon을 클릭할 때 chatbox를 나타내거나 숨깁니다.
	chatIcon.click(function(event) {
		event.preventDefault(); // 기본 동작인 링크 이동을 막습니다.
		event.stopPropagation(); // 클릭 이벤트가 문서로 전파되지 않도록 합니다.

		if (chatBox.is(":visible")) {
			chatBox.hide();
		} else {
			chatBox.show();
		}
	});

	// chatbox 외부를 클릭할 때 chatbox를 숨깁니다.
	$(document).click(function() {
		if (chatBox.is(":visible")) {
			chatBox.hide();
		}
	});
	// chatbox 내부 클릭 시 이벤트 전파를 막습니다.
	chatBox.click(function(event) {
		event.stopPropagation();
	});

	// 1 type button 클릭하면 request 질문을 response 붙여넣는다
	$("input:button").on("click", function() {
		const inputValue = $("#request").val();

		if ($(this).val() === "질문" && inputValue.trim() === "") {
			// 입력값이 빈 칸인 경우 "웰컴메시지" 동작 실행
			$("input[value='웰컴메시지']").click();
			return; // "웰컴메시지" 동작 후에는 함수 종료
		}
		$("#response").append('<div class="message user"><div class="message-content">' + $("#request").val() + '</div></div>');
		// 사용자 질문을 오른쪽에 말풍선 형태로 추가

		$.ajax({
			url: "/chatbotprocess",
			data: { "request": $("#request").val(), "event": $(this).val() },
			type: 'get',
			dataType: 'json',
			success: function(server) {
				let bubbles = server.bubbles;
				let botResponse = ''; // 챗봇 답변을 저장할 변수
				for (let b in bubbles) {
					if (bubbles[b].type === 'text') {
						botResponse += '<div class="message-content">' + bubbles[b].data.description + '</div>';
						if (bubbles[b].data.url != null) {
							botResponse += '<div class="message-content"><a href=' + bubbles[b].data.url + '>' + bubbles[b].data.description + '</a></div>';
						}
					} else if (bubbles[b].type === 'template' && bubbles[b].data.cover.type === 'text') {
						botResponse += '<div class="message-content">' + bubbles[b].data.cover.data.description + '</div>';
						for (let c in bubbles[b].data.contentTable) {
							for (let d in bubbles[b].data.contentTable[c]) {
								let link = bubbles[b].data.contentTable[c][d].data.title;
								let href = bubbles[b].data.contentTable[c][d].data.data.action.data.url;
								botResponse += '<div class="message-content"><a href=' + href + '>' + link + '</a></div>';
							}
						}
					}
				}
				$("#response").append('<div class="message bot">' + botResponse + '</div>');
				$("#response").scrollTop($("#response")[0].scrollHeight);
			},
			error: function(e) {
				alert(e);
			}
		});
	});
});

