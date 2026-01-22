<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty msg}">
    <script>
        alert("${msg}");
    </script>
</c:if>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
	<link rel="stylesheet" href="resources/css/Login.css"/>
	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<script>
		$(function() {
			$("#join").click(function() {
				location.href="Join";
			});
		});
	</script>
</head>
<body>
	<h1>로그인</h1>
	<form action="LoginAction" method="post">
		ID : <input type="text" name="id"/> <br/>
		PW : <input type="password" name="pw"/> <br/>
		<button id="login">로그인</button> <br/>
	</form>
	<button id="join">회원가입</button>
</body>
</html>