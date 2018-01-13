<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false" %>
<div class="table-responsive">

    <c:choose>


        <c:when test="${empty compounds}">

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
                <c:forEach items="${compounds}" var="compound">
                    <tr>

                        <td><c:out value="${compound.compound.name}" /></td>
                        <td><c:out value="${compound.compound.code}" /></td>
                        <td><c:out value="${compound.compoundPercentage}" /></td>

                        <td><a class ="one" href="${CONTEXT}/updateProductCompound?id=${compound.id}" > <fmt:message key="UPDATE_COMPOUND" /> </a></td>

                        <td><a class ="one" href="${CONTEXT}/deleteProductCompound?id=${compound.id}" onclick="return deleteCompound()"> <fmt:message key="DELETE" /> </a></td>


                    </tr>
                </c:forEach>


                </tbody>

            </table>

        </c:otherwise>

    </c:choose>

</div>

