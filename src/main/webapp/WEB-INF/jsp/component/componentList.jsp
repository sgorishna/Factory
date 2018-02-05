<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Register new customer</title>


    <style>

        <%@include file="/resources/css/bootstrap.css" %>

        <%@include file="/resources/css/dashboard.css" %>



    </style>

    <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">--%>
    <script type="text/javascript">

        <%@include file="/resources/js/jquery-1.10.2.js" %>
        <%@include file="/resources/js/jquery-ui.js" %>
        <%@include file="/resources/js/actions.js" %>
        <%@include file="/resources/js/tableSearch.js" %>

        <%@include file="/resources/js/tableSort.js" %>


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

                    <a href="${CONTEXT}/products"><fmt:message key="PRODUCTS"/> <span
                            class="sr-only">(current)</span></a>
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
                    <a href="${CONTEXT}/exceptions" class="styleone"><fmt:message key="EXCEPTIONS"/> <span
                            class="sr-only">(current)</span></a>
                </li>
            </ul>


        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header"><fmt:message key="COMPONENTS"/></h2>

            <div>

                <div class="input-group" style="float:right; margin-bottom:20px">


                    <input type="text" id="search" class="form-control" placeholder="Search for..."
                           aria-label="Left Align">
                </div>

            </div>

            <ol class="breadcrumb">

                <li><a href="${CONTEXT}/newComponent"><fmt:message key="ADD_NEW_COMPONENT"/></a>
                </li>


                </li>


            </ol>

            <jsp:include page="../modules/component/moduleComponentList.jsp"></jsp:include>
        </div>


    </div>
</div>

</body>
</html>
