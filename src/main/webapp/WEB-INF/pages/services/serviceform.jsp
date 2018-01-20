<%--
  Created by IntelliJ IDEA.
  User: maestro
  Date: 01.01.2018
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Service Form</title>
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
<sf:form action="/services" modelAttribute="serviceForm" >
    <table>
        <tr>
            <td>
                <label for="name">
                    <s:message code="property.enterServiceName"/>
                </label>
            </td>
            <td>
                <sf:input path="name" placeholder="Service Name" pattern="[A-Za-z0-9]{4,}" />
                <sf:errors path="name" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>
                <label for="payroll">
                    <s:message code="property.enterServicePayroll"/>
                </label>
            </td>
            <td>
                <sf:input path="payroll" placeholder="Service Payroll" />
                <sf:errors path="payroll" cssClass="error"/>
            </td>
        </tr>
        <%--<tr>--%>
            <%--<td>--%>
                <%--<label for="balance">--%>
                    <%--<s:message code="property.enterCustomerBalance"/>--%>
                <%--</label>--%>
            <%--</td>--%>
            <%--<td>--%>
                <%--<sf:input path="balance" placeholder="balance" />--%>
                <%--<sf:errors path="balance" cssClass="error"/>--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>--%>
                <%--<label for="user.login">--%>
                    <%--<s:message code="property.enterCustomerBalance"/>--%>
                <%--</label>--%>
            <%--</td>--%>
            <%--<td>--%>
                <%--<sf:input path="user.login" placeholder="User Login" />--%>
                <%--<sf:errors path="user.login" cssClass="error"/>--%>
            <%--</td>--%>
        <%--</tr>--%>
        <tr>
            <td colspan="2">
                <input type="submit" name="Send"/>
            </td>
        </tr>

    </table>
    <%--<sf:hidden path="user"/>--%>
    <%--<sf:hidden path="customerServiceList"/>--%>
    <%--<sf:hidden path="eventList"/>--%>
    <%--<sf:hidden path="billList"/>--%>
    <%--<sf:hidden path="paymentList"/>--%>

    <%--<sf:input path="user" placeholder="user" />--%>

</sf:form>

</body>
</html>
