<%-- 
    Document   : search
    Created on : Sep 29, 2023, 8:58:10 AM
    Author     : admin
--%>

<%--<%@page import="daolv.registration.RegistrationDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color ="red" > 
        Welcome, ${sessionScope.USER_INFO.fullName}
        </font>   <br/> 
        <h1>Search Page</h1>
        <form action="DispatchServlet">
            Search Value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}" /><br/> 
            <input type="submit" value="Search" name="btAction" />
        </form><br/>
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full Name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${result}" var="dto" varStatus="counter">
                        <form action="DispatchServlet" method="POST">
                            <tr>
                                <td>
                                    ${counter.count}.
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" 
                                           value="${dto.username}" />     

                                </td>
                                <td>
                                    <input type="text" name="txtPassword" 
                                           value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.fullName}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="urlRewriting" value="DispatchServlet">
                                        <c:param name="btAction" value="Delete"/>
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td>
                                    <input type="hidden" value="${searchValue}" 
                                           name="lastSearchValue" />
                                    <input type="submit" value="Update" name="btAction" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>    
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2>
                no record is matched!!!
            </h2>
        </c:if>
    </c:if>
</body>
</html>
<%--
<font color ="red" > 
Welcome, ${wellcomeUsername}
</font>   <br/>   
<<form action="DispatchServlet">
    <input type="submit" value="Logout" name="btAction" />
</form>
<h1>Search Page</h1>
<form action="DispatchServlet">
    Search Value <input type="text" name="txtSearchValue" 
                        value="<%= (request.getParameter("txtSearchValue") != null) ? request.getParameter("txtSearchValue") : ""%>" /><br/> 
    <input type="submit" value="Search" name="btAction" />
</form><br/>
<%
    String searchValue = request.getParameter("txtSearchValue");
    if (searchValue != null) {
        List<RegistrationDTO> result
                = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
        //render
        if (result != null) {//have 1 or more record// in ra table = html
%>
<table border="1">
    <thead>
        <tr>
            <!-- ctrl + shift + mui t?n chi len -->
            <th>No.</th>
            <th>Username</th>
            <th>Password</th>
            <th>Full name</th>
            <th>Role</th>
            <th>Delete</th>
            <th>Update</th>
        </tr>
    </thead>
    <tbody><!-- Criplet element -->
        <%
            int count = 0;
            for (RegistrationDTO dto : result) {
                String urlRewriting = "DispatchServlet"
                        + "?btAction=Delete"
                        + "&pk=" + dto.getUsername()
                        + "&lastSearchValue=" + searchValue;
        %>
    <form action="DispatchServlet" method="POST">
        <tr>
            <!-- ctrl + shift + mui t?n chi len -->
            <td>
                <%= ++count%>
                .</td>
            <td>
                <%= dto.getUsername()%>
                <input type="hidden" name="txtUsername" 
                       value="<%= dto.getUsername()%>" />
            </td>
            <td>
                <input type="text" name="txtPassword" 
                       value="<%= dto.getPassword()%>" />
            </td>
            <td>
                <%= dto.getFullName()%>
            </td>
            <td>
                <input type="checkbox" name="chkAdmin" value="ON" 
                       <%
                           if (dto.isRole()) {
                       %>
                       checked="checked"
                       <%
                           }//user is admin
                       %>
                       />
            </td>
            <td>
                <a href="<%= urlRewriting%>">Delete</a>
            </td>
            <<td>
                <input type="hidden" name="lastSearchValue" value="<%= searchValue%>" />
                <input type="submit" value="Update" name="btAction" />
            </td>
        </tr>
    </form>

            <%
                }//end traverse DTO
            %>
        </tbody>
    </table>
    <%
    } else {// no record
    %>
    <<h2>
        no record is matched !!!
    </h2>                   
    <%
            }

        }//searchValue did not trigger from previous form 
    %> --%>

