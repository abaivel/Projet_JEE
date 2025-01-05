<%@ page import="com.projet.model.JoueurDto" %>
<%@ page import="com.projet.model.Tuile" %>
<%@ page import="com.projet.model.Element.Ville" %>
<%@ page import="java.util.Random" %><%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 24/12/2024
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Combat</title>
</head>
<body>
<% JoueurDto joueurConnecte = (JoueurDto) request.getAttribute("joueurConnecte");
    Tuile tuileAttaque = (Tuile) request.getAttribute("tuileAttaque");
    Tuile tuileSoldat = (Tuile) request.getAttribute("tuileSoldat");
%>
<div style="display: flex; justify-content: space-between;">
<%if (tuileAttaque.getElement() instanceof Ville){%>
    <div>
        <img src="icons/Large/city.png">
        <p>Points de défense : <span id="points_ville"><%=((Ville) tuileAttaque.getElement()).getPoints_defense()%></span></p>
    </div>
    <div>
    <button onclick="attaque()">Attaquer</button>
        <p>Points d'attaque de l'attaque précédente : <span id="points_attaque"></span></p>
    </div>
    <div>
        <img src="icons/Large/soldier.png">
        <p>Points de défense : <span id="points_soldat"><%=tuileSoldat.getSoldat().getPoints_defence()%></span></p>
    </div>

<%}%>
<script>
    var soldatPoints = <%=tuileSoldat.getSoldat().getPoints_defence()%>;
    var attaquePoints = <%=((Ville)tuileAttaque.getElement()).getPoints_defense()%>;
    var uniteAttaque = true; //true si le soldat attaque la ville, false si la ville attaque le soldat
    function attaque(){
        var pointsAttaque =  Math.floor(Math.random() * 6) + 1
        document.getElementById("points_attaque").innerHTML=pointsAttaque;
        if (uniteAttaque){
            attaquePoints -= pointsAttaque;
            document.getElementById("points_ville").innerHTML=attaquePoints;
        }else{
            soldatPoints -= pointsAttaque;
            document.getElementById("points_soldat").innerHTML=soldatPoints;
        }
        console.log(attaquePoints)
        console.log(soldatPoints)
        if (attaquePoints<=0 || soldatPoints<=0){
            finCombat()
        }
        uniteAttaque = !uniteAttaque;
    }

    function finCombat(){
        const data = new URLSearchParams({
            tuileAttaquePoints : attaquePoints,
            soldatPoints : soldatPoints
        });
        const apiUrl = '${pageContext.request.contextPath}/combat';
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: data.toString(),
        };

        fetch(apiUrl, requestOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                    //afficher une erreur sur la page
                }
                return response.json()
            })
            .then(data => {
                if (data.redirect) {
                    window.location.href = data.redirect; // Redirection vers l'URL renvoyée
                }
            })
            .catch(err => {
                console.log(err.message);
            });
    }
</script>
</div>
</body>
</html>
