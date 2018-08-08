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
    <fmt:message bundle="${loc}" key="local.orders" var="orders" />
    <fmt:message bundle="${loc}" key="local.id" var="id" />
    <fmt:message bundle="${loc}" key="local.company" var="company" />
    <fmt:message bundle="${loc}" key="local.name" var="name" />
    <fmt:message bundle="${loc}" key="local.type" var="type" />
    <fmt:message bundle="${loc}" key="local.price" var="price" />
    <fmt:message bundle="${loc}" key="local.find" var="find" />
    <fmt:message bundle="${loc}" key="local.edit" var="edit" />
    <fmt:message bundle="${loc}" key="local.delete" var="delete" />
    <fmt:message bundle="${loc}" key="local.addProduct" var="addProduct" />
    <fmt:message bundle="${loc}" key="local.manageUsers" var="manageUsers" />
    <fmt:message bundle="${loc}" key="local.manageOrders" var="manageOrders" />
    <fmt:message bundle="${loc}" key="local.cannotFindProduct" var="cannotFindProduct" />
    <fmt:message bundle="${loc}" key="local.nothingHappened" var="nothingHappened" />

    <c:set var="currentPage" value="${requestScope.currentPage}"/>
    <c:set var="maxPage" value="${requestScope.maxPage}"/>

    <title>
        <c:out value="${requestScope.product.name}"/>
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
                <c:out value="${requestScope.product.name}" />
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
                <input type="hidden" name="command" value="showAllUsers"/>
                <input type="hidden" name="page" value="1"/>
                <button style="min-width:100px;height:75px;white-space:pre-line;" class="btn btn-default" type="submit">
                    <c:out value="${manageUsers}"/>
                </button>
            </form>
        </div>
        <div class="col-md-1" style="padding-top:10px;">
            <form method="get" action="FrontController">
                <input type="hidden" name="command" value="goToPage"/>
                <input type="hidden" name="address" value="orders.jsp"/>
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
        <li role="presentation" class="active">
            <a href="FrontController?command=goToPage&address=main.jsp">
                <c:out value="${main}"/>
            </a>
        </li>
    </ul>
</div>

<!-- MAIN -->
<c:if test="${requestScope.product == null}">
    <div class="panel-body">
        <div class="alert alert-info" role="alert" style="padding:15px">
            <h3><c:out value="${cannotFindProduct}"/></h3>
        </div>
    </div>
</c:if>

<c:if test="${requestScope.updateFailed == true}">
    <div class="panel-body">
        <div class="alert alert-info" role="alert" style="padding:15px">
            <h3><c:out value="${nothingHappened}"/></h3>
        </div>
    </div>
</c:if>

<c:if test="${requestScope.product != null}">
    <div class="col-md-12">
        <!-- PICTURE -->
        <div class="col-md-4">
            <div class="panel panel-default" style="margin-top:15px">
                <div class="panel-body">
                    <%--product-picture todo--%>
                </div>
            </div>
        </div>
        <!-- INFO -->
        <div class="col-md-8 ">
            <div class="panel panel-default" style="margin-top:15px">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <c:out value="${edit}"/>
                    </h3>
                </div>
                <div class="panel-body">
                    <table class="table table-hover" >
                        <thead style="color: #464a4c;background-color: #eceeef;">
                        <tr style="text-align: center;">
                            <td><c:out value="${id}"/></td>
                            <td><c:out value="${company}"/></td>
                            <td><c:out value="${name}"/></td>
                            <td><c:out value="${type}"/></td>
                            <td><c:out value="${price}"/></td>
                            <td>
                                <form id="deleteForm" action="FrontController" method="post">
                                    <input form="deleteForm"  type="hidden" name="command" value="deleteProduct" />
                                    <input form="deleteForm"  type="hidden" name="id" value="${requestScope.product.id}" />
                                    <input form="deleteForm" class="btn btn-default" type="submit" name="delete" value="${delete}" /><br/>
                                </form>
                            </td>
                        </tr>
                        </thead>

                        <tbody>
                            <form id="editProduct" action="FrontController" method="post">
                                <div class="form-group">
                                    <tr style="text-align: center">
                                        <td>
                                            <input type="hidden" name="command" value="updateProduct" />
                                            <input type="hidden" name="id" value="${requestScope.product.id}" title=""/>
                                            ${requestScope.product.id}
                                        </td>
                                        <td><input class="form-control" type="text" name="company" value="${requestScope.product.company}" title=""/></td>
                                        <td><input class="form-control" type="text" name="name" value="${requestScope.product.name}" title=""/></td>
                                        <td><input class="form-control" type="text" name="type" value="${requestScope.product.type}" title=""/></td>
                                        <td><input class="form-control" type="text" name="price" value="${requestScope.product.price}" title=""/></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td colspan="5">
                                            <textarea form="editProduct" class="form-control" rows="10" cols="45" name="description">${requestScope.product.description}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="submit" form="editProduct" name="edit" value="${edit}" class="btn btn-default"/>
                                        </td>
                                    </tr>
                                </div>
                            </form>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
</c:if>

</body>
</html>