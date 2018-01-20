<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: maestro
  Date: 21.12.2017
  Time: 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Enter successful</title>
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
        <%--<caption>Create new Customer</caption>--%>
        <tr>
            <td>
                <sf:form action="/customers/add" method="get" >
                    <input type="submit" value="New Customer" />
                 </sf:form>
            </td>
        </tr>
        <tr>
            <td>
                <button onclick="location.href='/customers/find'">Find Customer</button>
            </td>
        </tr>
        <br>
        <tr>
            <td>
                <button onclick="location.href='/services/create'">New Service</button>
            </td>
        </tr>
        <br>
        <tr>
            <td>
                <button onclick="location.href='/services/list'">Services List</button>
            </td>
        </tr>
    </table>
    <%--<c:if test="${!empty customerList}">--%>
    <c:if test="${!empty customerList}">
        <table>
            <caption>Numbers for ${loginUser.login}:</caption>
            <tr>
                <th>Phone number</th>
                <th>Customer Status</th>
                <th>Balance</th>
                <th>Action</th>
            </tr>
                <%--<c:forEach var="listItem" items="${customerList}">--%>
                <%--<c:forEach var="listItem" items="${sessionScope.loginUser.customerList}">--%>
                <c:forEach var="listItem" items="${customerList}">
            <tr>
                <td value="${listItem}">|${listItem.phoneNumber}|</td>
                <td value="${listItem}">|${listItem.customerStatus.name}|</td>
                <td value="${listItem}">|${listItem.balance}|</td>
                <td>
                    <button onclick="location.href='/customer/${listItem.phoneNumber}'">Query</button>
                    <button onclick="location.href='/customer/${listItem.phoneNumber}/update'">Update</button>
                    <button onclick="location.href='/customer/${listItem.phoneNumber}/delete'">Delete</button>
                </td>
            </tr>
                </c:forEach>
        </table>
        <%--<sf:form action="/customer" method="get" modelAttribute="selectedCustomer"  >--%>
            <%--<table>--%>
                <%--<tr>--%>
                    <%--<td>Select phone number</td>--%>
                    <%--&lt;%&ndash;<td><sf:select path="phoneNumber">&ndash;%&gt;--%>
                    <%--<td><sf:select path="phoneNumber">--%>
                        <%--<c:forEach var="listItem" items="${customerList}">--%>
                            <%--&lt;%&ndash;<sf:options items="${customerList}" />&ndash;%&gt;--%>
                            <%--<sf:option value="${listItem.phoneNumber}">${listItem.phoneNumber}</sf:option>--%>
                        <%--</c:forEach>--%>
                        <%--</sf:select>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td colspan="2"><input type="submit" value="Select Customer" /></td>--%>
                <%--</tr>--%>
            <%--</table>--%>
        <%--</sf:form>--%>
    </c:if>


    <%--<h2>Hello ${sessionScope.loginUser.login}. Login Successful!</h2>--%>
</body>
</html>
