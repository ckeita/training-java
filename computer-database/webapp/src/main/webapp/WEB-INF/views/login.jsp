<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapMinCss"/>
    <spring:url value="/resources/css/main.css" var="mainCss"/>
    <spring:url value="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/2.8.0/css/flag-icon.min.css" var="flagIconMinCss"/>
    <spring:url value="/resources/css/font-awesome.css" var="fontAwesomeCss"/>
    <title>Computer Database</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
            <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard"> Application - Computer Database </a>
        </div>
        <div id="lang">
            <a href="?lang=en">
                <span class="flag-icon flag-icon-gr"></span>
            </a>
            <a href="?lang=fr">
                <span class="flag-icon flag-icon-fr"></span>
            </a>
        </div>
    </header>
    <section id="main">
                <div class="container">
                    <div class="row">
                        <div class="col-md-offset-5 col-md-3">
                            <div class="form-login">
                                <h4><spring:message code="loginPage"/></h4>
                                <form action="login" method="POST">
                                    <input type="text" id="userName" name="username" class="form-control input-sm chat-input" placeholder="username" />
                                    </br>
                                    <input type="password" id="userPassword" name="password" class="form-control input-sm chat-input" placeholder="password" />
                                    </br>
                                    <input type="submit" class="btn btn-primary btn-md" value="<spring:message code="login"/>"/>
                                    <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
                                </form>
                            </div>
                        </div>
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
