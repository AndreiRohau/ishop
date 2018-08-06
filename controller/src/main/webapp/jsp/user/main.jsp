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
		<fmt:message bundle="${loc}" key="local.goToProfile" var="goToProfile_button" />
		<fmt:message bundle="${loc}" key="local.shop" var="shop" />
		<fmt:message bundle="${loc}" key="local.admin" var="admin" />
		<fmt:message bundle="${loc}" key="local.client" var="client" />
		<fmt:message bundle="${loc}" key="local.anonymous" var="anonymous" />
		<fmt:message bundle="${loc}" key="local.hello" var="hello" />
		<fmt:message bundle="${loc}" key="local.home" var="home" />
		<fmt:message bundle="${loc}" key="local.main" var="main" />
		<fmt:message bundle="${loc}" key="local.basket" var="basket" />
		<fmt:message bundle="${loc}" key="local.orders" var="orders" />
		<fmt:message bundle="${loc}" key="local.info" var="info" />
		<fmt:message bundle="${loc}" key="local.reg_form" var="reg_form" />
		<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.name.ch" var="ch_button" />
		<fmt:message bundle="${loc}" key="local.loginationText" var="loginationText" />
		<fmt:message bundle="${loc}" key="local.registrationText" var="registrationText" />
		<fmt:message bundle="${loc}" key="local.successRegistration" var="successRegistration" />
		<fmt:message bundle="${loc}" key="local.login" var="login" />
		<fmt:message bundle="${loc}" key="local.password" var="password" />
		<fmt:message bundle="${loc}" key="local.logbutton" var="log_button" />
		<fmt:message bundle="${loc}" key="local.log_out_button" var="log_out_button" />
		<fmt:message bundle="${loc}" key="local.regbutton" var="reg_button" />
		<fmt:message bundle="${loc}" key="local.success_log_out" var="success_log_out" />
		<fmt:message bundle="${loc}" key="local.invalid_log_or_password" var="invalid_log_or_password" />
		<fmt:message bundle="${loc}" key="local.plz_log_out" var="plz_log_out" />
		<fmt:message bundle="${loc}" key="local.login_exists" var="login_exists" />
		<fmt:message bundle="${loc}" key="local.login_to_start" var="login_to_start" />
		<fmt:message bundle="${loc}" key="local.loginExists" var="loginExists" />
		<fmt:message bundle="${loc}" key="local.logout" var="logout" />
		<fmt:message bundle="${loc}" key="local.noSuchUser" var="noSuchUser" />
		<fmt:message bundle="${loc}" key="local.unequalPasswords" var="unequalPasswords" />
		<fmt:message bundle="${loc}" key="local.company" var="company" />
		<fmt:message bundle="${loc}" key="local.name" var="name" />
		<fmt:message bundle="${loc}" key="local.type" var="type" />
		<fmt:message bundle="${loc}" key="local.price" var="price" />
		<fmt:message bundle="${loc}" key="local.find" var="find" />
		<fmt:message bundle="${loc}" key="local.buy" var="buy" />

		<c:set var="current_page" value="${requestScope.currentPage}"/>
		<c:set var="max_page" value="${requestScope.maxPage}"/>

		<title>
			<c:out value="${main}"/>
		</title>
	</head>
	<body>
	<!-- HEADER -->
		<div class="headerAnim" >
			<div class="row">
				<div class="col-md-1">
					<div class="col-md-12" style="padding-bottom:15px; padding-top:5px">
						<form action="FrontController" method="post">
							<input type="hidden" name="command" value="change_language"/>
							<input type="hidden" name="local" value="en"/>
							<button class="btn btn-default" type="submit" name="lang" value="en_EN">
								EN
							</button>
						</form>
					</div>
					<div class="col-md-12">
						<form action="FrontController" method="post">
							<input type="hidden" name="command" value="change_language"/>
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
							<c:out value="${client}"/>
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
						<c:out value="${main}" />
					</h1>
				</div>			
				<div class="col-md-1" style="padding-top:10px;">
					<form method="get" action="FrontController">
						<input type="hidden" name="command" value="selectAllReserved"/>
						<input type="hidden" name="page_num" value="1"/>
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
							<c:out  value="${goToProfile_button}"/>
						</button>
					</form>
				</div>
				<div class="col-md-1">
					<div class="col-md-12">
						<h4>
							<c:out value="${sessionScope.userName}"/>
						</h4>
					</div>	
					<div class="col-md-12">
						<form action="FrontController" method="post">
							<input type="hidden" name="command" value="logout"/>
							<button class="btn btn-default" type="submit" value="log_out">
								<c:out value="${log_out_button}"/>
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
		<div class="well well-sm" style="padding:30px; padding-bottom:0px;  background:0; border:1px; margin:0%;">
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
				<li role="presentation">
					<a href="FrontController?command=selectAllReserved&page_num=1">
						<c:out value="${basket}"/>
					</a>
				</li>
				<li role="presentation">
					<a href="FrontController?command=showAllMyOrders&page_num=1">
						<c:out value="${orders}"/>
					</a>
				</li>
			</ul>		
		</div>
		
	<!-- MAIN -->
		<div class="col-md-12">
			<div class="panel panel-default" style="margin-top:15px">
				<div class="panel-heading">
					<form action="FrontController" method="post">
						<div class="form-inline">
							<input class="form-control" type="hidden" name="command" value="findSuitable"/>
							<input class="form-control" type="hidden" name="page_num" value="1"/>
							<input class="form-control" type="text" name="company" value="" placeholder="${company}"/>
							<input class="form-control" type="text" name="name" value="" placeholder="${name}"/>
							<input class="form-control" type="text" name="type" value="" placeholder="${type}"/>
							<input class="form-control" type="text" name="price" value="" placeholder="${price}"/>
							<input class="btn btn-default" type="submit" name="get_products" value="${find}!"/>
						</div>
					</form>
				</div>
				<div class="panel-body">
					<c:if test="${current_page != null}">
						<table class="table table-hover" >
							<thead style="color: #464a4c;background-color: #eceeef;">
								<tr style="text-align: center;">
									<td><h4><c:out value="${info}"/></h4></td>
									<td><h4><c:out value="${company}"/></h4></td>
									<td><h4><c:out value="${name}"/></h4></td>
									<td><h4><c:out value="${type}"/></h4></td>
									<td><h4><c:out value="${price}"/></h4></td>
									<td><h4><c:out value="${buy}"/></h4></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${requestScope.productArray}" var="product">
									<tr style="text-align: center">
										<td>
											<form action="FrontController" method="post">
												<input type="hidden" name="command" value="productInfo" />
												<input type="hidden" name="productId" value="${product.id}" />
												<input type="submit" name="info" value="${info}" class="btn btn-default"/><br/>
											</form>
										</td>
										<td>${product.company}</td>
										<td>${product.name}</td>
										<td>${product.type}</td>
										<td>${product.price}</td>
										<td>
											<form action="FrontController" method="post">
												<input type="hidden" name="command" value="addToBasket" />
												<input type="hidden" name="productId" value="${product.id}" />
												<input type="submit" name="buy" value="${buy}" class="btn btn-default"/><br/>
											</form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<ul class="pagination pull-right">
							<c:forEach begin="1" end="${max_page}" var="i">
					            <c:if test="${i == current_page}">
					                <li class="active">
										<a href="${sessionScope.lastCMDneedPage}${i}">${i}</a>
									</li>
								</c:if>
					            <c:if test="${i != current_page}">
									<li>
						        		<a href="${sessionScope.lastCMDneedPage}${i}">${i}</a>
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