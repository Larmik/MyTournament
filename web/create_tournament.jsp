<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Créer un tournoi - My Tournament</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<form method="post">
    <fieldset>
        <legend>Choisissez votre sport :</legend>
        <input type="radio" name="sports" value="Fléchettes" onclick="${requestScope.dartsChecked = true}"> Fléchettes
        <input type="radio" name="sports" value="Pétanque" onclick="${requestScope.bowlingChecked = true}"> Pétanque
        <input type="radio" name="sports" value="Baby-foot" onclick="${requestScope.bfChecked = true}"> Baby-Foot
    </fieldset>
</form>
</body>
</html>
