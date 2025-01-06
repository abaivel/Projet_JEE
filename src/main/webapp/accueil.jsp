<%@ page import="com.projet.model.JoueurDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="background-image: url(images/fond_ecran_connexion.jpeg); background-repeat: no-repeat; background-size: cover;width: 100%; height: 100%; margin: 0; overflow: hidden">
<%JoueurDto j = (JoueurDto) request.getAttribute("joueur");%>

<div style="background-color: #ffffff8a;width: 100%; height: 100%;margin: 0;align-content: center;">
    <div style="box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;border-radius: 15px; background-color: white; width: 20%; margin: auto; padding: 1%;">
        <h2 style="text-align: center;margin: 0 0 3%;">Bienvenue <%=j.getLogin()%></h2>
        <div style="display: flex;justify-content: space-evenly;">
        <a style="width: 40%;" href="${pageContext.request.contextPath}/game"><button style="width: 100%;font-size: 15px;border-radius: 15px;padding: 2px;background-color: #ffffff;border-color: #000000;font-family: 'Times New Roman',serif;">Partie</button></a>

            <a style="width: 40%;" href="${pageContext.request.contextPath}/recapScoresJoueur">
                <button style="width: 100%;font-size: 15px;border-radius: 15px;padding: 2px;background-color: #ffffff;border-color: #000000;font-family: 'Times New Roman',serif;">
                    Scores des anciennes parties
                </button>
            </a>
        </div>
    </div>
</div>
</body>
</html>
