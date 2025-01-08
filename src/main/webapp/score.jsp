<%@ page import="java.util.Dictionary" %>
<%@ page import="com.mysql.cj.xdevapi.JsonArray" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Score de la partie</title>
</head>
<body>
<%
    Map<String, Integer> scores = (Map<String, Integer>) request.getAttribute("scores");
%>
<h2>Scores</h2>
<%for (String j : scores.keySet()){%>
    <p><%=j%> : <%=scores.get(j)%></p>
<%}%>
<a href="${pageContext.request.contextPath}/accueil">Accueil</a>
</body>
</html>
