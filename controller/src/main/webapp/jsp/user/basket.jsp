<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Basket</title>

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
                <p>Basket: <c:out value="${sessionScope.userName}"/> !!!</p>
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
                <p><b>Get ALL orders</b>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="showAllMyOrders"/>
                        <input type="hidden" name="page_num" value="1"/>
                        <input type="submit" name="get_orders" value="Show ALL Orders!"/>
                    </form>
                </p>
                <br/>
                <hr/>
                <p><b>Basket</b></p>
                <hr/>
                <form action="FrontController" method="post">
                    <p><b>Get all RESERVED products (or Reload)</b>
                        <input type="hidden" name="command" value="selectAllReserved"/>
                        <input type="hidden" name="page_num" value="1"/>
                        <input type="submit" name="get_reserved" value="Get them!"/>
                    </p>
                </form>
                <br/>
                <hr/>
                <br/>
                <form action="FrontController" method="post">
                    <p><b>Buy all products in basket.</b></p>
                    <input type="hidden" name="command" value="createOrder"/><br/>
                    <p>Fill with the address:</p>
                    <input type="text" name="user_address" value="" required/><br/><br/>
                    <p>Fill with your contact phone number:</p>
                    <input type="text" name="user_phone" value="" required/><br/><br/>
                    <input type="submit" name="buy" value="Buy now!"/>
                </form>
            </div>

            <div id="content">
                <br/>
                <hr/>

                <p><b><c:out value="${requestScope.get('msg')}"/></b></p>
                <c:if test="${current_page != null}">
                    <H1>Everything you have choosen for purchasing.</H1>
                    <hr/>
                    <table width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bgcolor="#ffebcd">
                        <tr>
                            <td>INFO</td>
                            <td>COMPANY</td>
                            <td>NAME</td>
                            <td>TYPE</td>
                            <td>PRICE</td>
                            <td>DELETE</td>
                        </tr>
                        <c:forEach items="${requestScope.productArray}" var="product">
                            <tr>
                                <td>
                                    <form action="FrontController" method="post">
                                        <input type="hidden" name="command" value="productInfo" />
                                        <input type="hidden" name="productId" value="${product.id}" />
                                        <input type="submit" name="info" value="INFO" /><br/>
                                    </form>
                                </td>
                                <td>${product.company}</td>
                                <td>${product.name}</td>
                                <td>${product.type}</td>
                                <td>${product.price}</td>
                                <td>
                                    <form action="FrontController" method="post">
                                        <input type="hidden" name="command" value="deleteReserved" />
                                        <input type="hidden" name="reserveId" value="${product.reserve_id}" />
                                        <input type="submit" name="remove" value="REMOVE" /><br/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                    <div width="100%" style="background-color: deepskyblue; font-size: 1em">    
                        <c:forEach begin="1" end="${max_page}" var="i">
                            <c:if test="${i != current_page}">
                        		<a href="FrontController?command=selectAllReserved&page_num=${i}">${i}</a>
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
                <p style="text-decoration-color: yellow">
                    <a href="FrontController?command=goToPage&address=index.jsp">INDEX</a>
                      -->
                    <a href="FrontController?command=goToPage&address=main.jsp">MAIN</a>
                      -->
                    <a href="FrontController?command=goToPage&address=basket.jsp">BASKET</a>
                </p>
            </div>
        </div>
    </body>
</html>