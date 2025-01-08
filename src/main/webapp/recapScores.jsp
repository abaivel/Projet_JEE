<%@ page import="com.projet.model.JPA.JoueurPartie" %>
<%@ page import="java.util.List" %>
<%@ page import="com.projet.model.JoueurDto" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Récapitulatif des Scores</title>
  <style>
    /* Style spécifique pour le tableau de recapScores.jsp */
    .table-recap {
      width: 90%; /* Occupe 90% de la largeur de la page */
      margin: 20px auto; /* Centré avec un espacement vertical */
      font-size: 18px; /* Texte plus grand */
      border-collapse: collapse; /* Réduire les bordures */
      background-color: #f9f9f9; /* Fond doux */
      box-shadow: 0 6px 10px rgba(0, 0, 0, 0.15); /* Ajouter une ombre */
    }

    .table-recap th, .table-recap td {
      padding: 15px; /* Espacement interne des cellules */
      text-align: left; /* Texte aligné à gauche */
    }

    .table-recap th {
      background-color: #96937d; /* Couleur de fond pour les en-têtes */
      color: white; /* Texte blanc */
      text-transform: uppercase; /* Texte en majuscules */
    }

    .table-recap tr:nth-child(even) {
      background-color: #f2f2f2; /* Fond alternatif pour les lignes */
    }

    .other-button{
      border-radius: 15px;
      padding: 5px;
      font-size: 15px;
    }

    .other-button:hover {
      background-color: #dcd9d4;
    }

  </style>
</head>
<body>
<div class="container">
  <h1 style="text-align: center;">Récapitulatif des Scores</h1>
  <table class="table-recap">
    <thead>
    <tr>
      <th>#</th>
      <th>Joueur</th>
      <th>Score</th>
    </tr>
    </thead>
    <tbody>
    <%
      Map<String, Integer> scores = (Map<String, Integer>) request.getAttribute("scores");
      if (scores != null && !scores.isEmpty()) {
        int rank = 1;
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
    %>
    <tr>
    <tr>
      <td><%= rank++ %></td>
      <td><%= entry.getKey() %></td>
      <td><%= entry.getValue() %></td>
    </tr>
    <%
      }
    } else {
    %>
    <tr>
      <td colspan="3">Aucun score disponible.</td>
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