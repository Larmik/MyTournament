<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${sessionScope.tournament.name} - My Tournament</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h3>${sessionScope.tournament.name}</h3>
<h5>Classement général</h5>
<table>
    <tr>
        <th>Classement</th>
        <th>Joueur</th>
        <th>Points</th>
        <th>Victoires</th>
        <th>Défaites</th>
    </tr>
    <c:forEach items="${sessionScope.players}" var="player">
        <tr>
            <td>${player.position}</td>
            <td>${player.pseudo}</td>
            <td>${player.points}</td>
            <td>${player.wins}</td>
            <td>${player.loses}</td>
        </tr>
    </c:forEach>
</table>
<h5>Calendrier</h5>
<c:forEach items="${sessionScope.matches}" var="match">
    <div>
            ${match.player1}
        V.S
            ${match.player2}
        <a href="${pageContext.request.contextPath}/match?id=${match.id}">Jouer ce match</a>
    </div>
</c:forEach>
</body>
</html>
