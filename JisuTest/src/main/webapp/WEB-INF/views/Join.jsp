<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<link rel="stylesheet" href="resources/css/Join.css"/>
	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<script>
		$(function() {
			$("button").click(function() {
				let id = $("input[name='id']").val();
				let pw = $("input[name='pw']").val();
				let name = $("input[name='name']").val();
				
				fetch('insert_member', {
				    method: 'POST',
				    headers: { 'Content-Type': 'application/json; charset=utf-8' },
				    body: JSON.stringify({ id: id, pw: pw, name: name })
				})
				.then(response => {
				    if (!response.ok) { throw new Error('네트워크 에러! 관리자에게 문의하세요.') }
				    return response.json();
				})
				.then(data => {
				    alert("가입되었습니다. 로그인 해주세요.");
				    location.href="Login";
				})
				.catch(error => {
				    alert("에러");
				});
			});
		});
	</script>
</head>
<body>
	<h1>회원가입</h1>
	ID : <input type="text" name="id"/> <br/>
	PW : <input type="password" name="pw"/> <br/>
	Name : <input type="text" name="name"/> <br/>
	<button>작성완료</button>
</body>
</html>