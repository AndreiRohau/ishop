<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="local.errorOccupied" var="errorOccupied" />
    <fmt:message bundle="${loc}" key="local.toIndexPage" var="toIndexPage" />
    <fmt:message bundle="${loc}" key="local.injectionDetected" var="injectionDetected" />
    <fmt:message bundle="${loc}" key="local.antiInjection" var="antiInjection" />
    <fmt:message bundle="${loc}" key="local.toPreviousPage" var="toPreviousPage" />
    <title>${antiInjection}</title>
</head>
<body style="">

<hr/>
<h1>
    ${errorOccupied} ...
</h1>
<hr/>
<h1>
    ${injectionDetected}
</h1>

<hr/>

<h3>
    <a href="FrontController?command=goToPage&address=index.jsp">
        ${toIndexPage}
    </a>
</h3>
<h3>
    <c:if test="${sessionScope.lastCommand != null}">
        <a href="${sessionScope.lastCommand}">
            ${toPreviousPage}
        </a>
    </c:if>
</h3>
</body>
</html>
