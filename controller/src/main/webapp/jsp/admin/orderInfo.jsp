<!--
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" type="text/css" href="bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous"/>
    <link rel="stylesheet" type="text/css" href="my.css"/>

    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.ch" var="ch_button" />
    <fmt:message bundle="${loc}" key="local.logOut" var="logOut" />
    <fmt:message bundle="${loc}" key="local.admin" var="admin" />
    <fmt:message bundle="${loc}" key="local.user" var="user" />
    <fmt:message bundle="${loc}" key="local.anonymous" var="anonymous" />
    <fmt:message bundle="${loc}" key="local.home" var="home" />
    <fmt:message bundle="${loc}" key="local.main" var="main" />
    <fmt:message bundle="${loc}" key="local.order" var="order" />
    <fmt:message bundle="${loc}" key="local.allOrders" var="allOrders" />
    <fmt:message bundle="${loc}" key="local.addProduct" var="addProduct" />
    <fmt:message bundle="${loc}" key="local.manageUsers" var="manageUsers" />
    <fmt:message bundle="${loc}" key="local.manageOrders" var="manageOrders" />
    <fmt:message bundle="${loc}" key="local.status" var="status" />
    <fmt:message bundle="${loc}" key="local.change" var="change" />
    <fmt:message bundle="${loc}" key="local.address" var="address" />
    <fmt:message bundle="${loc}" key="local.phone" var="phone" />
    <fmt:message bundle="${loc}" key="local.info" var="info" />
    <fmt:message bundle="${loc}" key="local.company" var="company" />
    <fmt:message bundle="${loc}" key="local.name" var="name" />
    <fmt:message bundle="${loc}" key="local.type" var="type" />
    <fmt:message bundle="${loc}" key="local.price" var="price" />
    <fmt:message bundle="${loc}" key="local.remove" var="remove" />

    <c:set var="currentPage" value="${requestScope.currentPage}"/>
    <c:set var="maxPage" value="${requestScope.maxPage}"/>
    <c:set var="indexRemovingProduct" value="1"/>

    <title>
        <c:out value="${order} ${requestScope.order.id}"/>
    </title>
