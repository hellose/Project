package project.common.session;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		System.out.print("HttpSessionListener 세션 생성: ");
		System.out.print(httpSessionEvent.getSession().getId() + "\n\n");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		System.out.print("HttpSessionListener 세션 삭제: ");
		System.out.print(httpSessionEvent.getSession().getId() + "\n\n");
	}
}
