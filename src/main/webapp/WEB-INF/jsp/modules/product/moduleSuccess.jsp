<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false" %>
<div class="table-responsive">

    <c:choose>


        <c:when test="${empty percentage}">

            <big><fmt:message key="NO_RESULT" /></big>


        </c:when>

        <c:otherwise>




            <table class="table table-hover">

                <thead>
                <tr class = "info">

                    <th><fmt:message key="COMPOUND_INGRIDIENT" /></th>
                    <th><fmt:message key="ORDER" /></th>
                    <th><fmt:message key="INGRIDIENT" /></th>
                    <th><fmt:message key="%_IN_PRODUCT_MIXING_BOWL" /></th>
                    <th><fmt:message key="%_IN_FINISHED_PRODUCT" /></th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${percentage}" var="percentage">
                    <tr>

                        <td><c:out value="${percentage.parent}" /></td>
                        <td><c:out value="${percentage.position}" /></td>
                        <td><c:out value="${percentage.name}" escapeXml="false" /></td>
                        <td><c:out value="${percentage.percentage}" escapeXml="false"/></td>
                        <td><c:out value="${percentage.mixBowlPercentage}" escapeXml="false" /></td>

                    </tr>
                </c:forEach>


                </tbody>

            </table>

        </c:otherwise>

    </c:choose>

</div>

