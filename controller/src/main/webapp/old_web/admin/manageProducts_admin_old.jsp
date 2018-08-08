<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>ManageShop</title>

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
                <!-- Add NEW product - STARTS-->
                <form action="FrontController" method="post">
                    <span><c:out value="${requestScope.isAdded}"/></span>
                    <input type="hidden" name="command" value="addNewProduct"/>
                    <p><b>Set product company: </b><br/>
                    <input type="text" name="company" value=""/></p><br/>
                    <p><b>Set product name: </b><br/>
                    <input type="text" name="name" value=""/></p><br/>
                    <p><b>Set product type: </b><br/>
                    <input type="text" name="type" value=""/></p><br/>
                    <p><b>Set product price: </b><br/>
                    <input type="text" name="price" value=""/></p><br/>
                    <p><b>Set description: </b><br/>
                    <textarea rows="8" cols="45" name="description"></textarea></p><br/>
                    <p><input type="submit" name="Save_new_product" value="ADD"/></p>
                </form>
                <!-- Add NEW product - ENDs-->
            </div>

            <div id="content">
                <form action="FrontController" method="post">
                    <p><b>Fill</b>
                        <input type="hidden" name="command" value="findSuitable"/>
                        <input type="hidden" name="page_num" value="1"/>
                        <input title="company" type="text" name="company" value="" />
                        <input title="name" type="text" name="name" value="" />
                        <input title="type" type="text" name="type" value="" />
                        <input title="price" type="text" name="price" value="" />
                        <input type="submit" name="get_products" value="Find it!"/>
                    </p>
                </form>
                <br/>
                <hr/>
                <form action="FrontController" method="post">
                    <p><b>Get all products</b>
                        <input type="hidden" name="command" value="selectAllProducts"/>
                        <input type="hidden" name="page_num" value="1"/>
                        <input type="submit" name="get_products" value="Get them!"/>
                    </p>
                </form>
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
                            <td>Link</td>
                        </tr>
                        <c:forEach items="${requestScope.productArray}" var="product">
                            <tr>
                                <td>${product.id}</td>
                                <td>${product.company}</td>
                                <td>${product.name}</td>
                                <td>${product.type}</td>
                                <td>${product.price}</td>
                                <td>
                                    <form action="FrontController" method="post">
                                        <input type="hidden" name="command" value="editProduct" />
                                        <input type="hidden" name="productId" value="${product.id}" />
                                        <input type="submit" name="edit" value="Edit" /><br/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                    <div width="100%" style="background-color: deepskyblue; font-size: 1em">    
                        <c:forEach begin="1" end="${max_page}" var="i">
                            <c:if test="${i != current_page}">
                                <a href="FrontController?command=selectAllProducts&page_num=${i}">${i}</a>
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
                    <a href="FrontController?command=goToPage&address=manageProducts.jsp">GOODS</a>

                    <c:if test="${current_page != null}">
                        -->
                         <a href="FrontController?command=selectAllProducts&page_num=${current_page}">Page: ${current_page}</a>
                    </c:if>
                </p>
            </div>
        </div>
    </body>
</html>