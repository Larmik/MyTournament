
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>My Tournament</h1>
<nav>
    <ul>
        <li><a href="index.jsp">Accueil</a></li>
            <li><a href="create">Créer un tournoi</a></li>
            <li><a href="index.jsp">Créer une équipe</a></li>
    </ul>
    <c:if test="${cookie.onlineCookie.value.equals('true')}">
        <div>
            <span>Bonjour ${cookie.pseudoCookie.value} !</span>
            <a href="index.jsp"  onclick="deleteAllCookies()">Déconnexion</a>
        </div>
    </c:if>
</nav>

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