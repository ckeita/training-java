<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Computer Database</title>
	<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapMinCss"/>
	<spring:url value="/resources/css/font-awesome.css" var="fontAwesomeCss"/>
	<spring:url value="/resources/css/main.css" var="mainCss"/>
	<spring:url value="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/2.8.0/css/flag-icon.min.css" var="flagIconMinCss"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="utf-8">
	<!-- Bootstrap -->
	<link href="${bootstrapMinCss}" rel="stylesheet" media="screen">
	<link href="${fontAwesomeCss}" rel="stylesheet" media="screen">
	<link href="${mainCss}" rel="stylesheet" media="screen">
	<!-- flag-icon -->
	<link href="${flagIconMinCss}" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard"> Application -
			Computer Database </a>
	</div>
	<div id="lang">
		<a href="?lang=en">
			<span class="flag-icon flag-icon-gb"></span>
		</a>
		<a href="?lang=fr">
			<span class="flag-icon flag-icon-fr"></span>
		</a>
		<form action="logout" method="post" class="pull-right">
			<input type="submit" class="btn btn-info btn-sm" value="<spring:message code="logout"/>">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
	</div>

	</header>

	<section id="main">
	<div class="container">
		<h1 id="homeTitle">${page.nbComputers} <spring:message code="found"/> </h1>
		<div id="actions" class="form-horizontal">
			<div class="pull-left">
				<form id="searchForm" action="dashboard" method="GET" class="form-inline">
					<input type="search" id="searchbox" name="search"
						class="form-control" placeholder="Search name" /> <input
						type="submit" id="searchsubmit" value="Filter by name"
						class="btn btn-primary" />
				</form>
			</div>
			<div class="pull-right">
				<a class="btn btn-success" id="addComputer" href="${pageContext.request.contextPath}/addcomputer"><spring:message code="addTitle"/></a> <a class="btn btn-default" id="editComputer" href="#"
					onclick="$.fn.toggleEditMode();">Edit</a>
			</div>
		</div>
	</div>

	<form id="deleteForm" action="dashboard" method="POST">
		<input type="hidden" name="selection" value="">
	</form>

	<div class="container" style="margin-top: 10px;">
		<table class="table table-striped table-bordered" >
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->

					<th class="editMode" style="width: 60px; height: 22px; display: none;" ><input
						type="checkbox" id="selectall" /> <span
						style="vertical-align: top;"> - <a href="#"
							id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
								class="fa fa-trash-o fa-lg"></i>
						</a>
					</span></th>
					<!-- Table header for Computer Name -->
					<spring:message code="name" var="name"/>
					<mytags:orderby target="dashboard?column=name&order=" column="${name}"/>
					<!-- Table header for Introduced Date -->
					<spring:message code="introduced" var="introduced"/>
					<mytags:orderby target="dashboard?column=introduced&order=" column="${introduced}" />
					<!-- Table header for Discontinued Date -->
					<spring:message code="discontinued" var="discontinued"/>
					<mytags:orderby target="dashboard?column=discontinued&order=" column="${discontinued}" />
					<!-- Table header for Company -->
					<spring:message code="company" var="company"/>
					<mytags:orderby target="dashboard?column=company&order=" column="${company}" />
				</tr>
			</thead>
			<!-- Browse attribute computers -->
			<tbody id="results">
				<c:forEach var="computer" items="${page.computers}">
					<tr>
						<td style="display: none;" class="editMode"><input type="checkbox" name="cb"
						class="cb" value="${computer.id}"></td>
						<td><a href="editcomputer?id=${computer.id}" onclick="">${computer.name}</a></td>
						<td>
							<c:if test="${not empty computer.introduced}">
								${computer.introduced}
							</c:if>
						</td>
						<td>
							<c:if test="${not empty computer.discontinued}">
	    						${computer.discontinued}
							</c:if>
						</td>
						<td>
							<c:if test="${not empty computer.companyDTO.name}">
	    						${computer.companyDTO.name}
							</c:if>
						</td>
					</tr>
					<input type="hidden" name="id" value="${computer.id}" />
				</c:forEach>
			</tbody>
		</table>
	</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<c:set var="start" value="${page.curPage}" scope="page"/>
		<c:set var="end" value="${page.nbPages}" scope="page"/>
		<div class="container text-center">
			<ul class="pagination">
				<c:if test="${page.curPage-1 > 0}">
					<li>
						<a href="dashboard?current=${page.curPage-1}" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a>
					</li>
				</c:if>
				<c:if test="${start+4 <= page.nbPages}">
					<c:set var="end" value="${start+4}" scope="page"/>
				</c:if>
				<c:forEach var="i" begin="${start}" end="${end}">
					<mytags:link target="dashboard?current=${i}" current="${current+i}"/>
				</c:forEach>
				<c:if test="${page.curPage+1 < page.nbPages-2}">
					<li>
						<a href="dashboard?current=${page.curPage+1}" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
					</li>
				</c:if>
			</ul>
			<div class="btn-group btn-group-sm pull-right" role="group">
				<mytags:pagination  target="dashboard?limit=${10}" limit="${10}"/>
				<mytags:pagination  target="dashboard?limit=${50}" limit="${50}"/>
				<mytags:pagination  target="dashboard?limit=${100}" limit="${100}"/>
			</div>
		</div>
	</footer>
	<spring:url value="/resources/js/jquery-1.12.4.min.js" var="jqueryMinJs"/>
	<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapMinJs"/>
	<spring:url value="/resources/js/dashboard.js" var="dashboardJs"/>

	<script src="${jqueryMinJs}"></script>
	<script src="${bootstrapMinJs}"></script>
	<script src="${dashboardJs}"></script>
</body>
</html>