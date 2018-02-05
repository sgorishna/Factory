<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <style>
        <%@include file="/resources/css/style.css" %>
        <%@include file="/resources/css/bootstrap.min.css" %>
    </style>

    <title> Login</title>
</head>
<body style="background-color: #2e6da4">

<c:choose>
    <c:when test="${not empty error}">
        <p>${error}</p>
        <br/>
    </c:when>

</c:choose>
<c:choose>
    <c:when test="${not empty msg}">
        <p>${msg}</p>
        <br/>
    </c:when>

</c:choose>


<div class="container" style="background-color: #2e6da4" align="center">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">

            <div class="account-wall">
                <img src="resources/images/main.png"
                >
                <form class="form-signin" action="j_spring_security_check" method="post">
                    <div class="form-group">
                        <input type='text' name="j_username" class="form-control" placeholder="Login"
                               required="required"
                               autofocus="true"/>
                    </div>
                    <div class="form-group">
                        <input type='text' name="j_password" class="form-control" placeholder="Password"
                               required="required"/>
                    </div>

                    <button class="btn btn-lg btn-primary btn-block" type="submit" value="Submit">
                        Sign in
                    </button>

                    <label class="checkbox pull-left">
                        <input type="checkbox" value="remember-me">
                        Remember me
                    </label>

                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>


                </form>
            </div>

        </div>
    </div>

</div>


</body>
</html>