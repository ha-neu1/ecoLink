package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/main")
    public String mainPage() {
        return "footer"; // jsp 파일의 이름
    }
}
