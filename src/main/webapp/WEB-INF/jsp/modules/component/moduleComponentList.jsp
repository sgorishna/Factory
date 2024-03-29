<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false" %>
<div class="table-responsive">

    <c:choose>


        <c:when test="${empty components}">

            <big><fmt:message key="NO_COMPONENT" /></big>


        </c:when>

        <c:otherwise>




            <table id="table" class="table table-bordered " >

                <thead>
                <tr class = "info" >

                    <th onclick="sortTableComponents(0)" style="cursor: pointer"><fmt:message key="COMPONENT_NAME" /><span style="float:right;"> &uarr;&darr;</span></th>

                    <th><fmt:message key="COMPONENT_CODE" /></th>

                    <th><fmt:message key="ALLERGEN" /></th>

                    <th colspan=2 ><fmt:message key="ACTION"/></th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${components}" var="component">
                    <tr>

                        <td><c:out value="${component.name}" /></td>
                        <td><c:out value="${component.code}" /></td>
                        <td><c:out value="${component.allergen}" /></td>


                        <td><a class ="one" href="${CONTEXT}/updateComponent?id=${component.id}" > <fmt:message key="UPDATE_COMPONENT" /> </a></td>
                        <td><a class ="one" href="${CONTEXT}/deleteComponent?id=${component.id}" onclick="return deleteComponent()"> <fmt:message key="DELETE" /> </a></td>


                    </tr>
                </c:forEach>


                </tbody>

            </table>

        </c:otherwise>

    </c:choose>


</div>

