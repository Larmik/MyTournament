<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${cookie.onlineCookie.value.equals('true')}">
    <jsp:forward page="/home"/>
</c:if>

<html>
<head>
    <title>My Tournament</title>
</head>
<body>

<h1>My Tournament</h1>
<img src="http://via.placeholder.com/600x400">
<p>Lorem ipsum dolor amet tumblr lomo synth shoreditch kinfolk.
</p>
<a href="${pageContext.request.contextPath}/signin.jsp">Je m'inscris</a>
<span>ou</span>
<a href="${pageContext.request.contextPath}/login.jsp">Je me connecte</a>

</body>
</html>
