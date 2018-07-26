<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" href="../CSS/header.css" />
</head>
<header>
    <h1>My Tournament</h1>
    <c:if test="${cookie.onlineCookie.value.equals('true')}">
        <nav>
            <ul class="navigation">
                <li>Bonjour ${cookie.pseudoCookie.value} !</li>
                <li><a href="home">Accueil</a></li>
                <li><a href="create">Créer un tournoi</a></li>
                <li><a href="create_team">Créer une équipe</a></li>
                <li><a href="show">Mes tournois</a></li>
                <li><a href="../index.jsp" onclick="deleteAllCookies()">Déconnexion</a></li>
            </ul>
        </nav>
    </c:if>
</header>


<div class="background_opacity"></div>

<script>
    function deleteAllCookies() {
        var cookies = document.cookie.split(";");

        for (var i = 0; i < cookies.length; i++) {
            var cookie = cookies[i];
            var eqPos = cookie.indexOf("=");
            var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
            document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
        }
        alert("Vous vous êtes déconnecté.")
    }
</script>