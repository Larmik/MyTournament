<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Se connecter - My Tournament</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/loginsignin.css">
</head>
<body>
<jsp:include page="../index.jsp"/>
<h3>Connexion</h3>
<form method="post" action="${pageContext.request.contextPath}/login">
    <label for="pseudo_form">Pseudo :</label>
    <input type="text" id="pseudo_form" name="pseudo" required/> <br/>
    <label for="password_form">Mot de passe :</label>
    <input type="password" id="password_form" name="password" required/><br/>
    <input class="button" type="submit" value="Connexion"/>
</form>
<c:if test="${not empty requestScope.error}">
    <script> alert('Identifiants incorrects.')</script>
</c:if>
</body>
</html>
