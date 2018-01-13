<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false" %>
<div class="table-responsive">

    <c:choose>


        <c:when test="${empty composition}">

            <big><fmt:message key="NO_COMPOSITION" /></big>


        </c:when>

        <c:otherwise>




            <table class="table table-hover">

                <thead>
                <tr class = "info">

                    <th><fmt:message key="COMPOUND_INGRIDIENT" /></th>
                    <th><fmt:message key="ORDER" /></th>
                    <th><fmt:message key="INGRIDIENT" /></th>
                    <th><fmt:message key="%_IN_PRODUCT_MIXING_BOWL" /></th>


                </tr>
                </thead>
                <tbody>
                <c:forEach items="${composition}" var="composition">
                    <tr>

                        <td><c:out value="${composition.parent}" /></td>
                        <td><c:out value="${composition.position}" /></td>
                        <td><c:out value="${composition.name}" /></td>
                        <td><c:out value="${composition.percentage}" /></td>


                    </tr>
                </c:forEach>


                </tbody>

            </table>

        </c:otherwise>

    </c:choose>

</div>

