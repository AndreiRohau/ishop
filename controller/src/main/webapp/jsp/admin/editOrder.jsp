<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Edit order</title>

    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="local.shop" var="shop" />
    <fmt:message bundle="${loc}" key="local.hello" var="hello" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.ch" var="ch_button" />

    <c:set var="current_page" value="${requestScope.get('currentPage')}"/>
    <c:set var="max_page" value="${requestScope.get('maxPage')}"/>
    <c:set var="productIDsString" value="${requestScope.get('productIDsString')}"/>
    <c:set var="orderId" value="${requestScope.get('orderId')}"/>

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
        <p>Order ID : ${requestScope.get("orderId")}</p>
        <p>Status ${requestScope.get('status')}:
            <form title="Set status = ${requestScope.get('new_status')}" action="FrontController" method="post">
                <input type="hidden" name="command" value="orderSetStatus" />
                <input type="hidden" name="new_status" value="${requestScope.get('new_status')}" />
                <input type="hidden" name="from" value="editOrder" />
                <input type="hidden" name="orderId" value="${orderId}" />
                <input type="submit" name="status" value="${requestScope.get('new_status')}" /><br/>
            </form>
        </p>
        <p>Delete order :
            <form title="Delete Order" action="FrontController" method="post">
                <input type="hidden" name="command" value="deleteOrder" />
                <input type="hidden" name="from" value="editOrder" />
                <input type="hidden" name="orderId" value="${orderId}" />
                <input type="submit" name="edit" value="DELETE" /><br/>
            </form>
        </p>
        <p>Inspect user :
            <form title="Go to user" action="FrontController" method="post">
                <input type="hidden" name="command" value="editClient" />
                <input type="hidden" name="orderId" value="${orderId}" />
                <input type="submit" name="userId" value="${requestScope.get('userId')}" /><br/>
            </form>
        </p>
        <p>Address : ${requestScope.get("address")}</p>
        <p>Phone : ${requestScope.get("phone")}</p>

    </div>

    <div id="content">
        <br/>
        <p><b><c:out value="${requestScope.get('msg')}"/></b></p>
        <hr/>
        <br/>
        <c:if test="${current_page != null}">
            <table width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bgcolor="#ffebcd">
                <tr>
                    <td>ID</td>
                    <td>COMPANY</td>
                    <td>NAME</td>
                    <td>TYPE</td>
                    <td>PRICE</td>
                    <c:if test="${!requestScope.lastCMD.matches('inspectOrder')}">
                        <td>Link</td>
                    </c:if>
                </tr>
                <c:set value="1" var="indexRemovingProduct"/>
                <c:forEach items="${requestScope.productArray}" var="product">
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.company}</td>
                        <td>${product.name}</td>
                        <td>${product.type}</td>
                        <td>${product.price}</td>
                        <c:if test="${!requestScope.lastCMD.matches('inspectOrder')}">
                            <td>
                                <form action="FrontController" method="post">
                                    <input type="hidden" name="command" value="deleteFromOrder" />
                                    <input type="hidden" name="product_id" value="${product.id}" />
                                    <input type="hidden" name="indexRemovingProduct" value="${indexRemovingProduct}" />
                                    <input type="hidden" name="currentPage" value="${current_page}" />
                                    <input type="hidden" name="productIDsString" value="<c:out value="${productIDsString}"/> " />
                                    <input type="hidden" name="orderId" value="${product.order_id}" />
                                    <input type="submit" name="remove" value="REMOVE" /><br/>
                                </form>
                                <c:set value="${indexRemovingProduct + 1}" var="indexRemovingProduct"/>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>

            <div width="100%" style="background-color: deepskyblue; font-size: 1em">    
                <c:forEach begin="1" end="${max_page}" var="i">
                    <c:if test="${i != current_page}">
                        <a href="${requestScope.get('lastCMDneedPage')}${i}">${i}</a>
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
                 <a href="${requestScope.get('lastCMDneedPage')}${current_page}">Page: ${current_page}</a>
            </c:if>
        </p>
    </div>
</div>
</body>
</html>