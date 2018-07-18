<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${sessionScope.match.player1} VS ${sessionScope.match.player2} - MyTournament</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h3>${sessionScope.match.player1} VS ${sessionScope.match.player2}</h3>
</body>
</html>
