<%@ page import="com.projet.model.JoueurDto" %>
<%@ page import="com.projet.model.Tuile" %>
<%@ page import="com.projet.model.Element.Ville" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Combat</title>
</head>
<body>
<%
    Tuile tuileAttaque = (Tuile) request.getAttribute("tuileAttaque");
    Tuile tuileSoldat = (Tuile) request.getAttribute("tuileSoldat");
%>
<div style="display: flex; justify-content: space-between;">
<div>
    <%if (tuileAttaque.getSoldat() != null){%>
    <img src="icons/Large/soldier.png">
    <%}else if (tuileAttaque.getElement()!=null && tuileAttaque.getElement() instanceof Ville){%>
    <img src="icons/Large/city.png">
    <%}%>
    <p>Points de défense : <span id="points_entite_attaque"><%=(tuileAttaque.getSoldat()!=null ? tuileAttaque.getSoldat().getPoints_defense() : (tuileAttaque.getElement()!=null && tuileAttaque.getElement() instanceof Ville ? ((Ville)tuileAttaque.getElement()).getPoints_defense() : 0 ) )%></span></p>
</div>
<div>
<button onclick="attaque()">Attaquer</button>
    <p>Points d'attaque de l'attaque précédente : <span id="points_attaque"></span></p>
</div>
<div>
    <img src="icons/Large/soldier.png">
    <p>Points de défense : <span id="points_soldat"><%=tuileSoldat.getSoldat().getPoints_defense()%></span></p>
</div>

<script>
    var soldatPoints = <%=tuileSoldat.getSoldat().getPoints_defense()%>;
    var entiteAttaquePoints = <%=(tuileAttaque.getSoldat()!=null ? tuileAttaque.getSoldat().getPoints_defense() : (tuileAttaque.getElement()!=null && tuileAttaque.getElement() instanceof Ville ? ((Ville)tuileAttaque.getElement()).getPoints_defense() : 0 ) )%>;
    var uniteAttaque = true; //true si notre soldat attaque la ville/le soldat, false si la ville/le soldat attaque notre soldat
    function attaque(){
        var pointsAttaque =  Math.floor(Math.random() * 6) + 1
        document.getElementById("points_attaque").innerHTML=pointsAttaque;
        if (uniteAttaque){ //La ville/le soldat ennemi se fait attaqué
            entiteAttaquePoints -= pointsAttaque;
            document.getElementById("points_entite_attaque").innerHTML=entiteAttaquePoints;
        }else{ // Notre soldat se fait attaqué
            soldatPoints -= pointsAttaque;
            document.getElementById("points_soldat").innerHTML=soldatPoints;
        }
        if (entiteAttaquePoints<=0 || soldatPoints<=0){
            finCombat()
        }
        uniteAttaque = !uniteAttaque;
    }

    function finCombat(){
        const data = new URLSearchParams({
            tuileAttaquePoints : entiteAttaquePoints,
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
