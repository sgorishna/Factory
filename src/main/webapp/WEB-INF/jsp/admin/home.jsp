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

    <script type="text/javascript">
        <%@include file="/resources/js/actions.js" %>
    </script>

</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">

            <img src="../resources/images/main_132x39_grey.png"
            >
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">

                <li>
                    <jsp:include page="../modules/moduleLogout.jsp"></jsp:include>
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
                    <a href="${CONTEXT}/home" class = "styleone">Home <span class="sr-only">(current)</span></a>
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
                    <a href="${CONTEXT}/exceptions" class = "styleone">Exceptions <span class="sr-only">(current)</span></a>
                </li>
            </ul>


        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header" ><fmt:message key="USERS" /></h2>

            <ol class="breadcrumb">

                <li><a href="${CONTEXT}/newUser"><fmt:message key="ADD_NEW_USER" /></a>
                </li>


                </li>



            </ol>

            <jsp:include page="../modules/admin/moduleUserList.jsp"></jsp:include>
        </div>
    </div>
</div>

</body>
</html>
