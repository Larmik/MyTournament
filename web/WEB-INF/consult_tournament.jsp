<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${sessionScope.tournament.name} - My Tournament</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/tournament.css"/>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
<h3>${sessionScope.tournament.name}</h3>
<table>
    <tr>
        <th>Classement</th>
        <th>Joueur</th>
        <th>Points</th>
        <th>Victoires</th>
        <th>Défaites</th>
        <th>Différence</th>
    </tr>
    <c:forEach items="${sessionScope.players}" var="player">
        <tr>
            <td>${player.position}</td>
            <td class="middle">${player.pseudo}</td>
            <td class="middle">${player.points}</td>
            <td class="middle">${player.wins}</td>
            <td class="middle">${player.loses}</td>
            <td>${player.winsets - player.losesets}</td>
        </tr>
    </c:forEach>
</table>
<h5>Résultats</h5>
    <c:forEach items="${sessionScope.matches}" var="match">
        <c:if test="${match.hasBeenPlayed}">
            <div class="matches">
                    ${match.player1}   ${match.score1} - ${match.score2}  ${match.player2}
            </div>
        </c:if>
    </c:forEach>



<h5>Matches à venir</h5>
<c:forEach items="${sessionScope.matches}" var="match">
    <c:if test="${not match.hasBeenPlayed}">
        <div class="matches" >
            <div class="details">
                <p>${match.player1} VS ${match.player2}</p>
                <a href="${pageContext.request.contextPath}/match?id=${match.id}">Jouer ce match</a>
            </div>
        </div>

    </c:if>
</c:forEach>




</body>
</html>
