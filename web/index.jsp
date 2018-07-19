<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${cookie.onlineCookie.value.equals('true')}">
    <jsp:forward page="/home"/>
</c:if>

<html>
<head>
    <title>My Tournament</title>
</head>
<body>

<h1>My Tournament</h1>
<div class="image">
    <img src="${pageContext.request.contextPath}/Files/1200px-Darts_in_a_dartboard.jpg">
    <p>La référence de l'organisation de tournois. Par les joueurs. Pour les joueurs</p>
</div>


<div class="connect">
    <a href="${pageContext.request.contextPath}/signin.jsp">Je m'inscris</a>
    <span>ou</span>
    <a href="${pageContext.request.contextPath}/login.jsp">Je me connecte</a>
</div>

</body>
</html>
