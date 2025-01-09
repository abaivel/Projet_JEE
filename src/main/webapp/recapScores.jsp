<%@ page import="com.projet.model.JPA.JoueurPartie" %>
<%@ page import="java.util.List" %>
<%@ page import="com.projet.model.JoueurDto" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Récapitulatif des Scores</title>
  <link href="css/style.css" rel="stylesheet">
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