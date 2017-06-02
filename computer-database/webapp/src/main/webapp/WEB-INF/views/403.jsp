<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>Computer Database</title>
	<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapMinCss"/>
	<spring:url value="/resources/css/font-awesome.css" var="fontAwesomeCss"/>
	<spring:url value="/resources/css/main.css" var="mainCss"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="utf-8">
	<!-- Bootstrap -->
	<link href="${bootstrapMinCss}" rel="stylesheet" media="screen">
	<link href="${fontAwesomeCss}" rel="stylesheet" media="screen">
	<link href="${mainCss}" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard"> Application - Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="alert alert-danger">
				Error 403: Access denied!
				<br/>
				<!-- stacktrace -->
			</div>
		</div>
	</section>

	<spring:url value="/resources/js/jquery-1.12.4.min.js" var="jqueryMinJs"/>
	<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapMinJs"/>
	<spring:url value="/resources/js/dashboard.js" var="dashboardJs"/>

	<script src="${jqueryMinJs}"></script>
	<script src="${bootstrapMinJs}"></script>
	<script src="${dashboardJs}"></script>
</body>
</html>