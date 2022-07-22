package project.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import project.common.Constant;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean isAuthenticated = isAuthenticated();

		// 디버깅
		if (isAuthenticated) {
			System.out.println("AuthenticationInterceptor: logined");
		} else {
			System.out.println("AuthenticationInterceptor: not logined");
		}
		if (!isAuthenticated) {
			ModelAndView modelAndView = new ModelAndView("redirect:/login/loginForm.do");
			throw new ModelAndViewDefiningException(modelAndView);
		}

		return true;
	}

	public static Object getAuthenticatedUser() {

		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		Object result = requestAttributes.getAttribute(Constant.SESSION_KEY, RequestAttributes.SCOPE_SESSION);
		if ((requestAttributes == null) || (result == null)) {
			return null;
		} else {
			return result;
		}

	}

	public static Boolean isAuthenticated() {

		if (RequestContextHolder.getRequestAttributes() == null) {
			return false;
		} else {
			if (RequestContextHolder.getRequestAttributes().getAttribute(Constant.SESSION_KEY,
					RequestAttributes.SCOPE_SESSION) == null) {
				return false;
			} else {
				return true;
			}
		}

	}

}
