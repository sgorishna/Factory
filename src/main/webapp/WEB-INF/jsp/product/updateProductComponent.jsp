<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Register new customer</title>
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
                    <a href="${CONTEXT}/home"><fmt:message key="HOME"/> <span class="sr-only">(current)</span></a>
                </li>
                <li>

                    <a href="${CONTEXT}/products"><fmt:message key="PRODUCTS"/> <span class="sr-only">(current)</span></a>
                </li>

                <li>
                    <a href="${CONTEXT}/compounds"><fmt:message key="COMPOUNDS"/></a>
                </li>

                <li>
                    <a href="${CONTEXT}/components"><fmt:message key="COMPONENTS"/></a>
                </li>

            </ul>

            <ul class="nav nav-sidebar">


                <li>
                    <a href="${CONTEXT}/exceptions" class = "styleone"><fmt:message key="EXCEPTIONS" /> <span class="sr-only">(current)</span></a>
                </li>
            </ul>


        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header"> ${component.product.name} <fmt:message key="COMPONENTS"/></h2>

            <ol class="breadcrumb">

                <li><a href="${CONTEXT}/newProductComponent?id=${component.product.id}"><fmt:message key="ADD_NEW_COMPONENT"/></a></li>
                <li><a href="${CONTEXT}/productComponentList?id=${component.product.id}"><fmt:message key="SHOW_COMPONENTS"/></a></li>

                </li>


            </ol>

            <jsp:include page="../modules/product/moduleUpdateProductComponent.jsp"></jsp:include>
        </div>


    </div>
</div>

</body>
</html>
