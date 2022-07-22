package project.common.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class SessionBindingListener implements HttpSessionBindingListener {

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("----------HttpSessionBindingEvent valueBound----------");
		HttpSession session = event.getSession();
		System.out.println("session id: " + session.getId());
		System.out.println("bound attribute: " + event.getName());
		System.out.println("object: " + event.getValue());
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		System.out.println("----------HttpSessionBindingEvent valueUnbound----------");
		System.out.println("session id: " + session.getId());
		System.out.println("unbound attribute: " + event.getName());
		System.out.println("object: " + event.getValue());
	}

}
