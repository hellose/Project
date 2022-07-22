package project.common.session;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import lombok.Getter;
import lombok.Setter;
import project.common.Constant;
import project.user.login.service.LoginVO;

@Getter
@Setter
public class ProjectSessionListener implements HttpSessionListener, HttpSessionAttributeListener {

	public static final ConcurrentHashMap<String, HttpSession> loginUserMap = new ConcurrentHashMap<String, HttpSession>();

	// DEBUG
	// HttpSessionListener 인터페이스 메서드 구현

	// HttpSession 객체(세션)가 생성될 때 호출되는 콜백 메서드
	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		System.out.print("HttpSessionListener 세션 생성: ");
		System.out.print(httpSessionEvent.getSession().getId() + "\n\n");
	}

	// DEBUG
	// HttpSession 객체(세션)가 삭제되기 전에 호출되는 콜백 메서드
	// (세션 삭제전 attribute 작업이 모두 진행되고 sessionDestroyed가 호출됨)
	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		System.out.print("HttpSessionListener 세션 삭제: ");
		System.out.print(httpSessionEvent.getSession().getId() + "\n\n");
	}

	// HttpSessionAttributeListener 인터페이스 메서드 구현

	// HttpSession에 setAttribute를 통해 속성이 추가될 때 호출되는 콜백 메서드
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();

		if (event.getName().equals(Constant.SESSION_KEY)) {
			LoginVO loginVO = (LoginVO) event.getValue();
			System.out.println("[Thread] " + printThread() + "], [Session] " + session.getId() + ", [AddAttribute] "
					+ event.getName() + "[User] " + loginVO.getUserId());

			if (loginUserMap.containsKey(loginVO.getUserId())) {
				// 사용자 로그인 리스트에서 먼저 로그인된 세션객체를 빼낸다.
				HttpSession originalSession = loginUserMap.remove(loginVO.getUserId());
				System.out.println("loginUserMap에서 [Session] " + originalSession.getId() + " remove");

				// 세션 만료 작업 없이 로그인 관련 속성만 제거해주는 작업만 해도 비로그인 처리는 되지만 이전에 사용했던 다른 세션 속성이 남아있는 문제
				// -> 세션을 만료시키거나 while을 통해 모든 세션 속성을 null로 제거해주는 작업 필요
				// originalSession.invalidate();

				// 기존 세션의 속성 제거 작업 전에 나중에 로그인한 클라이언트 세션을 확정적으로 loginUserMap에 추가시켜 문제의 소지를 없앤다(멀티
				// 스레드)
				loginUserMap.put(loginVO.getUserId(), session);

				// 기존 session의 attribute제거 작업을 진행한다.
				Enumeration<String> enumeration = originalSession.getAttributeNames();
				while (enumeration.hasMoreElements()) {
					// attributeRemoved가 호출된다.
					originalSession.setAttribute(enumeration.nextElement(), null);
				}
			} else {
				loginUserMap.put(loginVO.getUserId(), session);
			}

		} else {
			System.out.println("[Thread] " + printThread() + "], [Session] " + session.getId() + ", [AddAttribute] "
					+ event.getName());
		}

		System.out.println();
	}

	// HttpSessino에 setAttribute("속성",null)또는 removeAttribute("속성")으로 각 속성이 제거된 후
	// 호출된다.
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();

		if (event.getName().equals(Constant.SESSION_KEY)) {
			LoginVO loginVO = (LoginVO) event.getValue();
			System.out.println("[Thread] " + printThread() + "], [Session] " + session.getId() + ", [RemoveAttribute] "
					+ event.getName() + "[User] " + loginVO.getUserId());

			// 중복 로그인이 아닌 경우 loginUserMap에 자신의 세션이 들어있어 로그인 사용자 리스트에서 제거되고
			// 중복 로그인인 경우 이전에 attributeAdded에서 확정적으로 다른 세션을 저장해놨기 때문에

			// key에 해당하는 value 객체가 session객체와 동일한 경우에만 remove한다.(session2가 저장되어있다면 아무 작업 없음)
			loginUserMap.remove(loginVO.getUserId(), session);
		} else {
			System.out.println("[Thread] " + printThread() + "], [Session] " + session.getId() + ", [RemoveAttribute] "
					+ event.getName());
		}

		System.out.println();
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
	}

	// DEBUG
	private String printThread() {
		return Thread.currentThread().getName();
	}

	// DEBUG
	public static void printLoginUserMap() {
		ConcurrentHashMap<String, HttpSession> map = ProjectSessionListener.loginUserMap;

		if (map.isEmpty()) {
			System.out.println("loginUserMap is empty");
		} else {
			System.out.println("loginUserMap is not empty, size " + map.size());
			Enumeration<String> enumeration = map.keys();
			while (enumeration.hasMoreElements()) {
				String userId = enumeration.nextElement();
				System.out.println("[User] " + userId + ", [Session] " + map.get(userId).getId());
			}
		}
		System.out.println();
	}

}
