<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html style="width: 100%; height: 100%; margin: 0; overflow: hidden">
<head>
    <title>Connexion</title>
</head>
<body style="background-image: url(images/fond_ecran_connexion.jpeg); background-repeat: no-repeat; background-size: cover;width: 100%; height: 100%; margin: 0; overflow: hidden">
<div style="background-color: #ffffff8a;width: 100%; height: 100%;margin: 0;align-content: center;">
    <div>
        <h1 style="text-align: center;margin: 0 0 20px 0;">Bienvenue sur ce jeu</h1>
        <div style="box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;border-radius: 15px; background-color: white; width: 30%; margin: auto; padding: 1%;">
            <h3 style="text-align: center;margin:0">Inscription/Connexion</h3>
            <form action="${pageContext.request.contextPath}/accueil" method="post">
                <label for="login" style="font-size: 15px;">Login</label>
                <br>
                <input type="text" id="login" name="login" required style="width: 97%;border-radius: 15px;height: 20px;padding: 2px 5px;font-size: 15px;border-color: #000000;font-family: 'Times New Roman',serif;">
                <br>
                <br>
                <label for="mdp" style="font-size: 15px;">Mot de passe</label>
                <br>
                <input type="password" id="mdp" name="mdp" required style="width: 97%;border-radius: 15px;height: 20px;padding: 2px 5px;font-size: 15px;border-color: #000000;font-family: 'Times New Roman',serif;">
                <br>
                <br>
                <input type="submit" value="Se connecter" style="margin:0% 35%;font-size: 15px;border-radius: 15px;padding: 2px;width: 30%;background-color: #ffffff;border-color: #000000;font-family: 'Times New Roman',serif;">
                <% if (request.getAttribute("erreur")!=null && (boolean)request.getAttribute("erreur")) { %>
                    <p style="color: red">Le login est déjà utilisé ou le mot de passe est incorrect</p>
                <% } %>
            </form>
        </div>
    </div>
</div>

</body>
</html>