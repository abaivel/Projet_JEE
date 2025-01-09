<%@ page import="com.projet.model.JoueurDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accueil</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body class="body-login">
<%JoueurDto j = (JoueurDto) request.getAttribute("joueur");%>

<div class="fond-login">
    <div class="container-login" style="width:20%">
        <h2 style="text-align: center;margin: 0 0 3%;">Bienvenue <%=j.getLogin()%></h2>
        <div style="display: flex; justify-content: space-evenly;">
            <a style="width: 40%;" href="${pageContext.request.contextPath}/game">
                <button class="button-accueil">Partie</button>
            </a>
            <a style="width: 40%;" href="${pageContext.request.contextPath}/recapScoresJoueur">
                <button class="button-accueil">Recap des scores</button>
            </a>
        </div>
    </div>
</div>
</body>
</html>
