<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
 String lastCMD = (String) session.getAttribute("lastCMD");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error page</title>
 <fmt:setLocale value="${sessionScope.local}" />
 <fmt:setBundle basename="localization.local" var="loc" />
 <fmt:message bundle="${loc}" key="local.loginExists" var="loginExists" />
 <fmt:message bundle="${loc}" key="local.logout" var="logout" />
 <fmt:message bundle="${loc}" key="local.noSuchUser" var="noSuchUser" />
 <fmt:message bundle="${loc}" key="local.errorOccupied" var="errorOccupied" />
 <fmt:message bundle="${loc}" key="local.toIndexPage" var="toIndexPage" />
 <fmt:message bundle="${loc}" key="local.toPreviousPage" var="toPreviousPage" />

</head>

<body style="background-color: burlywood">

<hr/>
<h1><c:out value="${errorOccupied}"></c:out></h1><hr/>

 <%--<h2><%=(String) request.getAttribute("errorMessage")%></h2>--%>
<c:if test="${requestScope.errorMessage == 'exists'}">
 <span>
  <h2><c:out value="${loginExists}"/></h2>
 </span>
</c:if>

<c:if test="${requestScope.errorMessage == 'logout'}">
 <span>
  <h2><c:out value="${logout}"/></h2>
 </span>
</c:if>

<c:if test="${requestScope.errorMessage == 'noSuchUser'}">
 <span>
  <h2><c:out value="${noSuchUser}"/></h2>
 </span>
</c:if>



<hr/>
 <h3><a href="<%=lastCMD%>"><c:out value="${toPreviousPage}"></c:out></a></h3>
 <h3><a href="FrontController?command=goToPage&address=index.jsp"><c:out value="${toIndexPage}"></c:out></a></h3>
</body>
</html>