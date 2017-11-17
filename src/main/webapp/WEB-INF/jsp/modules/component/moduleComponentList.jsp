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




            <table class="table table-bordered">

                <thead>
                <tr class = "info">

                    <th><fmt:message key="COMPONENT_NAME" /></th>

                    <th colspan=3 ><fmt:message key="ACTION"/></th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${components}" var="component">
                    <tr>

                        <td><c:out value="${component.name}" /></td>


                        <td><a class ="one" href="${CONTEXT}/renameComponent?id=${component.id}" > <fmt:message key="RENAME_COMPONENT" /> </a></td>
                        <td><a class ="one" href="${CONTEXT}/deleteComponent?id=${component.id}" onclick="return deleteComponent()"> <fmt:message key="DELETE" /> </a></td>


                    </tr>
                </c:forEach>


                </tbody>

            </table>

        </c:otherwise>

    </c:choose>

</div>

