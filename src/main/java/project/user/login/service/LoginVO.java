package project.user.login.service;

import lombok.Data;

@Data
public class LoginVO {
	private String userId;
	private String userNm;
	private String userNick;
	private String userPw;
	private String userEmail;
}
