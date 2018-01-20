<%--
  Created by IntelliJ IDEA.
  User: maestro
  Date: 20.12.2017
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Confirmation</title>
</head>
<body>
    <sf:form action="/confirmation" method="GET">
    <table>
        <tr>
            <td><a href="/">|Return to home|</a></td>
            <td><a href="login">|Login|</a></td>
        </tr>
    </table>
    <br>
    <br>
    <table>
        <caption>Confirmation</caption>
        <tr>
            <td>login:</td>
            <td>${sessionScope.registeredUser.login}</td>
        </tr>
        <tr>
            <td>password:</td>
            <td>${sessionScope.registeredUser.password}</td>
        </tr>
        <tr>
            <td>
                <button name="confirmation" type="submit"> Confirmation </button>
            </td>
        </tr>
    </table>
    </sf:form>

    <%--<h2>Hello Confirmation!</h2>--%>
</body>
</html>
