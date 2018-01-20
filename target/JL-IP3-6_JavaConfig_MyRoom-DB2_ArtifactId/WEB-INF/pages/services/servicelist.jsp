<%--
  Created by IntelliJ IDEA.
  User: maestro
  Date: 01.01.2018
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Services List</title>
</head>
<body>
    <table>
        <tr>
            <td><a href="/userlogin">|Return to home|</a></td>
            <td>|Selected ${selectedCustomer.phoneNumber}|</td>
            <td>|Hello ${sessionScope.loginUser.login}|</td>
            <td><a href="/logout">|Logout|</a></td>
        </tr>
    </table>
    <br>
    <br>
    <table>
        <tr>
            <td>
                <button onclick="location.href='/services/create'">New Service</button>
            </td>
        </tr>
    </table>

    <c:if test="${!empty serviceList}">
        <table>
            <caption>Services List:</caption>
            <tr>
                <th>Service Name</th>
                <th>Service Payroll</th>
                <th>Action</th>
            </tr>
            <c:forEach var="listItem" items="${serviceList}">
                <tr>
                    <td value="${listItem}">|${listItem.name}|</td>
                    <td value="${listItem}">|${listItem.payroll}|</td>
                    <td>
                        <button onclick="location.href='/customer/${listItem.name}'">Query</button>
                        <button onclick="location.href='/customer/${listItem.name}/update'">Update</button>
                        <button onclick="location.href='/customer/${listItem.name}/delete'">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</body>
</html>
