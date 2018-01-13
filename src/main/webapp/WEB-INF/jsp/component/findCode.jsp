<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page isELIgnored="false" %>
<c:choose>
    <c:when test="${code != null}">
        ${code}
    </c:when>

    <c:when test="${no_code != null}">
        ${no_code}
    </c:when>

</c:choose>
