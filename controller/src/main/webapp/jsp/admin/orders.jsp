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
    <fmt:message bundle="${loc}" key="local.logOut" var="logOut" />
    <fmt:message bundle="${loc}" key="local.admin" var="admin" />
    <fmt:message bundle="${loc}" key="local.user" var="user" />
    <fmt:message bundle="${loc}" key="local.anonymous" var="anonymous" />
    <fmt:message bundle="${loc}" key="local.hello" var="hello" />
    <fmt:message bundle="${loc}" key="local.home" var="home" />
    <fmt:message bundle="${loc}" key="local.main" var="main" />
    <fmt:message bundle="${loc}" key="local.basket" var="basket"/>
    <fmt:message bundle="${loc}" key="local.orders" var="orders" />
    <fmt:message bundle="${loc}" key="local.info" var="info" />
    <fmt:message bundle="${loc}" key="local.updateProfile" var="updateProfile" />
    <fmt:message bundle="${loc}" key="local.noSuchUser" var="noSuchUser" />
    <fmt:message bundle="${loc}" key="local.dateCreated" var="dateCreated" />
    <fmt:message bundle="${loc}" key="local.order" var="order" />
    <fmt:message bundle="${loc}" key="local.status" var="status" />
    <fmt:message bundle="${loc}" key="local.noOrdersFound" var="noOrdersFound" />
    <fmt:message bundle="${loc}" key="local.allYourOrders" var="allYourOrders" />
    <fmt:message bundle="${loc}" key="local.new" var="new" />
    <fmt:message bundle="${loc}" key="local.active" var="active" />
    <fmt:message bundle="${loc}" key="local.success" var="success" />

    <c:set var="currentPage" value="${requestScope.currentPage}"/>
    <c:set var="maxPage" value="${requestScope.maxPage}"/>

    <title>
        <c:out value="${requestScope.userId}"/>
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
                    <c:out value="${requestScope.userId}" />
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
        </ul>
    </div>

    <!-- MAIN -->
    <%--<c:if test="${requestScope.orders == '[]'}">--%>
        <%--<div class="panel-body">--%>
            <%--<div class="alert alert-info" role="alert" style="padding:15px">--%>
                <%--<h3><c:out value="${noOrdersFound}"/></h3>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</c:if>--%>

    <%--<c:if test="${requestScope.orders != '[]'}">--%>
    <div class="col-md-12">
        <!-- Control panel -->
        <div class="col-md-4">
            <div class="panel panel-default" style="margin-top:15px">
                <div class="panel-body">
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="selectAllNewOrders" />
                        <input type="hidden" name="page" value="1" />
                        <input class="btn btn-default" type="submit" name="get" value="${new} ${orders}" /><br/>
                    </form>
                    <br/>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="selectAllActiveOrders" />
                        <input type="hidden" name="page" value="1" />
                        <input class="btn btn-default" type="submit" name="get" value="${active} ${orders}" /><br/>
                    </form>
                    <br/>
                    <form id="deleteForm" action="FrontController" method="post">
                        <input type="hidden" name="command" value="selectAllSuccessOrders" />
                        <input type="hidden" name="page" value="1" />
                        <input class="btn btn-default" type="submit" name="get" value="${success} ${orders}" /><br/>
                    </form>
                </div>
            </div>
        </div>
        <!-- INFO -->
        <div class="col-md-8 ">
            <div class="panel panel-default" style="margin-top:15px">
                <div class="panel-heading">
                    <h3><c:out value="${allYourOrders}"/></h3>
                </div>
                <div class="panel-body">
                    <table class="table table-hover" >
                        <thead style="color: #464a4c;background-color: #eceeef;">
                        <tr style="text-align: center;">
                            <td><h4><c:out value="${user}"/></h4></td>
                            <td><h4><c:out value="${dateCreated}"/></h4></td>
                            <td><h4><c:out value="${status}"/></h4></td>
                            <td><h4><c:out value="${order}"/></h4></td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.orders}" var="order">
                            <tr style="text-align: center">
                                    <%--new: user ID - edit user --%>
                                <td>
                                    <p>
                                        ${order.dateCreated}
                                    </p>
                                </td>
                                    <%--order id - open order--%>
                                <td>
                                    <form title="Observe the order" action="FrontController" method="post">
                                        <input type="hidden" name="command" value="orderInfo" />
                                        <input type="hidden" name="from" value="allUsersOrders" />
                                        <input type="hidden" name="page" value="1"/>
                                        <input type="hidden" name="id" value="${order.id}" />
                                        <input class="btn btn-default" type="submit" name="button_ok" value="${order.id}" /><br/>
                                    </form>
                                </td>
                                    <%--set-active--%>
                                <td>
                                    <p>
                                            ${order.status}
                                    </p>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <ul class="pagination pull-right">
                        <c:forEach begin="1" end="${maxPage}" var="i">
                                        <c:if test="${i == currentPage}">
                                            <li class="active">
                            <a href="FrontController?command=showAllMyOrders&page=${i}">${i}</a>
                            </li>
                        </c:if>
                                                <c:if test="${i != currentPage}">
                            <li>
                                        		<a href="FrontController?command=showAllMyOrders&page=${i}">${i}</a>
                                            </li>
                        </c:if>
                                    </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    <%--</c:if>--%>
</body>
</html>