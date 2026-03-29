<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <link href="${pageContext.request.contextPath}/style/navbar.css" rel="stylesheet">

    <nav>
        <div data-link="${pageContext.request.contextPath}/home">Home</div>
        <div data-link="${pageContext.request.contextPath}/browse">Browse</div>
        <div data-link="${pageContext.request.contextPath}/about">About</div>
        <div data-link="${pageContext.request.contextPath}/contact">Contact Us</div>
    </nav>

    <script>
        for (const element of document.querySelectorAll("div"))
            element.addEventListener("click", ()=>location.href=element.getAttribute("data-link"));
    </script>
</body>

</html>
