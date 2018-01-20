<%--
  Created by IntelliJ IDEA.
  User: maestro
  Date: 22.12.2017
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Find Customer</title>
</head>
<body>
    <table>
        <tr>
            <td><a href="/userlogin">|Return to home|</a></td>
            <td>|Hello ${sessionScope.loginUser.login}|</td>
            <td><a href="/logout">|Logout|</a></td>
        </tr>
    </table>
    <br>
    <br>
    <form action="/findcustomer" method="get">
        <table>
            <tr>
                <td><input type="text" name="findCustomerPhoneNumber" value="value"></td>
                <td><input type="submit" value="Find Customer"></td>
            </tr>
        </table>
    </form>

</body>
</html>
