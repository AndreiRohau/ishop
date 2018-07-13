<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>All Clients Orders</title>

        <fmt:setLocale value="${sessionScope.local}" />
        <fmt:setBundle basename="localization.local" var="loc" />
        <fmt:message bundle="${loc}" key="local.shop" var="shop" />
        <fmt:message bundle="${loc}" key="local.hello" var="hello" />
        <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
        <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
        <fmt:message bundle="${loc}" key="local.locbutton.name.ch" var="ch_button" />

        <c:set var="current_page" value="${requestScope.get('currentPage')}"/>
        <c:set var="max_page" value="${requestScope.get('maxPage')}"/>
        <c:set var="userId" value="${requestScope.get('userId')}"/>

    </head>
    <body>
        <div class="header">
            <div id="header1">
                <p><c:out value="${shop}"/></p>
                <p><c:out value="${hello}"/> <c:out value="${sessionScope.userName}"/> !!!</p>
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
                <form action="FrontController" method="post">
                    <p><b>Get ALL orders</b>
                        <input type="hidden" name="command" value="showAllClientsOrders"/>
                        <input type="hidden" name="page_num" value="1"/>
                        <input type="hidden" name="userId" value="${userId}"/><br/>
                        <input type="submit" name="get_orders" value="Show ALL Orders!"/>
                    </p>
                </form>
                <br/>
                <br/>
                <hr/>
                <br/>
                <p><b><c:out value="${requestScope.get('msg')}"/></b></p>

            </div>

            <div id="content">

                <c:if test="${current_page != null}">
                    <table width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bgcolor="#ffebcd">
                        <tr>
                            <td>USER</td>
                            <td>ORDER</td>
                            <td>STATUS</td>
                        </tr>
                        <c:forEach items="${requestScope.array}" var="element">
                            <tr>
                                    <%--new: Client ID - edit client --%>
                                <td>
                                    <form title="Go to user" action="FrontController" method="post">
                                        <input type="hidden" name="command" value="editClient" />
                                        <input type="hidden" name="orderId" value="${element.id}" />
                                        <input type="submit" name="userId" value="${element.userId}" /><br/>
                                    </form>
                                </td>
                                    <%--order id - open order--%>
                                <td>
                                    <form title="Observe the order" action="FrontController" method="post">
                                        <input type="hidden" name="command" value="inspectOrder" />
                                        <input type="hidden" name="from" value="allClientsOrders" />
                                        <input type="hidden" name="page_num" value="1"/>
                                        <input type="hidden" name="orderId" value="${element.id}" />
                                        <input type="submit" name="button_ok" value="${element.id}" /><br/>
                                    </form>
                                </td>
                                    <%--set-active--%>
                                <td>
                                    <p>
                                        ${element.status}
                                    </p>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>


                    <div width="100%" style="background-color: deepskyblue; font-size: 1em">    
                        <c:forEach begin="1" end="${max_page}" var="i">
                            <c:if test="${i != current_page}">
                                <a href="FrontController?command=showAllClientsOrders&userId=${userId}&page_num=${i}">${i}</a>
                            </c:if>
                            <c:if test="${i == current_page}">
                                <c:out value="${i}"/>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </div>

        <div class="footer" >
            <div id="footer" >
                <h1>footer</h1>
                <p>
                    <a href="FrontController?command=goToPage&address=index.jsp">INDEX</a>
                    -->
                    <c:if test="${requestScope.get('for_user') != 'for_user'}">
                        <a href="FrontController?command=goToPage&address=main.jsp">ADMINISTRATION</a>
                        -->
                        <a href="FrontController?command=goToPage&address=manageOrders.jsp">ORDERS</a>
                    </c:if>
                    <c:if test="${requestScope.get('for_user') == 'for_user'}">
                        <a href="FrontController?command=goToPage&address=main.jsp">MAIN</a>
                        -->
                        <a href="FrontController?command=goToPage&address=basket.jsp">BASKET</a>
                    </c:if>
                    -->
                    <a href="FrontController?command=showAllClientsOrders&userId=${userId}&page_num=${current_page}">Page: ${current_page}</a>
                </p>
            </div>
        </div>
    </body>
</html>