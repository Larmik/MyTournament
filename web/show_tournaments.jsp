<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mes tournois - My Tournament</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h3>Mes tournois</h3>
<table>
    <tr>
        <th>Nom du tournoi</th>
        <th>Discipline</th>
        <th>Type de tournoi</th>
        <th>Mode de jeu</th>
    </tr>

    <c:forEach items="${sessionScope.tournamentList}" var="tournament">
        <tr>
            <td>${tournament.name}</td>
            <td>${tournament.sport}</td>
            <td>${tournament.type}</td>
            <td>${tournament.mode}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
