package project.user.index.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/index")
public class IndexController {

	@GetMapping(value = "/mainPage.do")
	public String loginForm(HttpServletRequest request) throws Exception {
		return "/index/mainPage";
	}
}
