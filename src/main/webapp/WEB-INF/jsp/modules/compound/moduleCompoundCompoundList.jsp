<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false" %>
<div class="table-responsive">

    <c:choose>


        <c:when test="${empty child}">

            <big><fmt:message key="NO_COMPOUND" /></big>


        </c:when>

        <c:otherwise>




            <table class="table table-bordered">

                <thead>
                <tr class = "info">

                    <th><fmt:message key="COMPOUND_NAME" /></th>
                    <th><fmt:message key="COMPOUND_CODE" /></th>
                    <th><fmt:message key="PERCENTAGE" /></th>

                    <th colspan=2 ><fmt:message key="ACTION"/></th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${child}" var="child">
                    <tr>

                        <td><c:out value="${child.child.name}" /></td>
                        <td><c:out value="${child.child.code}" /></td>
                        <td><c:out value="${child.childPercentage}" /></td>

                        <td><a class ="one" href="${CONTEXT}/updateCompoundCompound?id=${child.id}" > <fmt:message key="UPDATE_COMPOUND" /> </a></td>

                        <td><a class ="one" href="${CONTEXT}/deleteCompoundCompound?id=${child.id}" onclick="return deleteCompound()"> <fmt:message key="DELETE" /> </a></td>


                    </tr>
                </c:forEach>


                </tbody>

            </table>

        </c:otherwise>

    </c:choose>

</div>

