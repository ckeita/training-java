<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapMinCss"/>
    <spring:url value="/resources/css/font-awesome.css" var="fontAwesomeCss"/>
    <spring:url value="/resources/css/main.css" var="mainCss"/>
    <spring:url value="/resources/css/bootstrap-datepicker3.min.css" var="bootstrapDatepicker3MinCss"/>
    <spring:url value="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" var="bootstrapValidatorMinCss"/>
    <title>Computer Database</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="${bootstrapMinCss}" rel="stylesheet" media="screen">
    <link href="${fontAwesomeCss}" rel="stylesheet" media="screen">
    <link href="${mainCss}" rel="stylesheet" media="screen">
    <!-- Bootstrap-datepicker -->
    <link href="${bootstrapDatepicker3MinCss}" rel="stylesheet" media="screen">
    <link href="${bootstrapValidatorMinCss}" rel="stylesheet" media="screen">

</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Add Computer</h1>
                    <form data-toggle="validator" role="form" action="addcomputer" id="form_id" name="computerToAdd" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="name">Computer name</label>
                                <input type="text" class="form-control" id="name" name="name" placeholder="Computer name" required>
                            </div>
                            <div class="form-group">
                                 <div class="input-append date" id="introduced_date">
                                 <label for="introduced">Introduced date</label>
                                 	<input type="date" class="form-control" id="introduced" name="introduced" placeholder="Introduced date">
                                 </div>
                            </div>
                            <div class="form-group">
                                <div class="input-append date" id="discontinued_date">
                                	<label for="discontinued_date">Discontinued date</label>
                                	<input type="date" class="form-control" id="discontinued" name="discontinued" placeholder="Discontinued date">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="companyId" >
                          			<option value="0">--</option>
                                	<c:forEach var="company" items="${companies}">
                                		<option value="${company.id}">${company.name}</option>	
                                	</c:forEach>
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Add" class="btn btn-primary">
                            or
                            <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <spring:url value="/resources/js/jquery-1.12.4.min.js" var="jqueryMinJs"/>
    <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapMinJs"/>
    <spring:url value="/resources/js/bootstrap-datepicker.min.js" var="bootstrapDatepickerMinJs"/>
    <spring:url value="/resources/js/bootstrap-datepicker.fr.min.js" var="bootstrapDatepickerFrMinJs"/>
    <spring:url value="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js" var="bootstrapValidatorMinJs"/>
    <spring:url value="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js" var="momentMinJs"/>
    <spring:url value="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment-with-locales.min.js" var="momentWithLocalesMinJs"/>
    <spring:url value="/resources/js/front-validation.js" var="frontValidationJs"/>

    <script type="text/javascript" src="${jqueryMinJs}"></script>
	<script type="text/javascript" src="${bootstrapMinJs}"></script>
    <script type="text/javascript" src="${bootstrapDatepickerMinJs}"></script>
    <script type="text/javascript" src="${bootstrapDatepickerFrMinJs}"></script>
    <script type="text/javascript" src="${bootstrapValidatorMinJs}"></script>
    <script src="${momentMinJs}"></script>
    <script src="${momentWithLocalesMinJs}"></script>
    <script type="text/javascript" src="${frontValidationJs}"></script>
</body>
</html>