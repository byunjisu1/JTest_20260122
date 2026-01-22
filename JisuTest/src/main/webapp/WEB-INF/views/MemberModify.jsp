<%@page import="com.js.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberDto memberDetail = (MemberDto)request.getAttribute("memberDetail");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원정보수정</title>
	<link rel="stylesheet" href="resources/css/MemberModify.css"/>
	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<script>
	
	</script>
</head>
<body>
	<div>회원관리-수정관리자</div>
	<form action="ModifyAction" method="post">
		ID <input type="text" name="id" value="<%=memberDetail.getId()%>" readonly/> <br/>
		PW <input type="text" name="pw" value="<%=memberDetail.getPw()%>"/> <br/>
		Name <input type="text" name="name" value="<%=memberDetail.getName()%>"/> <br/>
		point <input type="text" name="point" value="<%=memberDetail.getPoint()%>"/> <br/>
		<button>제출</button>
	</form>
	
	
</body>
</html>