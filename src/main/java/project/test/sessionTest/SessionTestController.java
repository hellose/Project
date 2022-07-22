package project.test.sessionTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.common.session.ProjectSessionListener;

@Controller
@RequestMapping(value = "/test/session")
public class SessionTestController {

	@GetMapping(value = "/invalidate.do")
	public String invalidateSession(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		System.out.println(session.getId() + " invalidate()");
		session.invalidate();
		return "/test";
	}

	@GetMapping(value = "/setAttribute.do")
	public String addSessionAttribute(HttpServletRequest request, @RequestParam String key, @RequestParam String value)
			throws Exception {

		if (key != null) {
			if ("null".equalsIgnoreCase(value)) {
				request.getSession().setAttribute(key, null);
			} else {
				request.getSession().setAttribute(key, value);
			}
		} else {
			System.out.println("key is null -> setAttribute 작업 없음");
		}
		return "/test";
	}

	@GetMapping(value = "/printLoginUserMap.do")
	public String printLoginUserMap(HttpServletRequest request) throws Exception {

		ProjectSessionListener.printLoginUserMap();
		return "/test";
	}

}