<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="style.css">
	<title>Logination</title>

	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="localization.local" var="loc" />
	<fmt:message bundle="${loc}" key="local.shop" var="shop" />
	<fmt:message bundle="${loc}" key="local.hello" var="hello" />
	<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
	<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
	<fmt:message bundle="${loc}" key="local.locbutton.name.ch" var="ch_button" />
	<fmt:message bundle="${loc}" key="local.loginationText" var="loginationText" />
	<fmt:message bundle="${loc}" key="local.registrationText" var="registrationText" />
	<fmt:message bundle="${loc}" key="local.successRegistration" var="successRegistration" />
	<fmt:message bundle="${loc}" key="local.login" var="login" />
	<fmt:message bundle="${loc}" key="local.password" var="password" />
	<fmt:message bundle="${loc}" key="local.logbutton" var="log_button" />
	<fmt:message bundle="${loc}" key="local.regbutton" var="reg_button" />

</head>

<body>

<div class="header">
	<div id="header1">
		<p>
			<c:out value="${shop}" />
		</p>
		<p>
			<c:out value="${hello}" />
		</p>
	</div>

	<div id="header2" style="display:flex; flex-flow: row wrap; justify-content:space-between">
		<div>
			<form action="FrontController" method="post">
				<input type="hidden" name="command" value="change_language"/>
				<input type="hidden" name="local" value="en"/>
				<input type="submit" value="ENG"/>
			</form>
			<form action="FrontController" method="post">
				<input type="hidden" name="command" value="change_language"/>
				<input type="hidden" name="local" value="ru"/>
				<input type="submit" value="РУС"/>
			</form>
			<form action="FrontController" method="post">
				<input type="hidden" name="command" value="change_language"/>
				<input type="hidden" name="local" value="ch"/>
				<input type="submit" value="汉语"/>
			</form>
		</div>
		<div>
			<form action="FrontController" method="post" style="height:100%">
				<input type="hidden" name="command" value="logout"/>
				<input type="submit" value="Log out" style="height:100%"/>
			</form>
		</div>
	</div>

</div>

<div class="middle">
	<div id="menu">

		<!-- LOGINATION -->
		<hr/>
		<p>
			<c:out value="${loginationText}" />
		</p>
		<hr/>
		<form action="FrontController" method="post">
			<input type="hidden" name="command" value="logination" />
			<c:out value="${login}" />:<br/>
			<input type="text" name="login" value="" /><br/>
			<c:out value="${password}" />:<br/>
			<input type="password" name="password" value="" /><br/>
			<input type="submit" name="sign in" value="${log_button}" />
		</form>

		<!-- REGISTRATION -->
		<br/>
		<hr/>
		<p>
			<c:out value="${registrationText}" />
		</p>
		<hr/>
		<c:if test="${requestScope.isRegistered == true}">
			<span>
				<c:out value="${successRegistration}"/>
			</span>
		</c:if>
		<form action="FrontController" method="post">
			<input type="hidden" name="command" value="registration" />
			<c:out value="${login}" />:<br/>
			<input type="text" name="login" value="" /><br/>
			<c:out value="${password}" />:<br/>
			<input type="password" name="password" value="" /><br/>
			<input type="submit" name="sign up" value="${reg_button}" /><br/>
		</form>

	</div>

	<div id="content">
		<H1>Invitation</H1>
		<p>To start using shop you have to log in. If you visit us for the first time then sign up!</p>

	</div>
</div>

<div class="footer">
	<div id="footer">
		<h1>footer</h1>
		<p>
			<a href="FrontController?command=goToPage&address=index.jsp">INDEX</a>
			 -->
			<a href="FrontController?command=goToPage&address=main.jsp">MAIN</a>
		</p>
	</div>
</div>


</body>

</html>