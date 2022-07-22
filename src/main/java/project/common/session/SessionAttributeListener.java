package project.common.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class SessionAttributeListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("----------HttpSessionBindingEvent attributeAdded----------");
		HttpSession session = event.getSession();
		System.out.println("session id: " + session.getId());
		System.out.println("added attribute: " + event.getName());
		System.out.println("object: " + event.getValue());
		System.out.println();
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		System.out.println("----------HttpSessionBindingEvent attributeRemoved----------");
		HttpSession session = event.getSession();
		System.out.println("session id: " + session.getId());
		System.out.println("removed attribute: " + event.getName());
		System.out.println("object: " + event.getValue());
		System.out.println();
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		System.out.println("----------HttpSessionBindingEvent attributeReplaced----------");
		HttpSession session = event.getSession();
		System.out.println("session id: " + session.getId());
		System.out.println("replaced attribute: " + event.getName());
		System.out.println("object: " + event.getValue());
		System.out.println();
	}

}
