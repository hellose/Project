package project.user.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.common.sql.CommonDAO;

//사용자 관리 DAO
@Repository
public class LoginDAO {

	@Autowired
	private CommonDAO commonDAO;

	public LoginVO processLogin(LoginVO loginVO) {
		return commonDAO.selectOne("login.selectOneUser", loginVO);
	}

	public int signUpUser(LoginVO loginVO) {
		return commonDAO.insert("login.insertOneUser", loginVO);
	}

}
