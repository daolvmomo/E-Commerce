<%-- 
    Document   : createAccount
    Created on : Oct 13, 2023, 9:13:57 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account Page</title>
    </head>
    <body>
        <h1>CREATE ACCOUNT</h1>
        <form action="DispatchServlet" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>
            Username*<input type="text" name="txtUsername" value="${param.txtUsername}" /><br/>
            <c:if test="${not empty errors.usernameLengthErr}">
                <font color="red">
                ${errors.usernameLengthErr}
                </font><br/>              
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                ${errors.usernameIsExisted}
                </font><br/>
            </c:if>
            Password*<input type="password" name="txtPassword" value="" /><br/>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red">
                ${errors.passwordLengthErr}
                </font><br/>
            </c:if>
            Confirm*<input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color="red">
                ${errors.confirmNotMatched}
                </font><br/>
            </c:if>
            Full Name*<input type="text" name="txtFullName" value="${param.txtFullName}" /><br/>
            <c:if test="${not empty errors.fullNameLengthErr}">
                <font color="red">
                ${errors.fullNameLengthErr}
                </font><br/>
            </c:if>
            <input type="submit" value="Create New Account" /><br/>
            <input type="reset" value="Reset" /><br/>   
        </form>
    </body>
</html>
