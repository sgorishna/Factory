<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<div class="table-responsive">

    <c:choose>


        <c:when test="${empty users}">

            <big><fmt:message key="NO_USERS"/></big>


        </c:when>

        <c:otherwise>


            <table class="table table-bordered">

                <thead>
                <tr class="info">

                    <th><fmt:message key="LOGIN"/></th>
                    <th><fmt:message key="PASSWORD"/></th>
                    <th><fmt:message key="ACTIVE"/></th>

                    <th colspan=3><fmt:message key="ACTION"/></th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>

                        <td><c:out value="${user.username}"/></td>
                        <td><c:out value="${user.password}"/></td>
                        <td>

                            <c:choose>
                                <c:when test="${user.active==1}">
                                    ACTIVE
                                </c:when>
                                <c:otherwise>
                                    BLOCKED

                                </c:otherwise>
                            </c:choose>

                        </td>

                        <td><a class ="one" href="${CONTEXT}/updateUser?username=${user.username}" > <fmt:message key="UPDATE"/> </a>
                        </td>

                        <td>
                            <c:choose>

                                <c:when
                                        test="${user.username==CURRENT_SESSION_ACCOUNT}">
                                    <fmt:message key="CURRENT_ACCOUNT"/>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${user.active==1}">
                                            <a class ="one" href="${CONTEXT}/deactivateUser?username=${user.username}"
                                               onclick="return deactivateAccount()" > <fmt:message key="BLOCK"/> </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a class ="one" href="${CONTEXT}/activateUser?username=${user.username}"
                                               onclick="return activateAccount()" > <fmt:message key="UNBLOCK"/> </a>

                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <c:choose>

                            <c:when
                                    test="${user.username==CURRENT_SESSION_ACCOUNT}">
                                <fmt:message key="CURRENT_ACCOUNT"/>
                            </c:when>
                            <c:otherwise>
                            <a class ="one" href="" onclick="return deleteUser()" >
                                <fmt:message key="DELETE"/> </a></td>
                        </c:otherwise>
                        </c:choose>

                    </tr>
                </c:forEach>


                </tbody>

            </table>

        </c:otherwise>

    </c:choose>

</div>

