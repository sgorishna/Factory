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
<form:form  method="POST" action="${CONTEXT}/newComponent" modelAttribute="component" >
<div class="form-group">

<div class="form-group">

 <form:label path = "code" class="control-label" ><fmt:message key="COMPONENT_CODE" /></form:label>
 &nbsp<span id="checkCode"> </span>
 <form:input path = "code" id ="code" type="text"  class="form-control"  placeholder="Input code" onkeyup="checkComponentCode()" />

 <form:label path = "name" class="control-label" ><fmt:message key="COMPONENT_NAME" /></form:label>
 &nbsp<span id="checkName"> </span>
 <form:input path = "name" id ="name" type="text" class="form-control"  placeholder="Input a name" onkeyup="checkComponentName()" required ="required" />

 <form:label path = "allergen" class="control-label" ><fmt:message key="ALLERGEN" /></form:label>

 <form:select path="allergen" class="form-control">
  <c:forEach var="allergen" items="${allergens}">

   <option value="${allergen}" ${ allergen == component.allergen ? 'selected' : ''}> ${allergen}</option>

  </c:forEach>
 </form:select>

</div>



<button type="submit" class="btn  btn-primary" value="Submit">Submit</button>

 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form:form>