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
		<fmt:message bundle="${loc}" key="local.shop" var="shop" />
		<fmt:message bundle="${loc}" key="local.admin" var="admin" />
		<fmt:message bundle="${loc}" key="local.client" var="client" />
		<fmt:message bundle="${loc}" key="local.anonymous" var="anonymous" />
		<fmt:message bundle="${loc}" key="local.hello" var="hello" />
		<fmt:message bundle="${loc}" key="local.home" var="home" />
		<fmt:message bundle="${loc}" key="local.main" var="main" />
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
		<fmt:message bundle="${loc}" key="local.goToProfile" var="goToProfile_button" />
		<fmt:message bundle="${loc}" key="local.basket" var="basket" />

		<title>
			<c:out value="${home}"/>
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
				</div>
				<div class="col-md-5" style="text-align:center">
					<h1>
						<c:out value="${hello}" />
					</h1>
				</div>
				<c:if test="${sessionScope.role == null}">
					<div class="col-md-1" style="padding-top:10px;">
						<label for="login" style="padding-bottom:25px;">
							<c:out value="${login}" />:
						</label>
						<br/>
						<label for="password">
							<c:out value="${password}" />:
						</label>

					</div>
					<div class="col-md-2">
						<div style="padding-top:5px">
							<form action="FrontController" id="login_form" method="post">
								<input type="hidden" name="command" value="logination" />
								<div class="form-group">
									<input type="text" class="form-control" id="login" placeholder="${login}" name="login" value="" />
								</div>
								<div class="form-group">
									<input type="password" class="form-control" id="password" placeholder="${password}" name="password" value="" />
								</div>
							</form>
						</div>
					</div>
					<div class="col-md-1">
						<div class="col-md-12" style="padding-bottom:15px; padding-top:5px">
							<button form="login_form" class="btn btn-default" type="submit" name="log_in" value="log_in">
								<c:out value="${log_button}"/>
							</button>
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
					<div class="col-md-1">
							<%--should be empty --%>
					</div>
				</c:if>
				<c:if test="${sessionScope.role == 'user'}">
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
				</c:if>
				<c:if test="${sessionScope.role == 'admin'}">
					<div class="col-md-1" style="padding-top:10px;">

					</div>
					<div class="col-md-1" style="padding-top:10px;">

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
				</c:if>


			</div>
		</div>

	<!-- NAVIGATION -->
		<div class="well well-sm" style="padding:30px; padding-bottom:0px;  background:0; border:1px; margin:0%;">
			<ul class="nav nav-pills" >
				<li role="presentation" class="active">
					<a href="FrontController?command=goToPage&address=index.jsp">
						<c:out value="${home}"/>
					</a>
				</li>
				<c:if test="${sessionScope.role != null}">
					<li role="presentation">
						<a href="FrontController?command=goToPage&address=main.jsp">
							<c:out value="${main}"/>
						</a>
					</li>
				</c:if>
			</ul>		
		</div>
		
	<!-- MAIN -->
		<div class="col-md-12">
			<!-- INFO -->
				<div class="col-md-6">
					<div class="panel panel-default" style="margin-top:15px">
						<div class="panel-heading">
							<h3 class="panel-title">
								<c:out value="${info}"/>
							</h3>
						</div>
						<div class="panel-body">
							<c:if test="${sessionScope.role == null}">
								<div class="alert alert-info" role="alert">
									<p>
										<span>
											<c:out value="${login_to_start}"/>
										</span>
									</p>
								</div>
							</c:if>
							<c:if test="${requestScope.isRegistered == true}">
								<div class="alert alert-success" role="alert">
									<p>
										<span>
											<c:out value="${successRegistration}"/>
										</span>
									</p>
								</div>
							</c:if>
							<c:if test="${requestScope.errorMessage == 'exists'}">
								<div class="alert alert-danger" role="alert">
									<p>
										<span>
											<c:out value="${loginExists}"/>
										</span>
									</p>
								</div>
							</c:if>
							<c:if test="${requestScope.errorMessage == 'logout'}">
								<div class="alert alert-info" role="alert">
									<p>
										<span>
											<c:out value="${logout}"/>
										</span>
									</p>
								</div>
							</c:if>
							<c:if test="${requestScope.errorMessage == 'noSuchUser'}">
								<div class="alert alert-danger" role="alert">
									<p>
										<span>
											<c:out value="${noSuchUser}"/>
										</span>
									</p>
								</div>
							</c:if>
							<c:if test="${requestScope.errorMessage == 'passwordsUnequal'}">
								<div class="alert alert-danger" role="alert">
									<p>
										<span>
											<c:out value="${unequalPasswords}"/>
										</span>
									</p>
								</div>
							</c:if>


						</div>
					</div>
				</div>
			<!-- REGISTRATION -->
				<div class="col-md-6 ">
					<div class="panel panel-default" style="margin-top:15px">
						<div class="panel-heading">
							<h3 class="panel-title">
								<c:out value="${reg_form}"/>
							</h3>
						</div>
						<div class="panel-body">
							<form action="FrontController" method="post">
								<input type="hidden" name="command" value="registration" />
								<div class="form-group">
									<label for="username">
										<c:out value="${login}" />:
									</label>
									<input type="text" class="form-control" id="username" placeholder="${login}" name="login"/>
								</div>
								<div class="form-group">
									<label for="password1">
										<c:out value="${password}" />:
									</label>
									<input type="password" class="form-control" id="password1" placeholder="${password}" name="password"/>
								</div>
								<div class="form-group">
									<label for="password2">
										<c:out value="${password}" />:
									</label>
									<input type="password" class="form-control" id="password2" placeholder="${password}" name="password2"/>
								</div>
								<input type="submit" class="btn btn-default"  name="sign up" value="${reg_button}" /><br/>
							</form><br/>

						</div>
					</div>
				</div>			
			
		</div>
		
		
		
		
		
		
		
		
		
		
		
		
	</body>
</html>