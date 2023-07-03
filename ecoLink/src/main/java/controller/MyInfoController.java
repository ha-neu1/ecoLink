package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyInfoController {
	
	@RequestMapping("/myInfo")
    public String myInfo() {
        return "MyInfo";
    }
	
	@RequestMapping("/updateUser")
    public String updateUser() {
        return "MyInfoUpdate";
    }

}
