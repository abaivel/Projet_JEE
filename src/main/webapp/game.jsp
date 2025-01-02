<%@ page import="com.projet.model.Tuile.*" %>
<%@ page import="com.projet.model.JoueurDto" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 24/12/2024
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Jeu</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<%Tuile[][] grille = (Tuile[][]) request.getAttribute("grille");
    JoueurDto joueurConnecte = (JoueurDto) request.getAttribute("joueur");
    JoueurDto joueurTour = (JoueurDto) request.getAttribute("joueurTour");
    List<JoueurDto> joueurs = (List<JoueurDto>) request.getAttribute("joueurs");
%>
<div style="display: flex;gap: 5%;">
    <div>
        <table>
            <%for (int i=0;i<10;i++){%>
            <tr>
                <%for (int j=0;j<10;j++){%>
                    <td><div>
                    <%if (grille[i][j] != null){
                        if (grille[i][j] instanceof Montagne){ %>
                            <img src="icons/Small/mountain.png" alt="montagne">
                        <%}else if (grille[i][j] instanceof Ville){%>
                            <img src="icons/Small/city.png"  alt="ville">
                        <%}else if (grille[i][j] instanceof Foret){%>
                            <img src="icons/Small/forest.png"  alt="foret">
                        <%}else if (grille[i][j] instanceof Soldat){
                            if (((Soldat) grille[i][j]).getProprietaire() == null){%>
                                <img style="border:3px solid grey" src="icons/Small/soldier.png"  alt="soldat indé">
                            <%}
                            else if (((Soldat) grille[i][j]).getProprietaire().getLogin().equals(joueurConnecte.getLogin())){%>
                                <img class="img-soldat-alie" style="border:3px solid green" src="icons/Small/soldier.png"  alt="soldat alié" onclick="clicSoldat(this, <%=grille[i][j].getX()%>, <%=grille[i][j].getY()%>)">
                            <%}else{%>
                                <img style="border:3px solid red" src="icons/Small/soldier.png"  alt="soldat ennemi">
                            <%}
                        }
                    }%>
                    </div></td>
                <%}%>
            </tr>
            <%}%>
        </table>
        <div class="buttons-actions">
            <%if (joueurTour.getLogin().equals(joueurConnecte.getLogin())){%>
                <div class="div-buttons-deplacements">
                    <button onclick="allerHaut('<%=joueurConnecte.getLogin()%>')" class="button-deplacement button-deplacement-haut"><img src="icons/fleche_haut.png"></button>
                    <button onclick="allerGauche('<%=joueurConnecte.getLogin()%>')" class="button-deplacement button-deplacement-gauche"><img src="icons/fleche_gauche.png"></button>
                    <button onclick="allerDroite('<%=joueurConnecte.getLogin()%>')" class="button-deplacement button-deplacement-droite"><img src="icons/fleche_droite.png"></button>
                    <button onclick="allerBas('<%=joueurConnecte.getLogin()%>')" class="button-deplacement button-deplacement-bas"><img src="icons/fleche_bas.png"></button>
                </div>
                <div class="div-others-buttons">
                    <button class="other-button">Se soigner</button>
                    <button class="other-button">Fourrager</button>
                </div>
            <%}else{%>
                <div class="div-buttons-deplacements">
                    <button disabled onclick="allerHaut('<%=joueurConnecte.getLogin()%>')" class="button-deplacement button-deplacement-haut"><img src="icons/fleche_haut.png"></button>
                    <button disabled onclick="allerGauche('<%=joueurConnecte.getLogin()%>')" class="button-deplacement button-deplacement-gauche"><img src="icons/fleche_gauche.png"></button>
                    <button disabled onclick="allerDroite('<%=joueurConnecte.getLogin()%>')" class="button-deplacement button-deplacement-droite"><img src="icons/fleche_droite.png"></button>
                    <button disabled onclick="allerBas('<%=joueurConnecte.getLogin()%>')" class="button-deplacement button-deplacement-bas"><img src="icons/fleche_bas.png"></button>
                </div>
                <div class="div-others-buttons">
                    <button disabled class="other-button">Se soigner</button>
                    <button disabled class="other-button">Fourrager</button>
                </div>
            <%}%>
        </div>
    </div>
    <div>
        <p>Joueurs :</p>
        <ul>
            <%for (JoueurDto j : joueurs){%>
                <li><%=j.getLogin()%></li>
            <%}%>
        </ul>
        <%if (joueurTour.getLogin().equals(joueurConnecte.getLogin())){%>
            <p>C'est votre tour</p>
        <%}else{%>
            <p>C'est le tour de <%=joueurTour.getLogin()%></p>
        <%}%>
        <p>Score : <%=joueurConnecte.getScore()%></p>
        <p>Points de production : <%=joueurConnecte.getPoints_production()%></p>
        <form method="post" action="/finPartie">
            <input type="submit" value="Finir la partie">
        </form>
    </div>
</div>
<script>
    var img_selectionne_x = 0
    var img_selectionne_y = 0
    function clicSoldat(image, x, y){
        var soldats = document.getElementsByClassName("img-soldat-alie")
        for (var i=0;i<soldats.length;i++){
            var t = soldats[i]
            t.style.borderColor="green"
        }
        image.style.borderColor="blue"
        img_selectionne_x = x
        img_selectionne_y = y
    }
    function allerGauche(login) {
        const data = new URLSearchParams({
            x_old: img_selectionne_x,
            y_old: img_selectionne_y,
            x_new: img_selectionne_x,
            y_new: img_selectionne_y-1,
            login: login
        });
        callMoveServlet(data)
    }
    function allerDroite(login) {
        const data = new URLSearchParams({
            x_old: img_selectionne_x,
            y_old: img_selectionne_y,
            x_new: img_selectionne_x,
            y_new: img_selectionne_y+1,
            login: login
        });
        callMoveServlet(data)
    }
    function allerHaut(login) {
        const data = new URLSearchParams({
            x_old: img_selectionne_x,
            y_old: img_selectionne_y,
            x_new: img_selectionne_x-1,
            y_new: img_selectionne_y,
            login: login
        });
        callMoveServlet(data)
    }
    function allerBas(login) {
        const data = new URLSearchParams({
            x_old: img_selectionne_x,
            y_old: img_selectionne_y,
            x_new: img_selectionne_x+1,
            y_new: img_selectionne_y,
            login: login
        });
        callMoveServlet(data)
    }
    function callMoveServlet(data){
        const apiUrl = '${pageContext.request.contextPath}/move';
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
                }else {
                    location.reload();
                }
            })
            .catch(err => {
                console.log(err.message);
            });
    }
    function pollServer() {
        location.reload()
    }
    // Polling chaque 5 secondes
    //setInterval(pollServer, 5000);

</script>
</body>
</html>
