<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>S'inscrire - My Tournament</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/loginsignin.css">
</head>
<body>
<jsp:include page="../index.jsp"/>
<h3>Inscription</h3>
<form method="post" action="${pageContext.request.contextPath}/signin">
    <label for="email_form">Entrez votre adresse mail :</label>
    <input type="email" id="email_form" name="email" required/> <br/>
    <label for="pseudo_form">Choisissez un pseudo :</label>
    <input type="text" id="pseudo_form" name="pseudo" required/><br/>
    <label for="password_form">Choisissez un mot de passe :</label>
    <input type="password" id="password_form" name="password" required/><br/>
    <label for="password_confirm_form">Confirmez votre mot de passe :</label>
    <input type="password" id="password_confirm_form" name="password_confirm" required/> <br/>
    <input class="button" type="submit" value="Inscription"/>
</form>

<c:if test="${not empty requestScope.noMatchError}">
    <script> alert('Mots de passes différents.')</script>
</c:if>
<c:if test="${not empty requestScope.shortError}">
    <script> alert('Mot de passe trop court.')</script>
</c:if>
</body>
</html>
