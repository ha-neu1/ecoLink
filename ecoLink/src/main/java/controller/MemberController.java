package controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import dto.EnterpriseDTO;
import dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.MemberService;

@Controller
public class MemberController {

	@Autowired
    private MemberService service;
    
	//로그인
	@GetMapping("/login")
	public String login(@SessionAttribute(name = "logininfo", required = false)MemberDTO dto, HttpServletResponse response, HttpServletRequest request) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
			
		if (dto == null) {
	    	return "login";
		} else {
			return "redirect:" + request.getHeader("Referer");
		}	
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute MemberDTO memberDTO, HttpServletResponse response,
	                    HttpServletRequest request, Model model) {
	    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	    response.setHeader("Pragma", "no-cache");
	    response.setDateHeader("Expires", 0);

	    // 세션 정보가 없을 때만 로그인 처리
	    HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("logininfo") == null) {
	        MemberDTO dto = service.login(memberDTO);
	        if (dto != null) {
	            session = request.getSession();
	            session.setAttribute("logininfo", dto);
	            return "redirect:/main";
	        } else {
	            model.addAttribute("loginfail", "아이디 또는 비밀번호가 맞지 않습니다.");
	            return "login";
	        }
	    } else {
	        // 이미 로그인 상태라면 메인 페이지로 리다이렉트
	        return "redirect:/main";
	    }
	}

    // 로그아웃
	@RequestMapping("/logout")
	public String logout(HttpServletResponse response, HttpServletRequest request) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
	    HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate();
	    }

	    return "login";
	}
    
    // 회원가입
    @GetMapping("/join")
    public String join() {
        return "join";
    }

    //회원가입 구현
    @PostMapping("/join")
    @ResponseBody
    public String memberjoin(@ModelAttribute("member") MemberDTO member) {
        // memType 값 설정 ("일반회원" -> "normal", "기업회원" -> "enter" 로 변환)
        String memType = member.getMemType();
        if (memType.equals("일반회원")) {
            member.setMemType("normal");
        } else if (memType.equals("기업회원")) {
            member.setMemType("enter");
        }

        /*// memNick 값 설정
        String memNick = service.generateNextMemNick(member.getMemType());
        member.setMemNick(memNick);*/
         
        // 회원 등록
        service.addMember(member);

        return "redirect:/login";
    }
    
    @PostMapping("/enterjoin")
    @ResponseBody
    public String enterprisejoin(@RequestPart(name = "enter") EnterpriseDTO enter,
    		@RequestPart(name = "img1", required = false) MultipartFile file1,
			@RequestPart(name = "img2", required = false) MultipartFile file2,
			@RequestPart(name = "img3", required = false) MultipartFile file3,
			@RequestPart(name = "img4", required = false) MultipartFile file4,
			@RequestPart(name = "img5", required = false) MultipartFile file5, HttpServletResponse response)
			throws IllegalStateException, IOException {
    	
		String savePath = "";
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			savePath = "c:/brand/";
		} else if (os.contains("linux")) {
			savePath = "/usr/mydir/brand/";
		} else {
			savePath = "c:/brand/";
		}
		
		if (file1 != null) {
			MultipartFile entdMainPicImg = file1;
			String newFileName1 = null;

			String originalName1 = entdMainPicImg.getOriginalFilename();
			String beforeExt1 = originalName1.substring(0, originalName1.indexOf("."));
			String ext1 = originalName1.substring(originalName1.indexOf("."));

			newFileName1 = beforeExt1 + "(" + UUID.randomUUID().toString() + ")" + ext1;
			entdMainPicImg.transferTo(new File(savePath + newFileName1));

			enter.setEntdMainPic("/brand/" + newFileName1);
		}

		if (file2 != null) {
			MultipartFile entdIntroPicImg = file2;
			String newFileName2 = null;

			String originalName2 = entdIntroPicImg.getOriginalFilename();
			String beforeExt2 = originalName2.substring(0, originalName2.indexOf("."));
			String ext2 = originalName2.substring(originalName2.indexOf("."));

			newFileName2 = beforeExt2 + "(" + UUID.randomUUID().toString() + ")" + ext2;
			entdIntroPicImg.transferTo(new File(savePath + newFileName2));

			enter.setEntdIntroPic("/brand/" + newFileName2);
		}

		if (file3 != null) {
			MultipartFile entdPic1Img = file3;
			String newFileName3 = null;

			String originalName3 = entdPic1Img.getOriginalFilename();
			String beforeExt3 = originalName3.substring(0, originalName3.indexOf("."));
			String ext3 = originalName3.substring(originalName3.indexOf("."));

			newFileName3 = beforeExt3 + "(" + UUID.randomUUID().toString() + ")" + ext3;
			entdPic1Img.transferTo(new File(savePath + newFileName3));

			enter.setEntdPic1("/brand/" + newFileName3);
		}

		if (file4 != null) {
			MultipartFile entdPic2Img = file4;
			String newFileName4 = null;

			String originalName4 = entdPic2Img.getOriginalFilename();
			String beforeExt4 = originalName4.substring(0, originalName4.indexOf("."));
			String ext4 = originalName4.substring(originalName4.indexOf("."));

			newFileName4 = beforeExt4 + "(" + UUID.randomUUID().toString() + ")" + ext4;
			entdPic2Img.transferTo(new File(savePath + newFileName4));

			enter.setEntdPic2("/brand/" + newFileName4);
		}

		if (file5 != null) {
			MultipartFile entdPic3Img = file5;
			String newFileName5 = null;

			String originalName5 = entdPic3Img.getOriginalFilename();
			String beforeExt5 = originalName5.substring(0, originalName5.indexOf("."));
			String ext5 = originalName5.substring(originalName5.indexOf("."));

			newFileName5 = beforeExt5 + "(" + UUID.randomUUID().toString() + ")" + ext5;
			entdPic3Img.transferTo(new File(savePath + newFileName5));

			enter.setEntdPic3("/brand/" + newFileName5);
		}
		
    	System.out.println("enter.getEntCrn() : " + enter.getEntCrn());

    	String modifiedIntro = convertToHtmlFormat(enter.getEntdIntro());
        enter.setEntdIntro(modifiedIntro);
        
    	String modifiedShort = convertToHtmlFormat(enter.getEntdShort());
        enter.setEntdShort(modifiedShort);
        
        String modifiedExplain1 = convertToHtmlFormat(enter.getEntdExplain1());
        enter.setEntdExplain1(modifiedExplain1);

        String modifiedExplain2 = convertToHtmlFormat(enter.getEntdExplain2());
        enter.setEntdExplain2(modifiedExplain2);

        String modifiedExplain3 = convertToHtmlFormat(enter.getEntdExplain3());
        enter.setEntdExplain3(modifiedExplain3);
        
        System.out.println("enter.toString() : " + enter.toString());
        //enter.toString() : EnterpriseDTO [entCrn=null, entPhone=null, 
        //memId=test3, entdMainPic=null, entdShort=null, entdURL=null, entdIntro=null, 
        //entdIntroPic=null, entdPic1=null, entdPic2=null, entdPic3=null, entdExplain1=null, entdExplain2=null, entdExplain3=null, entdConfirm=false]
		
    	service.addEnterprise(enter);
   
        return "redirect:/login";
    }
    
    public String convertToHtmlFormat(String text) {
        String htmlText = text.replace("\r\n", "<br>")
                              .replace("\n", "<br>")
                              .replace("\r", "<br>");
//                              .replace("\"", "&quot;")
//                              .replace("'", "&#39;")
//                              .replace("<", "&lt;")
//                              .replace(">", "&gt;");
        return htmlText;
    }
    
    //id, email 중복여부
    @RequestMapping(value="/ismemberexist", produces = {"application/json;charset=utf-8"})
    public @ResponseBody String isMemberexist(@RequestParam("inputId") String inputId, @RequestParam("inputEmail") String inputEmail) {
    	int idresult = service.isMemberIdExist(inputId);
    	int emailresult = service.isMemberEmailExist(inputEmail);
    	String result = "";
    	
    	if( idresult == 0 && emailresult == 0) {
    		result = "ok";
    	}
    	else if(idresult == 1 && emailresult == 0) {
    		result = "one_id";
    	}
    	else if(idresult == 0 && emailresult == 1) {
    		result = "one_email";
    	}
    	else {
    		result = "both";
    	}
    	return "{\"result\" : \"" + result+ " \" }";
    }    
 

    //아이디 찾기
    @GetMapping("/findId")
    public String findId() {
    	return "findId";
    }
    
    @PostMapping("/findId")
    @ResponseBody
    public String findId(@RequestParam String memType, @RequestParam String memEmail, Model model) {
        String foundId = service.findId(memType, memEmail); // memType 추가
        model.addAttribute("foundId", foundId);
            
        return foundId;
    }

    // 비밀번호 찾기
    @GetMapping("/findPw")
    public String findPw() {
        return "findPw";
    }
    
    @PostMapping("/foundPw_Email")
    @ResponseBody
    public String foundPw_Email(@RequestParam String memType, @RequestParam String memEmail, Model model) {
        String foundPw_Email = service.findPwByEmail(memType, memEmail); // 이메일로 찾기 로직 호출
        model.addAttribute("foundPw_Email", foundPw_Email);
        return foundPw_Email;
    }

    @PostMapping("/foundPw_Id")
    @ResponseBody
    public String foundPw_Id(@RequestParam String memType, @RequestParam String memId, Model model) {
        String foundPw_Id = service.findPwById(memType, memId); // 아이디로 찾기 로직 호출
        model.addAttribute("foundPw_Id", foundPw_Id);
        return foundPw_Id;
    }
}