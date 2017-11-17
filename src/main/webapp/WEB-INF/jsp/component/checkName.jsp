<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page isELIgnored="false" %>
<c:choose>
<c:when test="${taken != null}">
<font color=red> ${taken} </font>
</c:when>

<c:when test="${available != null}">
<font color=green> ${available} </font>
</c:when>

</c:choose>
