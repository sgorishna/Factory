<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page isELIgnored="false" %>


<c:choose>
    <c:when test="${not empty errMsg}">
        <p>${errMsg}</p>
        <br/>
    </c:when>

</c:choose>

<form method="POST" action="${CONTEXT}/newProductCompound?id=${product.id}">
    <div class="form-group">

        <div class="form-group">

            <label path="name" class="control-label"><fmt:message key="COMPOUND_NAME"/></label>

            <input type="text" id="compoundName" class="form-control" name="name" placeholder="Input name"
                   required="required"/>

            <label class="control-label"><fmt:message key="PERCENTAGE"/></label>

            <input id="percentage" type="number" step="any" min="0" class="form-control" name="percentage"
                   placeholder="Input percentage" required="required"/>

        </div>
    </div>

    <button type="submit" class="btn  btn-primary" value="Submit">Submit</button>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
