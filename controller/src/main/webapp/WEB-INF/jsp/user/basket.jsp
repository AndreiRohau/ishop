<!--
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
    <fmt:message bundle="${loc}" key="local.admin" var="admin" />
    <fmt:message bundle="${loc}" key="local.user" var="user" />
    <fmt:message bundle="${loc}" key="local.anonymous" var="anonymous" />
    <fmt:message bundle="${loc}" key="local.home" var="home" />
    <fmt:message bundle="${loc}" key="local.main" var="main" />
    <fmt:message bundle="${loc}" key="local.basket" var="basket"/>
    <fmt:message bundle="${loc}" key="local.orders" var="orders" />
    <fmt:message bundle="${loc}" key="local.info" var="info" />
    <fmt:message bundle="${loc}" key="local.updateProfile" var="updateProfile" />
    <fmt:message bundle="${loc}" key="local.logOut" var="logOut" />
    <fmt:message bundle="${loc}" key="local.buy" var="buy" />
    <fmt:message bundle="${loc}" key="local.address" var="address" />
    <fmt:message bundle="${loc}" key="local.phone" var="phone" />
    <fmt:message bundle="${loc}" key="local.productsInTheBasket" var="productsInTheBasket" />
    <fmt:message bundle="${loc}" key="local.company" var="company" />
    <fmt:message bundle="${loc}" key="local.name" var="name" />
    <fmt:message bundle="${loc}" key="local.type" var="type" />
    <fmt:message bundle="${loc}" key="local.price" var="price" />
    <fmt:message bundle="${loc}" key="local.remove" var="remove" />
    <fmt:message bundle="${loc}" key="local.basketIsEmpty" var="basketIsEmpty" />
    <fmt:message bundle="${loc}" key="local.orderCreated" var="orderCreated" />
    <fmt:message bundle="${loc}" key="local.orderNotCreated" var="orderNotCreated" />
    <fmt:message bundle="${loc}" key="local.deleted" var="deleted" />

    <title>
        <c:out value="${basket}"/>
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
            <div class="col-md-7" style="text-align:center">
                <h1>
                    <c:out value="${basket}" />
                </h1>
            </div>
            <div class="col-md-1" style="padding-top:10px;">
                <form method="get" action="FrontController">
                    <input type="hidden" name="command" value="showReserved"/>
                    <input type="hidden" name="page" value="1"/>
                    <button style="min-width:100px;height:75px" class="btn btn-default" type="submit">
                        <c:out value="${basket}"/>
                    </button>
                </form>
            </div>
            <div class="col-md-1" style="padding-top:10px;">
                <form method="get" action="FrontController">
                    <input type="hidden" name="command" value="goToPage"/>
                    <input type="hidden" name="address" value="profile.jsp"/>
                    <button class="btn btn-default" type="submit" style="min-width:100px;height:75px;white-space:pre-line;" >
                        <c:out  value="${updateProfile}"/>
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
            <li role="presentation" class="active">
                <a href="FrontController?command=showReserved&page=1">
                    <c:out value="${basket}"/>
                </a>
            </li>
            <li role="presentation">
                <a href="FrontController?command=showUserOrders&page=1">
                    <c:out value="${orders}"/>
                </a>
            </li>
        </ul>
    </div>

    <!-- MAIN -->
    <c:if test="${requestScope.products == '[]'}">
        <div class="panel-body">
            <div class="alert alert-info" role="alert" style="padding:15px">
                <h3><c:out value="${basketIsEmpty}"/></h3>
            </div>
        </div>
    </c:if>
    <c:if test="${requestScope.message == true}">
        <div class="panel-body">
            <div class="alert alert-info" role="alert" style="padding:15px">
                <h3><c:out value="${orderCreated}"/></h3>
            </div>
        </div>
    </c:if>
    <c:if test="${requestScope.message == false}">
        <div class="panel-body">
            <div class="alert alert-info" role="alert" style="padding:15px">
                <h3><c:out value="${orderNotCreated}"/></h3>
            </div>
        </div>
    </c:if>
    <c:if test="${requestScope.message == 'deleted'}">
        <div class="panel-body">
            <div class="alert alert-info" role="alert" style="padding:15px">
                <h3><c:out value="${deleted}"/></h3>
            </div>
        </div>
    </c:if>

    <c:if test="${ requestScope.products != '[]'}">
        <div class="col-md-12">
            <div class="panel panel-default" style="margin-top:15px">
                <div class="panel-heading">
                    <form action="FrontController" method="post">
                        <div class="form-inline">
                            <input class="form-control" type="hidden" name="command" value="createOrder"/>
                            <input class="form-control" type="text" name="userAddress" value="" placeholder="${address}" style="width:500px" required>
                            <input class="form-control" type="text" pattern="[0-9]{7-12}" name="userPhone" value="" placeholder="${phone}" required/>
                            <input class="btn btn-default" type="submit" name="buy" value="${buy}"/>
                        </div>
                    </form>
                </div>
                <div class="panel-body">
                    <p><c:out value="${productsInTheBasket}"/></p>
                    <%--<hr/>--%>
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
                        <c:forEach items="${requestScope.products}" var="product">
                            <tr style="text-align: center">
                                <td>
                                    <form action="FrontController" method="post">
                                        <input type="hidden" name="command" value="productInfo" />
                                        <input type="hidden" name="id" value="${product.id}" />
                                        <button type="submit" class="btn btn-default">${info}</button><br/>
                                    </form>
                                </td>
                                <td>${product.company}</td>
                                <td>${product.name}</td>
                                <td>${product.type}</td>
                                <td>${product.price}</td>
                                <td>
                                    <form action="FrontController" method="post">
                                        <input type="hidden" name="command" value="deleteReserve" />
                                        <input type="hidden" name="reserveId" value="${product.reserveId}" />
                                        <button type="submit" class="btn btn-default">${remove}</button><br/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <ul class="pagination pull-right">
                        <c:forEach begin="1" end="${requestScope.page.maxPage}" var="i">
                            <c:if test="${i == requestScope.page.currentPage}">
                                <li class="active">
                                    <a href="${sessionScope.lastCommandNeedPage}${i}">${i}</a>
                                </li>
                            </c:if>
                            <c:if test="${i != requestScope.page.currentPage}">
                                <li>
                                    <a href="${sessionScope.lastCommandNeedPage}${i}">${i}</a>
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </c:if>

</body>
</html>