<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<div class="table-responsive">

    <c:choose>


        <c:when test="${empty compounds}">

            <big><fmt:message key="NO_COMPOUND"/></big>


        </c:when>

        <c:otherwise>


            <table id="table" class="table table-bordered">

                <thead>
                <tr class="info">

                    <th onclick="sortTableCompoundsAndProducts(0)" style="cursor: pointer"><fmt:message key="COMPOUND_NAME"/><span style="float:right;"> &uarr;&darr;</span></th>
                    <th><fmt:message key="COMPOUND_CODE"/></th>

                    <th colspan=2><fmt:message key="ACTION"/></th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${compounds}" var="compound">
                    <tr>

                        <td><a class="one" href="${CONTEXT}/compoundComposition?id=${compound.id}"><u><c:out
                                value="${compound.name}"/></u></a>
                        </td>
                        <td><c:out value="${compound.code}"/></td>

                        <td><a class="one" href="${CONTEXT}/updateCompound?id=${compound.id}"> <fmt:message
                                key="UPDATE_COMPOUND"/> </a></td>

                        <td><a class="one" href="${CONTEXT}/deleteCompound?id=${compound.id}"
                               onclick="return deleteCompound()">
                            <fmt:message key="DELETE"/> </a></td>


                    </tr>
                </c:forEach>


                </tbody>

            </table>

        </c:otherwise>

    </c:choose>

</div>

