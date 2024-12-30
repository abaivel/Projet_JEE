<%@ page import="com.projet.model.Tuile.*" %><%--
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
<%Tuile[][] grille = (Tuile[][]) request.getAttribute("grille");%>
<table>
    <%for (int i=0;i<10;i++){%>
    <tr>
        <%for (int j=0;j<10;j++){%>
            <td><div>
            <%if (grille[i][j] != null){
                if (grille[i][j] instanceof Montagne){ %>
                    <img src="icons/Small/mountain.png">
                <%}else if (grille[i][j] instanceof Ville){%>
                    <img src="icons/Small/city.png">
                <%}else if (grille[i][j] instanceof Foret){%>
                    <img src="icons/Small/forest.png">
                <%}else if (grille[i][j] instanceof Soldat){%>
                    <img src="icons/Small/soldier.png">
                <%}
            }%>
            </div></td>
        <%}%>
    </tr>
    <%}%>
</table>
<div class="buttons-actions">
    <div class="div-buttons-deplacements">
        <button class="button-deplacement button-deplacement-haut"><img src="icons/fleche_haut.png"></button>
        <button class="button-deplacement button-deplacement-gauche"><img src="icons/fleche_gauche.png"></button>
        <button class="button-deplacement button-deplacement-droite"><img src="icons/fleche_droite.png"></button>
        <button class="button-deplacement button-deplacement-bas"><img src="icons/fleche_bas.png"></button>
    </div>
    <div class="div-others-buttons">
        <button class="other-button">Se soigner</button>
        <button class="other-button">Fourrager</button>
    </div>
</div>
</body>
</html>
