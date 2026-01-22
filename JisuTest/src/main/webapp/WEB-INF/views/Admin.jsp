<%@page import="com.js.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty msg}">
	<script>
	    alert("${msg}");
	</script>
</c:if>
<%

	List<MemberDto> listMember = (List<MemberDto>)request.getAttribute("listMember");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자 페이지</title>
	<link rel="stylesheet" href="resources/css/Admin.css"/>
	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<script>
		$(function() {
			$("#login").click(function() {
				location.href="Login";
			});
			$(".btn_modify").click(function() {
				let id = $(this).closest("tr").attr("data-id");
				location.href="MemberModify?id=" + id;
			});
			$(".btn_delete").click(function() {
				let deleteId = $(this).closest("tr").attr("data-id");
				
				fetch('member_delete', {
				    method: 'POST',
				    headers: { 'Content-Type': 'application/json; charset=utf-8' },
				    body: JSON.stringify({ id: deleteId })
				})
				.then(response => {
				    if (!response.ok) { throw new Error('네트워크 에러! 관리자에게 문의하세요.') }
				    return response.json();
				})
				.then(data => {
				    alert("삭제되었습니다.");
				    location.reload();
				})
				.catch(error => {
				    alert("에러");
				});
			});
		});
		function btnStart() {
			location.href="Start";
		}
		function btnEnd() {
			location.href="End";
		}
	</script>
</head>
<body>
	<div id="header">
		<button id="login" class="fr">로그인</button>
		<div class="fl">회원관리</div>
		<div style="clear: both;"></div>
	</div>
	<div id="member_list">
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>PW</th>
					<th>Name</th>
					<th>Point</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<% for(MemberDto dto : listMember) { %>
					<tr data-id="<%=dto.getId()%>">
						<td><%=dto.getId()%></td>
						<td><%=dto.getPw()%></td>
						<td><%=dto.getName()%></td>
						<td><%=dto.getPoint()%></td>
						<td><button class="btn_modify">수정</button></td>
						<td><button class="btn_delete">삭제</button></td>
					</tr>
				<% } %>
			</tbody>
		</table>
	</div>
	<div id="scheduler">
		<div>스케줄러관리</div>
		<button onclick="btnStart();">스케줄러(20초마다 포인트1 증가) 실행 시작</button>
		<button onclick="btnEnd();">스케줄러 실행 종료</button>
	</div>
</body>
</html>