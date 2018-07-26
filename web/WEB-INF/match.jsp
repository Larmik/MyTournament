<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${sessionScope.match.player1} VS ${sessionScope.match.player2} - MyTournament</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/match.css">
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
<h3>Match du tournoi ${sessionScope.tournament.name}</h3>
<h4>${sessionScope.match.player1} VS ${sessionScope.match.player2}</h4>
<div class="container" >
    <form method="post" action="${pageContext.request.contextPath}/match?id=${sessionScope.match.id}">
    <table>
        <tr>
            <th>${sessionScope.match.player1}</th>
            <th>${sessionScope.match.player2}</th>
        </tr>
        <tr>
            <td><select name="winone">
                <option>Manches gagnées</option>
                <option>0</option>
                <option>1</option>
                <option>2</option>
            </select></td>
            <td><select name="wintwo" >
                <option>Manches gagnées</option>
                <option>0</option>
                <option>1</option>
                <option>2</option>
            </select></td>
        </tr>
    </table>
        <input type="submit" value="Ajouter le résultat">
    </form>
</div>
<c:if test="${not empty requestScope.incompleteError}">
    <script>alert('${requestScope.incompleteError}')</script>
</c:if>
<c:if test="${not empty requestScope.drawError}">
    <script>alert('${requestScope.drawError}')</script>
</c:if>
<c:if test="${not empty requestScope.invalidError}">
    <script>alert('${requestScope.invalidError}')</script>
</c:if>
</body>
</html>
