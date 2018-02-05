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

            <table class="table table-hover">

                <thead>
                <tr class="info">

                    <th><fmt:message key="COMPOUND_INGRIDIENT"/></th>
                    <th><fmt:message key="ORDER"/></th>
                    <th><fmt:message key="INGRIDIENT"/></th>
                    <th><fmt:message key="%_IN_PRODUCT_MIXING_BOWL"/></th>
                    <th><fmt:message key="%_IN_FINISHED_PRODUCT"/></th>
                    <th><fmt:message key="TOTAL"/></th>
                    <th><fmt:message key="TOTAL_SALT"/></th>
                    <th><fmt:message key="TOTAL_WATER"/></th>
                    <th id="quid"><fmt:message key="QUID"/></th>
                    <th><fmt:message key="DECLARATION"/></th>
                    <th><fmt:message key="ALLERGEN"/></th>
                    <th><fmt:message key="FUNCTION"/></th>


                </tr>
                </thead>
                <tbody>
                <c:forEach items="${percentage}" var="percentage" varStatus="status">
                    <tr>

                        <td><c:out value="${percentage.parent}"/></td>
                        <td><c:out value="${percentage.position}"/></td>
                        <td><c:out value="${percentage.name}" escapeXml="false"/></td>
                        <td><c:out value="${percentage.percentage}" escapeXml="false"/></td>
                        <td><c:out value="${percentage.mixBowlPercentage}" escapeXml="false"/></td>
                        <td><c:out value="${percentage.total}" escapeXml="false"/></td>
                        <td><c:out value="${percentage.totalSalt}" escapeXml="false"/></td>
                        <td><c:out value="${percentage.totalWater}" escapeXml="false"/></td>

                        <td>

                            <c:choose>

                                <c:when test="${not empty quidded}">


                                    <c:choose>

                                        <c:when test="${quidded[status.index]=='Yes'}">

                                            <input type=checkbox name="${percentage.name}"
                                                   id="${percentage.position}"
                                                   style="width: 15px ; height: 15px;" checked>

                                        </c:when>
                                        <c:otherwise>


                                            <input type=checkbox name="${percentage.name}"
                                                   id="${percentage.position}"
                                                   style="width: 15px ; height: 15px;">

                                        </c:otherwise>
                                    </c:choose>


                                </c:when>
                                <c:otherwise>

                                    <input type=checkbox name="${percentage.name}" id="${percentage.position}"
                                           style="width: 15px ; height: 15px;">
                                </c:otherwise>
                            </c:choose>

                        </td>
                            <%--Declaration--%>
                        <td>

                            <c:choose>
                                <c:when test="${not empty declared}">


                                    <c:choose>

                                        <c:when test="${declared[status.index]=='Yes'}">

                                            <input type=checkbox name="${percentage.name}"
                                                   id="${percentage.position}"
                                                   style="width: 15px ; height: 15px;" checked>

                                        </c:when>
                                        <c:otherwise>


                                            <input type=checkbox name="${percentage.name}"
                                                   id="${percentage.position}"
                                                   style="width: 15px ; height: 15px;">

                                        </c:otherwise>
                                    </c:choose>


                                </c:when>
                                <c:otherwise>

                                    <input type=checkbox name="${percentage.name}" id="${percentage.position}"
                                           style="width: 15px ; height: 15px;">
                                </c:otherwise>
                            </c:choose>
                        </td>

                            <%--End Declaration--%>

                            <%-- Allergen--%>

                        <td>

                            <c:choose>

                                <c:when test="${not empty allergen}">


                                    <c:choose>

                                        <c:when test="${allergen[status.index]!='N/A'}">

                                            <select style="width: 200px" onchange="selectAllergen(this)">
                                            <c:forEach var="allergenList" items="${allergenList}">

                                            <option value="${percentage.name}"  ${allergenList == allergen[status.index] ? 'selected="selected"' : ''}>${allergenList}</option>

                                            </c:forEach>
                                            </select>

                                        </c:when>
                                        <c:otherwise>

                                            <select style="width: 200px" onchange="selectAllergen(this)">
                                                <c:forEach var="allergenList" items="${allergenList}">

                                                    <option value="${percentage.name}">${allergenList}</option>
                                                </c:forEach>

                                            </select>

                                        </c:otherwise>
                                    </c:choose>


                                </c:when>
                                <c:otherwise>

                                    <select style="width: 200px" onchange="selectAllergen(this)">
                                        <c:forEach var="allergenList" items="${allergenList}">

                                        <option value="${percentage.name}">${allergenList}</option>
                                        </c:forEach>

                                    </select>
                                </c:otherwise>
                            </c:choose>



                        </td>

                            <%-- End Allergen--%>

                        <td>


                            <c:choose>

                                <c:when test="${not empty function}">


                                    <c:choose>

                                        <c:when test="${function[status.index]!='N/A'}">

                                            <select style="width: 200px" >
                                                <c:forEach var="functionList" items="${functionList}">

                                                    <option value="${percentage.name}"  ${functionList == function[status.index] ? 'selected="selected"' : ''}>${functionList}</option>

                                                </c:forEach>
                                            </select>

                                        </c:when>
                                        <c:otherwise>

                                            <select style="width: 200px" >
                                                <c:forEach var="functionList" items="${functionList}">

                                                    <option value="${percentage.name}">${functionList}</option>
                                                </c:forEach>

                                            </select>

                                        </c:otherwise>
                                    </c:choose>


                                </c:when>
                                <c:otherwise>

                                    <select style="width: 200px" >
                                        <c:forEach var="functionList" items="${functionList}">

                                            <option value="${percentage.name}">${functionList}</option>
                                        </c:forEach>

                                    </select>
                                </c:otherwise>
                            </c:choose>



                        <%--<select style="width: 200px">--%>
                                <%--<c:forEach var="functionList" items="${functionList}">--%>

                                    <%--<option value="${percentage.name}">${functionList}</option>--%>

                                <%--</c:forEach>--%>
                            <%--</select>--%>

                        </td>

                    </tr>
                </c:forEach>


                </tbody>

            </table>


            <p class="text-right " style="margin-right: 40px"><strong><fmt:message key="TOTAL"/>= ${total}</strong></p>


            </div>


            <button onclick="getQD()" class="btn  btn-primary" style="margin-bottom: 10px"><fmt:message
                    key="SYSTEM_GENERATED_INGREDIENT_LIST"/></button>

            &nbsp;

            <c:choose>


                <c:when test="${declaration != ''}">

                    <div id="qd">${declaration}</div>

                </c:when>

                <c:otherwise>

                    <div id="qd"></div>

                </c:otherwise>

                </c:choose>




            &nbsp;
            &nbsp;

            <div class="wmd-panel">
                <div  class="text-primary font-weight-bold " style="margin-bottom: 10px" > <fmt:message
                        key="DECLARED_INGREDIENT_LIST"/>:


                            <form action="${CONTEXT}/saveDeclarationEditable" method="post">

                                <input id="myinput1" type="hidden"  name="data"/>
                                <input type="hidden"  name="productName" value="${product}"/>

                                <input onclick="getDeclaredIngredientList()" type="submit" class="btn  btn-primary " style="margin-top: 10px" value="Save"/>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                <textarea id = "tiny"  >${declarationEditable}</textarea>
                            </form>


                </div>



            </div>

            <form action="${CONTEXT}/downloadPDF" method="post" target="_blank">

                <input id="myinput" type="hidden"  name="data"/>

                <input onclick="getDeclaredIngredientList()" type="submit" class="btn  btn-primary " style="margin-top: 10px" value="Save PDF"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>

        </c:otherwise>

    </c:choose>



