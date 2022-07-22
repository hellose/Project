package project.common.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

	public static void requestInfo(HttpServletRequest request) {
		System.out.println(request.getRequestURI());
	}
}
