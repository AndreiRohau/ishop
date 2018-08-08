<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Manage Orders</title>

        <fmt:setLocale value="${sessionScope.local}" />
        <fmt:setBundle basename="localization.local" var="loc" />
        <fmt:message bundle="${loc}" key="local.shop" var="shop" />
        <fmt:message bundle="${loc}" key="local.hello" var="hello" />
        <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
        <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
        <fmt:message bundle="${loc}" key="local.locbutton.name.ch" var="ch_button" />

        <c:set var="current_page" value="${requestScope.get('currentPage')}"/>
        <c:set var="max_page" value="${requestScope.get('maxPage')}"/>

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
                    <%--@returns new ORDERS list: userId and orderId and adds command SET STATUS-ACTIVE and cmd EDIT
                            where you can see detailed list of products and total sum of payment
                            and delete order--%>
                    <p><b>Get NEW orders</b>
                        <input type="hidden" name="command" value="selectAllNewOrders"/>
                        <input type="hidden" name="page_num" value="1"/>
                        <input type="submit" name="get_users" value="Show New Orders!"/>
                    </p>
                </form>
                <br/>
                <br/>
                <form action="FrontController" method="post">
                    <%--returns Active orders list: userId and orderId, look detailes but not to edite but delete order,
                            AND set status success--%>
                    <p><b>Get ACTIVE orders</b>
                        <input type="hidden" name="command" value="selectAllActiveOrders"/>
                        <input type="hidden" name="page_num" value="1"/>
                        <input type="submit" name="get_users" value="Show Active Orders!"/>

                    </p>
                </form>
                <br/>
                <br/>
                <form action="FrontController" method="post">
                    <%--returns successed orders: userId + orderId + link for detailed list of sold proDs + benifit--%>
                    <p><b>Get SUCCESSFUL orders</b>
                        <input type="hidden" name="command" value="selectAllSuccessOrders"/>
                        <input type="hidden" name="page_num" value="1"/>
                        <input type="submit" name="get_users" value="Show Successed Orders!"/>
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
                            <td>INFO</td>
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
                                        <input type="hidden" name="command" value="${requestScope.get('command_2')}" />
                                        <input type="hidden" name="new_status" value="${requestScope.get('command_3')}" />
                                        <input type="hidden" name="from" value="manageOrders" />
                                        <input type="hidden" name="page_num" value="1"/>
                                        <input type="hidden" name="userId" value="${element.userId}" />
                                        <input type="submit" name="orderId" value="${element.id}" /><br/>
                                    </form>
                                </td>
                                    <%--set-active [FrontController?command=orderSetStatus&new_status=orderSetASSHOLE&from=manageOrders&orderId=108&status=new]--%>
                                <td>
                                    <form title="Set status = ${requestScope.get('command_3')}" action="FrontController" method="post">
                                        <input type="hidden" name="command" value="orderSetStatus" />
                                        <input type="hidden" name="new_status" value="${requestScope.get('command_3')}" />
                                        <input type="hidden" name="from" value="manageOrders" />
                                        <input type="hidden" name="orderId" value="${element.id}" />
                                        <input type="submit" name="status" value="${element.status}" /><br/>
                                    </form>
                                </td>
                                    <%--delete--%>
                                <td>
                                    <c:if test="${requestScope.get('command_4') != 'archiveOrder'}">
                                        <form title="${requestScope.get('command_4')}" action="FrontController" method="post">
                                            <input type="hidden" name="command" value="${requestScope.get('command_4')}" />
                                            <input type="hidden" name="from" value="manageOrders" />
                                            <input type="hidden" name="orderId" value="${element.id}" />
                                            <input type="submit" name="edit" value="DELETE" /><br/>
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                    <div width="100%" style="background-color: deepskyblue; font-size: 1em">    
                        <c:forEach begin="1" end="${max_page}" var="i">
                            <c:if test="${i != current_page}">
                                <a href="FrontController?command=${requestScope.get('command')}&page_num=${i}">${i}</a>
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
                    <a href="FrontController?command=goToPage&address=main.jsp">ADMINISTRATION</a>
                    -->
                    <a href="FrontController?command=goToPage&address=manageOrders.jsp">ORDERS</a>
                    <c:if test="${current_page != null}">
                        -->
                         <a href="FrontController?command=${requestScope.get('command')}&page_num=${current_page}">Page: ${current_page}</a>
                    </c:if>
                </p>
            </div>
        </div>
    </body>
</html>