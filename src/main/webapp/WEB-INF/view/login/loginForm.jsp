<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>login</title>
</head>
<body>
	<form id="loginForm" action="<c:url value='/login/processLogin.do'/>"
		name="loginForm" method="post">
		<p>
			아이디 : <input type="text" id="userId" name="userId"">
		</p>
		<p>
			비밀번호 : <input type="password" id="userPw" name="userPw">
		</p>
		<input type="submit" value="로그인">
	</form>
</body>
</html>

<script type="text/javascript">
	
</script>