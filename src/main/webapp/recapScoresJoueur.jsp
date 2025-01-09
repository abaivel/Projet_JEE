<%@ page import="com.projet.model.JPA.JoueurPartie" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Récapitulatif des Scores Joueur</title>
  <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <h1 style="text-align: center;">Vos scores passés</h1>
  <table class="table-recap">
    <thead>
    <tr>
      <th>#</th>
      <th>Partie</th>
      <th>Date de début</th>
      <th>Date de fin</th>
      <th>Score</th>
    </tr>
    </thead>
    <tbody>
    <%
      List<JoueurPartie> scores = (List<JoueurPartie>) request.getAttribute("scores");
      if (scores != null && !scores.isEmpty()) {
        int rank = 1;
        for (JoueurPartie joueurPartie : scores) {
    %>
    <tr>
      <td><%= rank++ %></td>
      <td>Partie <%= joueurPartie.getPartie().getIdPartie() %></td>
      <td><%= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.FRANCE).format(joueurPartie.getPartie().getDateDebut()) %></td>
      <td><%= joueurPartie.getPartie().getDateFin()!=null ? new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.FRANCE).format(joueurPartie.getPartie().getDateFin()) : "-" %></td>
      <td><%= joueurPartie.getScore() %></td>
    </tr>
    <%
      }
    } else {
    %>
    <tr>
      <td colspan="5">Aucun score disponible.</td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>
  <div style="text-align: center; margin-top: 20px;">
    <a href="${pageContext.request.contextPath}/accueil">
      <button class="other-button">Retour à l'accueil</button>
    </a>
  </div>
</div>
</body>
</html>