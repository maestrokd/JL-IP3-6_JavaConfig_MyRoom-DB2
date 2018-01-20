<%--
  Created by IntelliJ IDEA.
  User: maestro
  Date: 22.12.2017
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Find Customer successful</title>
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
    <table>
        <caption>Found Customers:</caption>
        <tr>
            <th>Phone number</th>
            <th>Status</th>
            <th>Balance</th>
            <th>Action</th>
        </tr>
        <c:forEach var="listItem" items="${findCustomerList}">
            <tr>
                <%--<sf:form action="/customer" method="get" modelAttribute="selectedCustomer"  >--%>
                <td value="${listItem}">|${listItem.phoneNumber}|</td>
                <td value="${listItem}">|${listItem.customerStatus.name}|</td>
                <td value="${listItem}">|${listItem.balance}|</td>
                <td>
                    <button onclick="location.href='/customer/${listItem.phoneNumber}'">Query</button>
                    <button onclick="location.href='/customer/${listItem.phoneNumber}/update'">Update</button>
                    <button onclick="location.href='/customer/${listItem.phoneNumber}/delete'">Delete</button>
                </td>
                <%--</sf:form>--%>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
