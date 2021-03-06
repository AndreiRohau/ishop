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
    <fmt:message bundle="${loc}" key="local.id" var="id" />
    <fmt:message bundle="${loc}" key="local.company" var="company" />
    <fmt:message bundle="${loc}" key="local.name" var="name" />
    <fmt:message bundle="${loc}" key="local.type" var="type" />
    <fmt:message bundle="${loc}" key="local.price" var="price" />
    <fmt:message bundle="${loc}" key="local.find" var="find" />
    <fmt:message bundle="${loc}" key="local.edit" var="edit" />
    <fmt:message bundle="${loc}" key="local.addProduct" var="addProduct" />
    <fmt:message bundle="${loc}" key="local.manageUsers" var="manageUsers" />
    <fmt:message bundle="${loc}" key="local.users" var="users" />
    <fmt:message bundle="${loc}" key="local.login" var="login" />
    <fmt:message bundle="${loc}" key="local.orders" var="orders" />
    <fmt:message bundle="${loc}" key="local.edit" var="edit" />
    <fmt:message bundle="${loc}" key="local.manageOrders" var="manageOrders" />

    <title>
        <c:out value="${users}"/>
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
                    <c:out value="${users}" />
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
            <li role="presentation" class="active">
                <a href="FrontController?command=showUsers&page=1">
                    <c:out value="${users}"/>
                </a>
            </li>
        </ul>
    </div>

    <!-- MAIN -->
    <div class="col-md-12">
        <div class="panel panel-default" style="margin-top:15px">
            <div class="panel-heading">
                <c:out value="${users}"/>
            </div>
            <div class="panel-body">
                <c:if test="${requestScope.page.currentPage != null}">
                    <table class="table table-hover" >
                        <thead style="color: #464a4c;background-color: #eceeef;">
                        <tr style="text-align: center;">
                            <td><h4><c:out value="${id}"/></h4></td>
                            <td><h4><c:out value="${login}"/></h4></td>
                            <td><h4><c:out value="${orders}"/></h4></td>
                            <td><h4><c:out value="${edit}"/></h4></td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.users}" var="user">
                            <tr style="text-align: center">
                                <td>${user.id}</td>
                                <td>${user.login}</td>
                                <td>
                                    <form action="FrontController" method="post">
                                        <input type="hidden" name="command" value="showUserOrders" />
                                        <input type="hidden" name="id" value="${user.id}" />
                                        <input type="hidden" name="login" value="${user.login}" />
                                        <input type="hidden" name="page" value="1" />
                                        <button type="submit" class="btn btn-default">${orders}</button><br/>
                                    </form>
                                </td>
                                <td>
                                    <form action="FrontController" method="post">
                                        <input type="hidden" name="command" value="userInfo" />
                                        <input type="hidden" name="id" value="${user.id}" />
                                        <input type="submit" name="edit" value="${edit}" class="btn btn-default"/><br/>
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
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>