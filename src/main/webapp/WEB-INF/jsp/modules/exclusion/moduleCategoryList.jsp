<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<div class="table-responsive">

    <c:choose>


        <c:when test="${empty category}">

            <big><fmt:message key="NO_CATEGORIES"/></big>


        </c:when>

        <c:otherwise>


            <table class="table table-bordered">

                <thead>
                <tr class="info">

                    <th><fmt:message key="CATEGORY_NAME"/></th>

                    <th colspan=2><fmt:message key="ACTION"/></th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${category}" var="category">
                    <tr>

                        <td><a class ="one" href="${CONTEXT}/categoryException?id=${category.id}"><u><c:out value="${category.name}"/></u></a></td>



                        <td><a class ="one" href="${CONTEXT}/renameCategory?id=${category.id}" > <fmt:message key="UPDATE"/> </a>
                        </td>

                        <td><a class ="one" href="${CONTEXT}/deleteCategory?id=${category.id}" onclick="return deleteCategory()" > <fmt:message key="DELETE"/> </a>
                        </td>



                    </tr>
                </c:forEach>


                </tbody>

            </table>

        </c:otherwise>

    </c:choose>

</div>

