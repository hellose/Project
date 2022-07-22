package project.user.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//사용자 관리 service
@Service
public class LoginService {

	@Autowired
	private LoginDAO loginDAO;

	public LoginVO processLogin(LoginVO loginVO) {
		return (LoginVO) loginDAO.processLogin(loginVO);
	}

	public int signUpUser(LoginVO loginVO) {
		return loginDAO.signUpUser(loginVO);
	}

}
