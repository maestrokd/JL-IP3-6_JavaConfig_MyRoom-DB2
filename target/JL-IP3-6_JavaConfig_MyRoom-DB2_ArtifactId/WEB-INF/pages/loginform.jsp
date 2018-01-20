<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: maestro
  Date: 20.12.2017
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Login</title>
    <style>
        .error{
            color:#ff0000;
        }
    </style>
</head>
<body>
    <table>
        <tr>
            <td><a href="/">|Return to home|</a></td>
            <td><a href="registration">|Registration|</a></td>
        </tr>
    </table>
    <br>
    <br>
    <sf:form action="/userlogin" modelAttribute="loginUser" >
        <%--<form:input path="name" placeholder="YourName"/>--%>
        <%--<form:input path ="name"/>--%>
        <table>
            <tr>
                <td>
                    <label for="login">
                        <s:message code="property.enterYourLogin"/>
                    </label>
                </td>
                <td>
                    <sf:input path ="login" placeholder="YourLogin" pattern="[A-Za-z0-9]{4,}" />
                    <sf:errors path="login" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="password">
                        <s:message code="property.enterYourPassword"/>
                    </label>
                </td>
                <td>
                    <sf:password path ="password" placeholder="YourPassword" pattern="[A-Za-z0-9]{4,}" />
                    <sf:errors path="password" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" name="Send"/>
                </td>
            </tr>
        </table>
    </sf:form>
    <%--<h2>Hello Login!</h2>--%>
</body>
</html>
