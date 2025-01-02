<%@ page import="com.projet.model.JoueurDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%JoueurDto j = (JoueurDto) request.getAttribute("joueur");%>




<a href="${pageContext.request.contextPath}/game?login=<%=j.getLogin()%>"><button>Partie</button></a>

<button>Recap des scores</button>

</body>
</html>
