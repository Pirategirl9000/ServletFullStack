<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Servlets Contact Us</title>
    <link href="${pageContext.request.contextPath}/style/fonts.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/style/contact.css" rel="stylesheet">
</head>

<body>
    <%@ include file="header.jspf"%>

    <h1>Contact Us</h1>

    <form id="email-form" method="POST">
        <table>
            <tr>
                <td><label for="from">From: </label></td>
                <td><input id="from" name="from" type="email"><span> *</span></td>
            </tr>

            <tr>
                <td><label for="subject">Subject: </label></td>
                <td><input id="subject" name="subject" type="text"><span> *</span></td>
            </tr>
            <tr>
                <td><label for="body">Body: </label><span>*</span></td>
            </tr>
        </table>
        <textarea id="body" name="body"></textarea>

        <br>
        <input id="submit" type="submit">
    </form>

    <p id="email-confirm"></p>

    <script src="${pageContext.request.contextPath}/src/contact.js"></script>

</body>
</html>