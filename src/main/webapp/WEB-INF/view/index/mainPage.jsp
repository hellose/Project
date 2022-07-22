<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Main Page</title>
</head>
<body>
	메인 페이지
	<br>
	<form id="logoutForm" name="logoutForm" method="get"
		action="<c:url value='/logout/processLogout.do'/>">
		<input type="submit" value="로그아웃" />
	</form>
</body>
</html>

<script type="text/javascript">
	
</script>