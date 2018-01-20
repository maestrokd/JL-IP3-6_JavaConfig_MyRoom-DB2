<%--<!DOCTYPE html>--%>
<%--
  Created by IntelliJ IDEA.
  User: maestro
  Date: 21.12.2017
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <script type="text/javascript" src="../../resources/static/js/servicesforcustomer.js"></script>
    <title>Customer Room</title>
</head>
<body onload="init()">
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
    <c:if test="${not empty message}">
        <strong>${message}</strong>
    </c:if>
    <br>
    <br>
     <table>
         <caption>Selected Customer ${selectedCustomer.phoneNumber}:</caption>
         <tr>
             <th>Phone number</th>
             <th>Customer Status</th>
             <th>Balance</th>
             <th>Action</th>
         </tr>
         <tr>
             <td>|${selectedCustomer.phoneNumber}|</td>
             <td>|${selectedCustomer.customerStatus.name}|</td>
             <td>|${selectedCustomer.balance}|</td>
             <td>
                 <button onclick="location.href='/customer/${selectedCustomer.phoneNumber}'">Info</button>
                 <button onclick="location.href='/customer/${selectedCustomer.phoneNumber}/update'">Update</button>
                 <button onclick="location.href='/customer/${selectedCustomer.phoneNumber}/delete'">Delete</button>
             </td>
         </tr>
     </table>

     <%--<c:if test="${!empty selectedCustomer.customerServiceList}">--%>
         <%--<table>--%>
            <%--<caption>ServicesDto for ${selectedCustomer.phoneNumber}</caption>--%>
            <%--<tr>--%>
                <%--<th>Service</th>--%>
                <%--<th>Payroll</th>--%>
                <%--<th>Status</th>--%>
                <%--&lt;%&ndash;<th>Balance</th>&ndash;%&gt;--%>
            <%--</tr>--%>
            <%--<c:forEach var="listItem" items="${selectedCustomer.customerServiceList}">--%>
            <%--<tr>--%>
                <%--<td value="${listItem}">|${listItem.service.name}|</td>--%>
                <%--<td value="${listItem}">|${listItem.service.payroll}|</td>--%>
                <%--<td value="${listItem}">|${listItem.serviceStatus.name}|</td>--%>
                <%--&lt;%&ndash;<td value="${listItem}">|${listItem.balance}|</td>&ndash;%&gt;--%>
            <%--</tr>--%>
             <%--</c:forEach>--%>
        <%--</table>--%>
     <%--</c:if>--%>
    <br>
    <br>
    <table>
        <tr>
            <td>
                <label for="complete-field">Select Service for Phone Number</label>
            </td>
            <td>
                <%--<input id="selected-phone-number" value="${selectedCustomer.phoneNumber}" />--%>
                <div id="selected-phone-number" >${selectedCustomer.phoneNumber}</div>
            </td>
            <td>
                <select id="complete-field">
                    <c:forEach var="listItem" items="${serviceList}">
                        <option value="${listItem.name}">${listItem.name}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <input type="submit" value="Add Service" onclick="doAddService()" >
            </td>
            <td>
                <input type="submit" value="Show Services" onclick="doShowServices()" >
            </td>
        </tr>
        <%--<tr>--%>
            <%--<td id="auto-row" colspan="3" >--%>
                <%--<table id="complete-table" >--%>
                <%--</table>--%>
            <%--</td>--%>
        <%--</tr>--%>
    </table>
    <table>
        <caption>Services for ${selectedCustomer.phoneNumber}</caption>
        <tr>
            <th>Service</th>
            <th>Payroll</th>
            <th>Customer Status</th>
            <th>Action</th>
        </tr>
    </table>
    <div id="auto-row" >
        <table id="complete-table" >
        </table>
    </div>

    <%--<h2>Hello Customer Room!</h2>--%>
</body>
</html>
