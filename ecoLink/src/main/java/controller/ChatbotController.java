package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import service.ChatbotServiceImpl;

@Controller
public class ChatbotController {

	@Autowired
	@Qualifier("chatbotServiceImpl")
	ChatbotServiceImpl service;

	// 기본+이미지+멀티링크답변 분석한 뷰
	@RequestMapping("/chatbot")
	public String chatbotajax() {
		return "chatbot";
	}

	@RequestMapping("/chatbotprocess")
	@ResponseBody
	public String chatbotajaxprocess(String request, String event) {
		String response = "";

		// 웰컴 메시지인 경우에는 "event": "open"으로 응답
		if ("웰컴메시지".equals(event)) {
			response = service.test(request, "open");
		} else {
			response = service.test(request, "send");
		}

		return response;
	}
}
