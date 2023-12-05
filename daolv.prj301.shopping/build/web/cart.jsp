<%-- 
    Document   : cart
    Created on : Oct 11, 2023, 4:07:57 PM
    Author     : admin
--%>

<%@page import="java.util.Map"%>
<%--<%@page import="daolv.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>BOOK STORE</h1>
        <c:if test="${not empty session}" >
            <c:if test="${not empty session.CART}" >
                <c:set var="cart" value="${session.CART}"/>
                <c:if test="${not empty cart}" >
                    
                </c:if>
            </c:if>
        </c:if>
        <%--<%
            //1. cust goes to cart place
            if (session != null) {
                //cust takes cart for cart place
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. cust get items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
        %>--%>
        <form action="DispatchServlet">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (String key : items.keySet()) {
                    %>
                    <tr>
                        <td>
                            <%= ++count%>
                        </td>
                        <td>
                            <%= key%>
                        </td>
                        <td>
                            <%= items.get(key)%>
                        </td>
                        <td>
                            <input type="checkbox" name="chkItem" 
                                   value="<%= key%>" />
                        </td>
                    </tr>
                    <%
                        }//end get key value of each item
                    %>       
                    <tr>
                        <td colspan="3">
                            <a href="bookStore.html">Add more book to cart</a>
                        </td>
                        <td colspan="1">
                            <input type="submit" value="Remove Selected Items" name="btAction" />
                        </td>

                    </tr>
                </tbody>
            </table>
        </form>
        <%
            }
        
        return;
                }//end cart has existed
            }//cart place must be existed
        %>
        <h2>No item in cart </h2>
        <%
        %>
    </body>
</html>
