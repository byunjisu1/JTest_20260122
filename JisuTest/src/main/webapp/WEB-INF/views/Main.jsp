<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String id = (String)session.getAttribute("loginId");
	Map<String, Object> profileDetail = (Map<String, Object>)request.getAttribute("profileDetail");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>메인화면</title>
	<link rel="stylesheet" href="resources/css/Main.css"/>
	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<script>
		$(function() {
			$("#logout").click(function() {
				location.href="Logout";
			});
			$("#main > div > img").click(function() {	/* 컨텐츠 구입 */
				let needPoint = $(this).next(".point").text().replace(/[^0-9]/g, "");
				let contentName = $(this).parent().attr("id");
				
				fetch('buy_content', {
				    method: 'POST',
				    headers: { 'Content-Type': 'application/json; charset=utf-8' },
				    body: JSON.stringify({ id: "<%=id%>", needPoint: needPoint })
				})
				.then(response => {
				    if (!response.ok) { throw new Error('네트워크 에러! 관리자에게 문의하세요.') }
				    return response.json();
				})
				.then(data => {
				    if(data.result) {
				    	alert("컨텐츠(" +  contentName + ")를 구입하였습니다.");
				    	$("#myPoint").text(data.minusPoint);
				    } else {
				    	alert("포인트가 부족합니다. 광고를 클릭하세요.");
				    }
				})
				.catch(error => {
				    alert("에러");
				});
			});
			$("#ad").click(function() {	/* 광고 클릭 (포인트 적립) */
				fetch('click_ad', {
				    method: 'POST',
				    headers: { 'Content-Type': 'application/json; charset=utf-8' },
				    body: JSON.stringify({ id: "<%=id%>" })
				})
				.then(response => {
				    if (!response.ok) { throw new Error('네트워크 에러! 관리자에게 문의하세요.') }
				    return response.json();
				})
				.then(data => {
				    alert(data.addPoint + "점이 적립되었습니다.");
				    $("#myPoint").text(data.plusPoint);
				    location.href="https://koreaisacademy.com";
				})
				.catch(error => {
				    alert("에러");
				});
			});
		});
	</script>
</head>
<body>
	<div id="header">
		<div class="fl">메인페이지</div>
		<div class="fr">
			<div>
				<span><%=profileDetail.get("NAME")%>(<%=id%>)님 안녕하세요.</span>
				<button id="logout">로그아웃</button>
			</div>
			<div>포인트 : <span id="myPoint"><%=profileDetail.get("POINT")%></span>점</div>
		</div>
		<div style="clear: both;"></div>
	</div>
	<div id="header2">구입할 컨텐츠를 선택하세요.</div>
	<div id="main">
		<div id="intro">
			<img src="resources/img/Intro_350_408.png"/>
			<div class="point">100,000포인트</div>
		</div>
		<div id="java">
			<img src="resources/img/Java_350_408.png"/>
			<div class="point">500,000포인트</div>
		</div>
		<div id="C++">
			<img src="resources/img/Cpp_350_408.png"/>
			<div class="point">300,000포인트</div>
		</div>
	</div>
	<div id="ad" class="fr">
		<div><광고></div>
		<img src="resources/img/korea_it.png"/>
	</div>
	<div style="clear: both"></div>
</body>
</html>