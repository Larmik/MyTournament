<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Créer un tournoi - My Tournament</title>
    <link rel="stylesheet" href="CSS/create.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<h3>Créer un tournoi</h3>
<form method="post" action="${pageContext.request.contextPath}/create">
    <c:if test="${not empty requestScope.error}">
        <script>alert('Veuillez nommer votre tournoi')</script>
    </c:if>
    <fieldset>
        <legend>Choisissez votre sport :</legend>
        <label for="darts">Fléchettes</label>
        <input type="radio" name="sports" value="Fléchettes" id="darts" checked>
        <label for="bowl">Pétanque</label>
        <input type="radio" name="sports" value="Pétanque" id="bowl">
        <label for="baby">Baby-foot</label>
        <input type="radio" name="sports" value="Baby-foot" id="baby">
    </fieldset>
    <fieldset>
        <legend>Choisissez votre type de tournoi :</legend>
        <label for="champion">Championnat</label>
        <input type="radio" name="type" value="Championnat" id="champion" checked>
        <label for="poules">Poules avec élimination directe</label>
        <input type="radio" name="type" value="Poules/Elimination" id="poules">
        <label for="final">Elimination directe</label>
        <input type="radio" name="type" value="Elimination" id="final">
    </fieldset>
    <fieldset>
        <legend>Choisissez votre mode :</legend>
        <label for="indiv">Individuel</label>
        <input type="radio" name="mode" value="Individuel" id="indiv" checked>
        <label for="team">Par équipes</label>
        <input type="radio" name="mode" value="Par équipes" id="team">
    </fieldset>
    <fieldset>
        <legend>Sélectionnez vos joueurs :</legend>
        <select name="players">
            <c:forEach items="${sessionScope.playerNames}" var="player">
                <option>${player}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Ajouter" onclick="form.action='/addplayer';" class="button">
        <c:forEach items="${sessionScope.playerSelected}" var="selected">
            <p>${selected}</p>
        </c:forEach>
    </fieldset>
    <fieldset>
        <legend>Donnez un nom à votre tournoi :</legend>
        <label for="name">Nom :</label>
        <input type="text" name="name" id="name"/>
    </fieldset>
    <input type="submit" value="C'est parti !" class="button"/>

</form>
<c:if test="${not empty requestScope.emptyError}">
    <script>alert('Veuillez choisir au moins deux joueurs')</script>
</c:if>
</body>
</html>
