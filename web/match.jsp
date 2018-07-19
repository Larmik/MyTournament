<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${sessionScope.match.player1} VS ${sessionScope.match.player2} - MyTournament</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h3>Match du tournoi ${sessionScope.tournament.name}</h3>
<h4>${sessionScope.match.player1} VS ${sessionScope.match.player2}</h4>
<div class="container" style="display: flex">
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
        <input type="submit" value="Ajouter">
    </form>

</div>
</body>
</html>
