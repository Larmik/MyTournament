<%--
  Created by IntelliJ IDEA.
  User: wilder
  Date: 12/07/18
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Se connecter - My Tournament</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <h3>Connexion</h3>
    <form method="post" action="login">
        <label for="pseudo_form">Pseudo :</label>
        <input type="text" id="pseudo_form" name="pseudo" required/>
        <label for="password_form">Mot de passe :</label>
        <input type="password" id="password_form" name="password" required/>
        <input type="submit" value="Connexion"/>
    </form>
</body>
</html>
