<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Registration jsp</title>
    <style>
        .error{
            color:#ff0000;
        }
    </style>
</head>
<body>
    <table>
        <tr>
            <td><a href="/WEB-INF/pages/index.jsp">|Return to home|</a></td>
            <td><a href="login">|Login|</a></td>
        </tr>
    </table>
    <br>
    <br>

<sf:form action="/userregistration" modelAttribute="registeredUser" >
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
            </td>
            <td>
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
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" name="Send"/>
            </td>
        </tr>
    </table>
</sf:form>

<%--<h2>Hello Registraion!</h2>--%>
</body>
</html>
