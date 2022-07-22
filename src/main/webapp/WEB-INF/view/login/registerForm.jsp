<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>signUp</title>
</head>
<body>
	<form id="registerForm"
		action="<c:url value='/login/registerUser.do'/>" name="registerForm"
		method="post">
		<p>
			아이디 : <input type="text" id="userId" name="userId"">
		</p>
		<p>
			비밀번호 : <input type="password" id="userPassword" name="userPassword">
		</p>
		<p>
			비밀번호 확인 : <input type="password" id="userPasswordCheck"
				name="userPasswordCheck">
		</p>
		<input type="submit" value="회원가입">
	</form>
</body>
</html>

<script type="text/javascript">
	
</script>