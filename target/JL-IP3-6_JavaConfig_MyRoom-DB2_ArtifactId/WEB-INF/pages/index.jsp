<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="../../resources/static/js/services.js"></script>
    <%--<link rel="stylesheet" type="text/css" href="resources/static/css/stylesheet.css">--%>
</head>
<body onload="init()">
    <table>
        <tr>
            <td><a href="login">|Login|</a></td>
            <td><a href="registration">|Registration|</a></td>
            <%--<td><a href="/logout">|Logout|</a></td>--%>
        </tr>
    </table>




<%--<br><br>--%>
<%--<h2>Login/Registration</h2>--%>
<%--<h2>Hello World!, index.jsp</h2>--%>
</body>
<br>
<br>
<br>
<br>
<form name="autofillform" action="autocomplete">
    <table border="0" cellpadding="5">
        <thead>
        <tr>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><strong>Product Name:</strong></td>
            <td>
                <input type="text"
                       size="40"
                       id="complete-field"
                       onkeyup="doCompletion();">
            </td>
        </tr>
        <tr>
            <td id="auto-row" colspan="2">
                <table id="complete-table" class="popupBox">
                </table>
            </td>
        </tr>
        </tbody>
    </table>
</form>
<br>
Try
<a href="http://localhost:8080/autocomplete?action=complete&search=">
    this URL
</a>
to see how native XML creation works in Spring.

</html>
