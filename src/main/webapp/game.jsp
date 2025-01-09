<%@ page import="com.projet.model.Element.*" %>
<%@ page import="com.projet.model.JoueurDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.projet.model.Soldat" %>
<%@ page import="com.projet.model.Tuile" %><%--
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
<%
    Tuile[][] grille = (Tuile[][]) request.getAttribute("grille");
    JoueurDto joueurConnecte = (JoueurDto) request.getAttribute("joueur");
    JoueurDto joueurTour = (JoueurDto) request.getAttribute("joueurTour");
    List<JoueurDto> joueurs = (List<JoueurDto>) request.getAttribute("joueurs");
%>
<button class="other-button" type="button" name="back" onclick="history.back()">Retour</button>
<div style="display: flex;gap: 5%;">
    <div>
        <table id = "map">
            <%for (int i=0;i<10;i++){%>
            <tr>
                <%for (int j=0;j<10;j++){%>
                    <td><div>
                    <%if (grille[i][j].getElement() != null){
                        if (grille[i][j].getElement() instanceof Montagne){ %>
                            <img src="icons/Small/mountain.png" alt="montagne">
                        <%}else if (grille[i][j].getElement() instanceof Ville){
                            if (((Ville) grille[i][j].getElement()).getProprietaire() == null){%>
                                <img style="border:3px solid grey" src="icons/Small/city.png"  alt="ville indé">
                            <%}else if (((Ville) grille[i][j].getElement()).getProprietaire().getLogin().equals(joueurConnecte.getLogin())){%>
                                <img class="img-soldat-alie" style="border:3px solid green" src="icons/Small/city.png"  alt="ville alié">
                            <%}else{%>
                                <img style="border:3px solid red" src="icons/Small/city.png"  alt="ville ennemi">
                            <%}%>
                        <%}else if (grille[i][j].getElement() instanceof Foret){%>
                            <img src="icons/Small/forest.png"  alt="foret">
                        <%}
                    }if (grille[i][j].getSoldat()!=null){
                        if (grille[i][j].getSoldat().getProprietaire() == null){%>
                        <img style="border:3px solid grey" src="icons/Small/soldier.png"  alt="soldat indé">
                        <%}
                        else if (grille[i][j].getSoldat().getProprietaire().getLogin().equals(joueurConnecte.getLogin())){%>
                        <img class="img-soldat-alie" style="border:3px solid green" src="icons/Small/soldier.png"  alt="soldat alié" <%if (grille[i][j].getSoldat().CanPlay()){%> onclick="clicSoldat(this, <%=i%>, <%=j%>)<%}%>">
                        <%}else{%>
                        <img style="border:3px solid red" src="icons/Small/soldier.png"  alt="soldat ennemi">
                        <%}
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
                <div class="div-others-buttons" id="other-button-div">
                    <button onclick="seSoigner('<%=joueurConnecte.getLogin()%>')" class="other-button">Se soigner</button>
                    <button onclick="passerTour('<%=joueurConnecte.getLogin()%>')" class="other-button">finir le tour</button>
                    <button onclick="fourrager('<%=joueurConnecte.getLogin()%>')" style="display: none;" class="other-button" id="fourrage">Fourrager</button>
                </div>
                <p style="margin: 0 0 0 10px;" id="nb-points-defense"></p>
            <%}else{%>
                <div class="div-buttons-deplacements">
                    <button disabled onclick="allerHaut('<%=joueurConnecte.getLogin()%>')" class="button-deplacement button-deplacement-haut"><img src="icons/fleche_haut.png"></button>
                    <button disabled onclick="allerGauche('<%=joueurConnecte.getLogin()%>')" class="button-deplacement button-deplacement-gauche"><img src="icons/fleche_gauche.png"></button>
                    <button disabled onclick="allerDroite('<%=joueurConnecte.getLogin()%>')" class="button-deplacement button-deplacement-droite"><img src="icons/fleche_droite.png"></button>
                    <button disabled onclick="allerBas('<%=joueurConnecte.getLogin()%>')" class="button-deplacement-disabled button-deplacement-bas"><img src="icons/fleche_bas.png"></button>
                </div>
                <div class="div-others-buttons">
                    <button disabled class="other-button-disabled">Se soigner</button>
                    <button disabled class="other-button-disabled">Fourrager</button>
                </div>
            <%}%>
        </div>
    </div>
    <div>
        <p>Joueurs :</p>
        <ul>
            <%for (JoueurDto j : joueurs){%>
            <%if (j.getLogin().equals(joueurConnecte.getLogin())){%>
                <%if (joueurTour.getLogin().equals(j.getLogin())){%>
                <li class ="name-tour"><%=j.getLogin()%> (vous)</li>
                <%}else{%>
                <li><%=j.getLogin()%> (vous)</li>
            <%}}else{%>
            <%if (joueurTour.getLogin().equals(j.getLogin())){%>
            <li class ="name-tour"><%=j.getLogin()%></li>
            <%}else{%>
                <li><%=j.getLogin()%></li>
            <%}
            }
            }%>
        </ul>
        <%if (joueurTour.getLogin().equals(joueurConnecte.getLogin())){%>
            <p>C'est votre tour</p>
        <%}else{%>
            <p>C'est le tour de <%=joueurTour.getLogin()%></p>
        <%}%>
        <p>Score : <%=joueurConnecte.getScore()%></p>
        <p>Points de production : <%=joueurConnecte.getPoints_production()%></p>
        <form>
            <%if (joueurConnecte.getPoints_production()>=15){%>
            <button class="other-button" onclick="recruter('<%=joueurConnecte.getLogin()%>')"> Recruter un soldat : 15 points de production </button>
            <%}else{%>
            <input class="other-button-disabled" disabled type="submit" value="Recruter un soldat : 15 points de production">
            <%}%>
        </form>
        <form method="post" action="${pageContext.request.contextPath}/finPartie">
            <input class="other-button" type="submit" value="Finir la partie">
        </form>
    </div>
</div>
<script>
    var img_selectionne_x = 0
    var img_selectionne_y = 0
    var grille = [[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0]]
    <% for (int i=0;i<10;i++){%>
        <%for (int j=0;j<10;j++){
        if (grille[i][j].getSoldat() != null){%>
            grille[<%=i%>][<%=j%>] = <%=grille[i][j].getSoldat().getPoints_defence()%>
        <%}}
    }%>
    function clicSoldat(image, x, y){
        var soldats = document.getElementsByClassName("img-soldat-alie")
        for (var i=0;i<soldats.length;i++){
            var t = soldats[i]
            t.style.borderColor="green"
        }
        image.style.borderColor="blue"
        img_selectionne_x = x
        img_selectionne_y = y
        console.log(x)
        console.log(y)
        const text_points_defense = document.getElementById("nb-points-defense");
        text_points_defense.innerHTML = "Points de défense : " + grille[parseInt(x)][parseInt(y)].toString()

        //faire apparaitre les boutons
        var button= document.getElementById("fourrage");
        const cell = getCell("map",x,y).querySelectorAll("img")
        if (Array.from(cell).some(img => img.getAttribute("alt") === "foret")){ //tester si la case est une forêt, si oui, on affiche le bouton
            button.style.display = 'block';
        }else{
            button.style.display = 'none';
        }

    }
    function getCell(tableId, rowIndex, colIndex) {
        const table = document.getElementById(tableId);
        const row = table.rows[rowIndex];
        return row ? row.cells[colIndex] : null;
    }
    function callServlet(data, servlet){
        const apiUrl = '${pageContext.request.contextPath}'+servlet;
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: data.toString(),
        };

        fetch(apiUrl, requestOptions)
            .then(response => {
                if (response.status === 301){
                    return response.json();
                }else if (!response.ok) {
                    throw new Error('Network response was not ok');
                    //afficher une erreur sur la page
                }
                else{
                    location.reload();
                }
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
    function fourrager(login) {
        const data = new URLSearchParams({
            x: img_selectionne_x,
            y: img_selectionne_y,
            login: login
        });
        callServlet(data, "/fourrage");
    }


    function allerGauche(login) {
        const data = new URLSearchParams({
            x_old: img_selectionne_x,
            y_old: img_selectionne_y,
            x_new: img_selectionne_x,
            y_new: img_selectionne_y-1,
            login: login
        });
        callServlet(data, "/move");
    }
    function allerDroite(login) {
        const data = new URLSearchParams({
            x_old: img_selectionne_x,
            y_old: img_selectionne_y,
            x_new: img_selectionne_x,
            y_new: img_selectionne_y+1,
            login: login
        });
        callServlet(data, "/move");
    }
    function allerHaut(login) {
        const data = new URLSearchParams({
            x_old: img_selectionne_x,
            y_old: img_selectionne_y,
            x_new: img_selectionne_x-1,
            y_new: img_selectionne_y,
            login: login
        });
        callServlet(data, "/move");
    }
    function allerBas(login) {
        const data = new URLSearchParams({
            x_old: img_selectionne_x,
            y_old: img_selectionne_y,
            x_new: img_selectionne_x+1,
            y_new: img_selectionne_y,
            login: login
        });
        callServlet(data, "/move");
    }

    function seSoigner(login){
        const data = new URLSearchParams({
            x: img_selectionne_x,
            y: img_selectionne_y,
            login: login
        });
        callServlet(data, "/soigner");
    }

    function passerTour(login){
        const data = new URLSearchParams({
            login: login
        });
        callServlet(data, "/pass");
    }
    function recruter(login){
        const data = new URLSearchParams({
            login: login
        });
        callServlet(data, "/recruter");
    }
    function pollServer() {
        <%if (!joueurTour.getLogin().equals(joueurConnecte.getLogin())){%>
        location.reload()
        <%}%>
    }
    // Polling chaque 5 secondes
    setInterval(pollServer, 5000);
</script>
</body>
</html>