</head>
<body>
    <!-- HEADER -->
    <div class="headerAnim" >
        <div class="row">
            <div class="col-md-1">
                <div class="col-md-12" style="padding-bottom:15px; padding-top:5px">
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="changeLanguage"/>
                        <input type="hidden" name="local" value="en"/>
                        <button class="btn btn-default" type="submit" name="lang" value="en_EN">
                            EN
                        </button>
                    </form>
                </div>
                <div class="col-md-12">
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="changeLanguage"/>
                        <input type="hidden" name="local" value="ru"/>
                        <button class="btn btn-default" type="submit" name="lang" value="ru_RU">
                            РУ
                        </button>
                    </form>
                </div>
            </div>
            <div class="col-md-1" style="text-align:center">
                <c:if test="${sessionScope.role == 'admin'}">
                    <span>
                        <c:out value="${admin}"/>
                    </span>
                </c:if>
                <c:if test="${sessionScope.role == 'user'}">
                    <span>
                        <c:out value="${user}"/>
                    </span>
                </c:if>
                <c:if test="${sessionScope.role == null}">
                    <span>
                        <c:out value="${anonymous}"/>
                    </span>
                </c:if>
            </div>
            <div class="col-md-6" style="text-align:center">
                <h1>
                    <c:out value="${order} ${requestScope.order.id}" />
                </h1>
            </div>
            <div class="col-md-1" style="padding-top:10px;">
                <form method="get" action="FrontController">
                    <input type="hidden" name="command" value="goToPage"/>
                    <input type="hidden" name="address" value="addProduct.jsp"/>
                    <button style="min-width:100px;height:75px;white-space:pre-line;" class="btn btn-default" type="submit">
                        <c:out value="${addProduct}"/>
                    </button>
                </form>
            </div>
            <div class="col-md-1" style="padding-top:10px;">
                <form method="get" action="FrontController">
                    <input type="hidden" name="command" value="showUsers"/>
                    <input type="hidden" name="page" value="1"/>
                    <button style="min-width:100px;height:75px;white-space:pre-line;" class="btn btn-default" type="submit">
                        <c:out value="${manageUsers}"/>
                    </button>
                </form>
            </div>
            <div class="col-md-1" style="padding-top:10px;">
                <form method="get" action="FrontController">
                    <input type="hidden" name="command" value="showOrders"/>
                    <input type="hidden" name="page" value="1"/>
                    <button class="btn btn-default" type="submit" style="min-width:100px;height:75px;white-space:pre-line;" >
                        <c:out  value="${manageOrders}"/>
                    </button>
                </form>
            </div>
            <div class="col-md-1">
                <div class="col-md-12">
                    <h4>
                        <c:out value="${sessionScope.login}"/>
                    </h4>
                </div>
                <div class="col-md-12">
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="logout"/>
                        <button class="btn btn-default" type="submit" value="logOut">
                            <c:out value="${logOut}"/>
                        </button>
                    </form>
                </div>
            </div>
            <%--<div class="col-md-1">--%>
            <%--&lt;%&ndash;should be empty &ndash;%&gt;--%>
            <%--</div>--%>
        </div>
    </div>

    <!-- NAVIGATION -->
    <div class="well well-sm" style="padding: 30px 30px 0;background:0; border:1px; margin:0;">
        <ul class="nav nav-pills" >
            <li role="presentation">
                <a href="FrontController?command=goToPage&address=index.jsp">
                    <c:out value="${home}"/>
                </a>
            </li>
            <li role="presentation">
                <a href="FrontController?command=goToPage&address=main.jsp">
                    <c:out value="${main}"/>
                </a>
            </li>
            <li role="presentation">
                <a href="FrontController?command=userInfo&id=${requestScope.order.userId}">
                    <c:out value="${user} ${requestScope.order.userId}"/>
                </a>
            </li>
        </ul>
    </div>

    <!-- MAIN -->
    <div class="col-md-12">
        <!-- ORDER -->
        <div class="col-md-4">
            <div class="panel panel-default" style="margin-top:15px">
                <div class="panel-body">
                    <p><b>${order} : ${requestScope.order.id}</b></p>
                    <p><b>${address} : ${requestScope.order.userAddress}</b></p>
                    <p><b>${phone} : ${requestScope.order.userPhone}</b></p>
                    <label for="setOrderStatusform">${status} :</label>
                    <form id="setOrderStatusform" title="${change}" action="FrontController" method="post">
                        <input type="hidden" name="command" value="orderSetStatus" />
                        <input type="hidden" name="id" value="${requestScope.order.id}" />
                        <input class="btn btn-default" type="submit" name="status" value="${requestScope.order.status}" />
                    </form>
                    <br/>
                </div>
            </div>
        </div>
        <!-- INFO -->
        <div class="col-md-8 ">
            <div class="panel panel-default" style="margin-top:15px">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <c:out value="${info}"/>
                    </h3>
                </div>
                <div class="panel-body">
                    <c:if test="${currentPage != null}">
                        <table class="table table-hover" >
                            <thead style="color: #464a4c;background-color: #eceeef;">
                            <tr style="text-align: center;">
                                <td><h4><c:out value="${info}"/></h4></td>
                                <td><h4><c:out value="${company}"/></h4></td>
                                <td><h4><c:out value="${name}"/></h4></td>
                                <td><h4><c:out value="${type}"/></h4></td>
                                <td><h4><c:out value="${price}"/></h4></td>
                                <td><h4><c:out value="${remove}"/></h4></td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:set value="1" var="indexRemovingProduct"/>
                            <c:forEach items="${requestScope.products}" var="product">
                                <tr style="text-align: center">
                                    <td>
                                        <form action="FrontController" method="post">
                                            <input type="hidden" name="command" value="productInfo" />
                                            <input type="hidden" name="id" value="${product.id}" />
                                            <input type="submit" name="info" value="${info}" class="btn btn-default"/><br/>
                                        </form>
                                    </td>
                                    <td>${product.company}</td>
                                    <td>${product.name}</td>
                                    <td>${product.type}</td>
                                    <td>${product.price}</td>
                                    <td>
                                        <form action="FrontController" method="post">
                                            <input type="hidden" name="command" value="deleteFromOrder" />
                                            <input type="hidden" name="indexRemovingProduct" value="${indexRemovingProduct}" />
                                            <input type="hidden" name="currentPage" value="${currentPage}" />
                                            <input type="hidden" name="productIds" value="${requestScope.order.productIds}" />
                                            <input type="hidden" name="orderId" value="${requestScope.order.id}" />
                                            <input type="submit" name="remove" value="${remove}" class="btn btn-default"/><br/>
                                        </form>
                                        <c:set value="${indexRemovingProduct + 1}" var="indexRemovingProduct"/>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                        <ul class="pagination pull-right">
                            <c:forEach begin="1" end="${maxPage}" var="i">
                                <c:if test="${i == currentPage}">
                                    <li class="active">
                                        <a href="${requestScope.lastCMDneedPage}${i}">${i}</a>
                                    </li>
                                </c:if>
                                <c:if test="${i != currentPage}">
                                    <li>
                                        <a href="${requestScope.lastCMDneedPage}${i}">${i}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</body>
</html>