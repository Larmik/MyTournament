<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>My Tournament</h1>
<nav>
    <ul>
        <li><a href="index.jsp">Accueil</a> </li>
        <c:if test="${sessionScope.isConnected}">
            <li><a href="create">Créer un tournoi</a> </li>
            <li><a href="index.jsp">Participer à un tournoi</a> </li>
            <li><a href="index.jsp">Créer une équipe</a></li>
        </c:if>
       <c:if test="${!sessionScope.isConnected}">
           <li><a href="#" onclick="alert('Veuillez vous connecter pour créer un tournoi')">Créer un tournoi</a> </li>
           <li><a href="#" onclick="alert('Veuillez vous connecter pour participer à un tournoi')">Participer à un tournoi</a> </li>
           <li><a href="#" onclick="alert('Veuillez vous connecter pour créer ou rejoindre une équipe')">Créer une équipe</a></li>
       </c:if>
    </ul>
    <c:if test="${sessionScope.isConnected}">
        <div>
            <span>Bonjour ${sessionScope.pseudo} !</span>
            <a href="index.jsp">Déconnexion</a>
        </div>
    </c:if>
</nav>