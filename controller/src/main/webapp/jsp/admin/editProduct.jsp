<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Edit Product</title>

        <fmt:setLocale value="${sessionScope.local}" />
        <fmt:setBundle basename="localization.local" var="loc" />
        <fmt:message bundle="${loc}" key="local.shop" var="shop" />
        <fmt:message bundle="${loc}" key="local.hello" var="hello" />
        <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
        <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
        <fmt:message bundle="${loc}" key="local.locbutton.name.ch" var="ch_button" />
        <fmt:message bundle="${loc}" key="local.deleteAccountButton" var="deleteAccountButton" />
        <c:set var="rProductId" value="${requestScope.get('productToEdit').id}"/>
        <c:set var="product_name" value="${requestScope.get('productToEdit').name}"/>
        <c:set var="product_company" value="${requestScope.get('productToEdit').company}"/>
        <c:set var="product_type" value="${requestScope.get('productToEdit').type}"/>
        <c:set var="product_price" value="${requestScope.get('productToEdit').price}"/>
        <c:set var="product_description" value="${requestScope.get('productToEdit').description}"/>

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
                    <p>Get all products</p>
                    <input type="hidden" name="command" value="selectAllProducts"/>
                    <input type="hidden" name="page_num" value="1"/>
                    <input type="submit" name="get_products" value="Get them!"/>
                </form>
                <br/>
                <hr/>
                <br/>
            </div>

            <div id="content">

                <table width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bgcolor="#ffebcd">
                    <tr>
                        <td>ID</td>
                        <td>COMPANY</td>
                        <td>NAME</td>
                        <td>TYPE</td>
                        <td>PRICE</td>
                        <td>Link</td>
                    </tr>
                    <tr>
                        <td>${rProductId}</td>
                        <td>${product_company}</td>
                        <td>${product_name}</td>
                        <td>${product_type}</td>
                        <td>${product_price}</td>
                        <td>
                            <form action="FrontController" method="post">
                                <input type="hidden" name="command" value="delete_product"/><br/>
                                <input type="hidden" name="id" value="${rProductId}"/><br/>
                                <input type="submit" name="delete" value="${deleteAccountButton}"/>
                            </form>
                        </td>
                    </tr>
                </table>
                <br/>
                <hr/>

                <c:out value="${requestScope.get('updateFailed')}"/>
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" value="updateProduct" />
                    <input type="hidden" name="id" value="${rProductId}" />

                    <table width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bgcolor="#ffebcd">
                        <tr>
                            <td>Company: <input type="text" name="company" value="${product_company}" title=""/></td>
                            <td>Name: <input type="text" name="name" value="${product_name}" title=""/></td>
                            <td>Type: <input type="text" name="type" value="${product_type}" title=""/></td>
                            <td>Price: <input type="text" name="price" value="${product_price}" title=""/></td>
                        </tr>
                        <br/>
                        <tr>
                            <td colspan="4"><br/>Set description:<br/>
                                <textarea rows="10" cols="45" name="description">${product_description}</textarea>
                            </td>
                        </tr>
                    </table>
                    <br/>
                    <input type="submit" name="edit" value="Edit" />
                </form>

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
                    -->
                    <a href="FrontController?command=editProduct&productId=${rProductId}" style="text-transform: uppercase">${product_name}</a>
                </p>
            </div>
        </div>
    </body>
</html>