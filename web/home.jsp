<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accueil - My Tournament</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<img src="http://via.placeholder.com/600x400">
<p>Lorem ipsum dolor amet tumblr lomo synth shoreditch kinfolk, pok pok cornhole taiyaki cold-pressed mlkshk try-hard.
    DIY normcore raw denim, literally activated charcoal food truck cornhole retro williamsburg franzen photo booth.
    Cloud bread migas humblebrag photo booth, plaid brooklyn offal narwhal celiac YOLO. Health goth blue bottle hell of
    tilde 3 wolf moon kitsch, taxidermy twee farm-to-table try-hard. Cloud bread af woke, synth lyft listicle thundercats
    master cleanse blog unicorn ennui jean shorts beard narwhal. Af tumblr meh glossier health goth stumptown fam.
</p>

<c:if test="${not empty requestScope.success}">
    <script>
        window.addEventListener("load",function(){
            alert("${success}");
        })
    </script>
</c:if>
</body>
</html>
