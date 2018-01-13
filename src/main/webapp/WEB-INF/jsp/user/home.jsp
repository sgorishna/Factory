<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

    <title>Home</title>
    <style>
        <!--
        Bootstrap core CSS

        -->

        <%@include file="/resources/css/bootstrap.css" %>

        <!--
        Custom styles for this template

        -->
        <%@include file="/resources/css/dashboard.css" %>

    </style>

</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">

            <%--<img class="profile-img" src="../image"--%>
            <%-->--%>
            <%----%>
            <%--<a class = "navbar-brand"> MyBank</a> --%>


        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">

                <li>
                    <c:url var="logoutUrl" value="/logout"/>
                    <form action="${logoutUrl}" method="post">
                        <input type="submit" value="Logout"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </li>
            </ul>
            <form class="navbar-form navbar-right">
            </form>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">


                <li>
                    <a href="${CONTEXT}/home">Home <span class="sr-only">(current)</span></a>
                </li>
            </ul>

            <ul class="nav nav-sidebar">
                <li>
                    <a href="${CONTEXT}/products">Products <span class="sr-only">(current)</span></a>
                </li>

                <li>
                    <a href="${CONTEXT}/compounds">Compounds</a>
                </li>

                <li>
                    <a href="${CONTEXT}/components">Components</a>
                </li>
            </ul>

            <ul class="nav nav-sidebar">


                <li>
                    <a href="${CONTEXT}/exceptions" class = "styleone"><fmt:message key="EXCEPTIONS" /> <span class="sr-only">(current)</span></a>
                </li>
            </ul>


        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header">Home page</h2>
            <p>User page</p>
        </div>
    </div>
</div>

</body>
</html>
