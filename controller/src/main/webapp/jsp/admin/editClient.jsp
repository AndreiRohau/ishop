<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Edit Client</title>
    
        <fmt:setLocale value="${sessionScope.local}" />
        <fmt:setBundle basename="localization.local" var="loc" />
        <fmt:message bundle="${loc}" key="local.shop" var="shop" />
        <fmt:message bundle="${loc}" key="local.hello" var="hello" />
        <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
        <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
        <fmt:message bundle="${loc}" key="local.locbutton.name.ch" var="ch_button" />
        <fmt:message bundle="${loc}" key="local.deleteAccountButton" var="deleteAccountButton" />
        <fmt:message bundle="${loc}" key="local.login" var="login" />
        <fmt:message bundle="${loc}" key="local.password" var="password" />

        <c:set var="userId" value="${requestScope.get('userToEdit').id}"/>
        <c:set var="user_login" value="${requestScope.get('userToEdit').login}"/>
        <c:set var="user_password" value="${requestScope.get('userToEdit').password}"/>
        
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
                    <p>Get all users</p>
                    <input type="hidden" name="command" value="selectAllUsers"/>
                    <input type="hidden" name="page_num" value="1"/>
                    <input type="submit" name="get_users" value="Get them!"/>
                </form>
                <br/>
                <br/>
                <hr/>
                <br/>
                <form action="FrontController" method="post">
                    <p><b>Get ALL orders</b>
                        <input type="hidden" name="command" value="showAllClientsOrders"/>
                        <input type="hidden" name="page_num" value="1"/>
                        <input type="hidden" name="userId" value="${userId}"/><br/>
                        <input type="submit" name="get_orders" value="Show ALL Orders!"/>
                    </p>
                </form>
            </div>
        
            <div id="content">

                <table width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bgcolor="#ffebcd" >
                    <tr>
                        <td>ID</td>
                        <td>LOGIN</td>
                        <td>PASSWORD</td>
                        <td>LINK</td>
                    </tr>
                    <tr>
                        <td>${userId}</td>
                        <td>${user_login}</td>
                        <td>${user_password}</td>
                        <td>
                            <form action="FrontController" method="post">
                                <input type="hidden" name="command" value="delete_user"/><br/>
                                <input type="hidden" name="login" value="${user_login}"/><br/>
                                <input type="hidden" name="password" value="${user_password}"/><br/>
                                <input type="submit" name="delete" value="${deleteAccountButton}"/>
                            </form>
                        </td>
                    </tr>
                </table>
                <br/>
                <hr/>
                <br/>

                <c:out value="${requestScope.get('updateFailed')}"/>
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" value="updateClient" />
                    <input type="hidden" name="id" value="${userId}" />

                    <table width="100%" border="1" align="center" cellpadding="4" cellspacing="0" bgcolor="#ffebcd">
                        <tr>
                            <td>
                                <c:out value="${login}" />:
                                <input type="text" name="login" value="${user_login}" title=""/>
                            </td>
                            <td>
                                <c:out value="${password}" />:
                                <input type="text" name="password" value="${user_password}" title=""/>
                            </td>
                        </tr>
                    </table>
                    <br/>
                    <input type="submit" name="edit" value="Edit" />
                </form>

                <br/>
                <hr/>
                <br/>

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
                    <a href="FrontController?command=goToPage&address=manageClients.jsp">CLIENTS</a>
                    -->
                    <a href="FrontController?command=editClient&userId=${userId}" style="text-transform: uppercase">${user_login}</a>
                </p>
            </div>
        </div>
    </body>
</html>