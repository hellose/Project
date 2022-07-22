package project.attachFile.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/attachFile")
public class AttachFileController {

	@RequestMapping(value = "/upload.do")
	public String processUploadFile(HttpServletRequest request) throws Exception {
		return "/login/loginForm";
	}


}
