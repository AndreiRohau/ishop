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
        <fmt:message bundle="${loc}" key="local.basket" var="basket" />
        <fmt:message bundle="${loc}" key="local.updateProfile" var="updateProfile" />
        <fmt:message bundle="${loc}" key="local.orders" var="orders" />
        <fmt:message bundle="${loc}" key="local.profile" var="profile" />
        <fmt:message bundle="${loc}" key="local.info" var="info" />
        <fmt:message bundle="${loc}" key="local.login" var="login" />
        <fmt:message bundle="${loc}" key="local.password" var="password" />
        <fmt:message bundle="${loc}" key="local.unequalPasswords" var="unequalPasswords" />
        <fmt:message bundle="${loc}" key="local.newPassword" var="newPassword" />
        <fmt:message bundle="${loc}" key="local.change" var="change" />
        <fmt:message bundle="${loc}" key="local.delete" var="delete" />
        <fmt:message bundle="${loc}" key="local.changePasswordText" var="changePasswordText" />
        <fmt:message bundle="${loc}" key="local.deleteAccountText" var="deleteAccountText" />
        <fmt:message bundle="${loc}" key="local.nothingHappened" var="nothingHappened" />

        <title>
            <c:out value="${profile}"/>
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
                        <c:out value="${profile}" />
                    </h1>
                </div>
                <div class="col-md-1" style="padding-top:10px;">
                    <form method="get" action="FrontController">
                        <input type="hidden" name="command" value="selectAllReserved"/>
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
            <li role="presentation">
                <a href="FrontController?command=selectAllReserved&page=1">
                    <c:out value="${basket}"/>
                </a>
            </li>
            <li role="presentation">
                <a href="FrontController?command=showAllMyOrders&page=1">
                    <c:out value="${orders}"/>
                </a>
            </li>
        </ul>
    </div>

    <!-- MAIN -->
    <div class="col-md-12">
        <!-- CHANGE PASSWORD -->
        <div class="col-md-6">
            <div class="panel panel-default" style="margin-top:15px">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <c:out value="${changePasswordText}"/>
                    </h3>
                </div>
                <div class="panel-body">
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="changePassword"/><br/>
                        <div class="form-group">
                            <label for="login">
                                <c:out value="${login}" />:
                            </label>
                            <input type="text" class="form-control" id="login" placeholder="${login}" name="login"/>
                        </div>
                        <div class="form-group">
                            <label for="password">
                                <c:out value="${password}" />:
                            </label>
                            <input type="password" class="form-control" id="password" placeholder="${password}" name="password"/>
                        </div>
                        <div class="form-group">
                            <label for="newPassword">
                                <c:out value="${newPassword}" />:
                            </label>
                            <input type="password" class="form-control" id="newPassword" placeholder="${newPassword}" name="newPassword"/>
                        </div>
                        <input type="submit" class="btn btn-default" name="change" value="${change}"/>
                    </form>


                    <c:if test="${requestScope.errorMessage == 'changePasswordError'}">
                        <div class="alert alert-danger" role="alert">
                            <p>
                                <span>
                                    <c:out value="${nothingHappened}"/>
                                </span>
                            </p>
                        </div>
                    </c:if>
                    <c:if test="${requestScope.isChanged != null}">
                        <div class="alert alert-info" role="alert">
                            <p>
                                <span>
                                    New password is [<c:out value="${requestScope.isChanged}"/>].
                                </span>
                            </p>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
        <!-- DELETE -->
        <div class="col-md-6 ">
            <div class="panel panel-default" style="margin-top:15px">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <c:out value="${deleteAccountText}"/>
                    </h3>
                </div>
                <div class="panel-body">
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="deleteUser"/><br/>
                        <div class="form-group">
                            <label for="username">
                                <c:out value="${login}" />:
                            </label>
                            <input type="text" class="form-control" id="username" placeholder="${login}" name="login" value=""/>
                        </div>
                        <div class="form-group">
                            <label for="password1">
                                <c:out value="${password}" />:
                            </label>
                            <input type="password" class="form-control" id="password1" placeholder="${password}" name="password" value=""/>
                        </div>
                        <input type="submit" class="btn btn-default" name="delete" value="${delete}"/>
                    </form>

                    <c:if test="${requestScope.errorMessage == 'deleteUserError'}">
                        <div class="alert alert-info" role="alert">
                            <span>
                                <c:out value="${nothingHappened}"/>
                            </span>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>

    </div>

    </body>
</html>