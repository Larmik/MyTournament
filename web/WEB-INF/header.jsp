<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/header.css" />
</head>
<header>
    <h1>My Tournament</h1>
    <c:if test="${cookie.onlineCookie.value.equals('true')}">
        <nav>
            <ul class="navigation">
                <li>Bonjour ${cookie.pseudoCookie.value} !</li>
                <li><a href="${pageContext.request.contextPath}/home">Accueil</a></li>
                <li><a href="${pageContext.request.contextPath}/create">Créer un tournoi</a></li>
                <li><a href="${pageContext.request.contextPath}/create_team">Créer une équipe</a></li>
                <li><a href="${pageContext.request.contextPath}/show">Mes tournois</a></li>
                <li><a href="${pageContext.request.contextPath}/delete" onclick="alert('Vous vous êtes déconnecté')">Déconnexion</a></li>
            </ul>
        </nav>
    </c:if>
</header>


<div class="background_opacity"></div>

