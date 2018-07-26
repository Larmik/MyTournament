<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${cookie.onlineCookie.value.equals('true')}">
    <jsp:forward page="/home"/>
</c:if>

<html>
<head>
    <title>My Tournament</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/index.css">
</head>
<body>

<h1>My Tournament</h1>
<p>La référence de l'organisation de tournois. Par les joueurs. Pour les joueurs.</p>
<div class="background_opacity"></div>
<div class="connect">
    <a href="${pageContext.request.contextPath}/signin">Je m'inscris</a>
    <span>ou</span>
    <a href="${pageContext.request.contextPath}/login">Je me connecte</a>
</div>
</body>
</html>
