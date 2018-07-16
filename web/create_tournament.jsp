<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Créer un tournoi - My Tournament</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<form method="post" action="${pageContext.request.contextPath}/create">
    <label for="name">Donnez un nom à votre tournoi :</label>
    <input type="text" name="name" id="name"/>
    <c:if test="${not empty requestScope.error}">
        <p>${error}</p>
    </c:if>
    <fieldset>
        <legend>Choisissez votre sport :</legend>
        <input type="radio" name="sports" value="darts" checked> Fléchettes
        <input type="radio" name="sports" value="bowling"> Pétanque
        <input type="radio" name="sports" value="baby-foot"> Baby-Foot
    </fieldset>
    <fieldset>
        <legend>Choisissez votre type de tournoi :</legend>
        <input type="radio" name="type" value="championship" checked>Championnat
        <input type="radio" name="type" value="groups">Poules avec élimination directe
        <input type="radio" name="type" value="finales">Elimination directe
    </fieldset>
    <fieldset>
        <legend>Choisissez votre mode :</legend>
        <input type="radio" name="mode" value="individual" checked>Individuel
        <input type="radio" name="mode" value="teams">Par équipes
    </fieldset>
    <fieldset>
        <legend>Sélectionnez vos joueurs :</legend>
        <select name="players">
            <c:forEach items="${sessionScope.playerNames}" var="player">
                <option>${player}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Ajouter" onclick="form.action='/addplayer';">


        <c:forEach items="${sessionScope.playerSelected}" var="selected">
            <p>${selected}</p>
        </c:forEach>
    </fieldset>
    <input type="submit" value="C'est parti !"/>

</form>

</body>
</html>
