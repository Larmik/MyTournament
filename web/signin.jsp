<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>S'inscrire - My Tournament</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <h3>Inscription</h3>
    <form method="post" action="${pageContext.request.contextPath}/signin">
        <label for="email_form">Entrez votre adresse mail :</label>
        <input type="email" id="email_form" name="email" required/>
        <label for="pseudo_form">Choisissez un pseudo :</label>
        <input type="text" id="pseudo_form" name="pseudo" required/>
        <label for="password_form">Choisissez un mot de passe :</label>
        <input type="password" id="password_form" name="password" required/>
        <label for="password_confirm_form">Confirmez votre mot de passe :</label>
        <input type="password" id="password_confirm_form" name="password_confirm" required/>
        <input type="submit" value="Inscription"/>
    </form>
</body>
</html>
