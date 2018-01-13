<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<div class="table-responsive">

    <c:choose>


        <c:when test="${empty percentage}">

            <big><fmt:message key="NO_RESULT"/></big>


        </c:when>

        <c:otherwise>
            <form action="${CONTEXT}/downloadPDF" method="post">

                <input type="hidden" value="${total}" name="total" />

                <input type="submit" class="btn  btn-primary " style="margin-bottom: 10px" value="Save PDF"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>


            <table class="table table-hover">

                <thead>
                <tr class="info">

                    <th><fmt:message key="COMPOUND_INGRIDIENT"/></th>
                    <th><fmt:message key="ORDER"/></th>
                    <th><fmt:message key="INGRIDIENT"/></th>
                    <th><fmt:message key="%_IN_PRODUCT_MIXING_BOWL"/></th>
                    <th><fmt:message key="%_IN_FINISHED_PRODUCT"/></th>
                    <th><fmt:message key="TOTAL"/></th>
                    <th id="quid"><fmt:message key="QUID"/></th>
                    <th><fmt:message key="DECLARATION"/></th>
                    <th><fmt:message key="ALLERGEN"/></th>
                    <th><fmt:message key="FUNCTION"/></th>


                </tr>
                </thead>
                <tbody>
                <c:forEach items="${percentage}" var="percentage">
                    <tr>

                        <td><c:out value="${percentage.parent}"/></td>
                        <td><c:out value="${percentage.position}"/></td>
                        <td><c:out value="${percentage.name}" escapeXml="false"/></td>
                        <td><c:out value="${percentage.percentage}" escapeXml="false"/></td>
                        <td><c:out value="${percentage.mixBowlPercentage}" escapeXml="false"/></td>
                        <td><c:out value="${percentage.total}" escapeXml="false"/></td>

                        <td>
                            <c:choose>
                                <c:when test="${percentage.parent == product}">

                                    <input type=checkbox name="${percentage.name}" id="${percentage.position}"
                                           style="width: 15px ; height: 15px;">


                                </c:when>

                            </c:choose>

                        </td>

                        <td>
                            <input type=checkbox name="${percentage.name}" id="${percentage.position}"
                                   style="width: 15px ; height: 15px;">
                        </td>




                        <td>
                            <c:choose>
                                <c:when test="${percentage.component == true  && percentage.name != 'Pork'}">
                                   <%-- &&  percentage.total !=''--%>
                                    <c:choose>

                                        <c:when test="${percentage.total != ''}">



                                            <select style="width: 200px" onchange="selectAllergen(this)">
                                                <c:forEach var="allergen" items="${allergens}">

                                                    <option value="${percentage.name}"  ${allergen == percentage.allergen ? 'selected="selected"' : ''}>${allergen}</option>

                                                </c:forEach>
                                            </select>

                                        </c:when>
                                        <c:when test="${percentage.allergen != 'N/A'}">

                                            <c:out value="${percentage.allergen}"/>

                                        </c:when>



                                        <c:otherwise>


                                            <c:out value="N/A"/>


                                        </c:otherwise>
                                    </c:choose>

                                </c:when>

                                <c:otherwise>

                                    <c:choose>

                                    <c:when test="${percentage.name != 'Pork'}">

                                        <c:out value="COMPOUND"/>

                                    </c:when>

                                    </c:choose>


                                </c:otherwise>
                            </c:choose>

                        </td>

                        <td>


                            <select style="width: 200px">
                                <c:forEach var="function" items="${functions}">

                                    <option value="${percentage.name}">${function}</option>

                                </c:forEach>
                            </select>

                        </td>

                    </tr>
                </c:forEach>


                </tbody>

            </table>

        </c:otherwise>

    </c:choose>

    <p class="text-right " style="margin-right: 40px"><strong><fmt:message key="TOTAL"/>= ${total}</strong></p>


</div>


<button onclick="getQD()" class="btn  btn-primary" style="margin-bottom: 10px" ><fmt:message key="DECLARED_INGREDIENT_LIST"/></button>

&nbsp;

<div id="qd"></div>



