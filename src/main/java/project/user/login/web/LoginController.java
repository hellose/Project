package project.user.login.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.common.Constant;
import project.user.login.service.LoginService;
import project.user.login.service.LoginVO;

//로그인, 로그아웃 처리 컨트롤러
@Controller
@RequestMapping
public class LoginController {

	@Autowired
	private LoginService loginService;

	@GetMapping(value = "/login/loginForm.do")
	public String loginForm(HttpServletRequest request) throws Exception {
		return "/login/loginForm";
	}

	@GetMapping(value = "/login/registerForm.do")
	public String registerForm(HttpServletRequest request) throws Exception {
		return "/login/registerForm";
	}

	@PostMapping(value = "/login/processLogin.do")
	public String processLogin(HttpServletRequest request, @ModelAttribute LoginVO signVO) throws Exception {
		HttpSession session = request.getSession();
		LoginVO resultVO = loginService.processLogin(signVO);
		if (resultVO == null) {
			return "redirect:/login/loginForm.do";
		} else {
			session.setAttribute(Constant.SESSION_KEY, resultVO);
			return "redirect:/index/mainPage.do";
		}
	}

	@GetMapping(value = "/logout/processLogout.do")
	public String processLogout(HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		Object loginAttribute = session.getAttribute(Constant.SESSION_KEY);
		if ((loginAttribute == null) || !(loginAttribute instanceof LoginVO)) {
			return "redirect:/login/loginForm.do";
		} else {
			session.removeAttribute(Constant.SESSION_KEY);
			return "redirect:/login/loginForm.do";
		}
	}

}
