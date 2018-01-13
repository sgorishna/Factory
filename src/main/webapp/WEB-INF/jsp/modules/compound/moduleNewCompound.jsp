<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false" %>


<c:choose>
 <c:when test="${not empty errMsg}">
  <p>${errMsg}</p>
  <br />
 </c:when>

</c:choose>
<form:form  method="POST" action="${CONTEXT}/newCompound" modelAttribute="compound" >
<div class="form-group">

<div class="form-group">

 <form:label path = "code" class="control-label" ><fmt:message key="COMPOUND_CODE" /></form:label>
 &nbsp<span id="checkCode"> </span>
 <form:input path = "code" id ="code" type="number" step="any" min="0" max="99999999" class="form-control"  placeholder="Input code" onkeyup="checkCompoundCode()" />

 <form:label path = "name" class="control-label" ><fmt:message key="COMPOUND_NAME" /></form:label>
 &nbsp<span id="checkName"> </span>
 <form:input path = "name" id ="name" type="text" class="form-control"  placeholder="Input a name" onkeyup="checkCompoundName()" required ="required" />

</div>



<button type="submit" class="btn  btn-primary" value="Submit">Submit</button>

 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form:form>