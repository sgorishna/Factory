<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false" %>
<div class="table-responsive">

    <c:choose>


        <c:when test="${empty products}">

            <big><fmt:message key="NO_PRODUCT" /></big>


        </c:when>

        <c:otherwise>




            <table class="table table-bordered">

                <thead>
                <tr class = "info">

                    <th><fmt:message key="PRODUCT_NAME" /></th>

                    <th colspan=3 ><fmt:message key="ACTION"/></th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${products}" var="product">
                    <tr>

                        <td><a class ="one" href="${CONTEXT}/productComposition?id=${product.id}" ><u><c:out value="${product.name}" /></u></a></td>


                        <td><a class ="one" href="${CONTEXT}/renameProduct?id=${product.id}" > <fmt:message key="RENAME_PRODUCT" /> </a></td>

                        <td><a class ="one" href="${CONTEXT}/deleteProduct?id=${product.id}" onclick="return deleteProduct()"> <fmt:message key="DELETE" /> </a></td>
                        <td><a class ="one" href="${CONTEXT}/calculate?id=${product.id}"> <fmt:message key="CALCULATE_PERCENTAGE" /> </a></td>

                    </tr>
                </c:forEach>


                </tbody>

            </table>

        </c:otherwise>

    </c:choose>

</div>

