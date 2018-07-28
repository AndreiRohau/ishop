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
        <title>404</title>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <fmt:setLocale value="${sessionScope.local}" />
        <fmt:setBundle basename="localization.local" var="loc" />
        <fmt:message bundle="${loc}" key="local.errorOccupied" var="errorOccupied" />
        <fmt:message bundle="${loc}" key="local.toIndexPage" var="toIndexPage" />
        <fmt:message bundle="${loc}" key="local.toPreviousPage" var="toPreviousPage" />
    </head>
    <body style="">

        <hr/>
        <h1>
            <c:out value="${errorOccupied}"></c:out> 404
        </h1>

        <hr/>
        <%--<h3>--%>
            <%--<a href="<%=lastCMD%>">--%>
                <%--<c:out value="${toPreviousPage}"></c:out>--%>
            <%--</a>--%>
        <%--</h3>--%>
        <h3>
            <a href="FrontController?command=goToPage&address=index.jsp">
                <c:out value="${toIndexPage}"></c:out>
            </a>
        </h3>

    </body>
</html>
