<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page isELIgnored="false" %>


<form action="${CONTEXT}/downloadPDF" method="post">
    <input type="submit" value="Save PDF"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>