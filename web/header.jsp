<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>My Tournament</h1>
<nav>
    <ul>
        <li><a href="index.jsp">Accueil</a> </li>
        <c:if test="${sessionScope.isConnected}">
            <li><a href="create">Créer un tournoi</a> </li>
        </c:if>
       <c:if test="${!sessionScope.isConnected}">
           <li><a href="#" onclick="alert('Veuillez vous connecter pour créer un tournoi')">Créer un tournoi</a> </li>
       </c:if>
        <li><a href="index.jsp">Participer à un tournoi</a> </li>
    </ul>
    <c:if test="${requestScope.isConnected}">
        <div>
            <span>Bonjour ${requestScope.pseudo} !</span>
            <a href="index.jsp">Déconnexion</a>
        </div>
    </c:if>
</nav>