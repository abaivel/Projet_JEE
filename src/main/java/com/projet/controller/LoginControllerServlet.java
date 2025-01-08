package com.projet.controller;

import com.projet.model.JoueurDto;
import com.projet.service.JoueurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/accueil")
public class LoginControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        JoueurDto joueurConnecte = (JoueurDto) session.getAttribute("joueurConnecte");

        if (joueurConnecte == null) {
            // Redirection si la session a expiré ou si l'utilisateur n'est pas connecté
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            // Continuer si l'utilisateur est connecté
            req.setAttribute("joueur", joueurConnecte);
            this.getServletContext().getRequestDispatcher("/accueil.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String mdp = req.getParameter("mdp");

        if (login == null || mdp == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            JoueurDto res = (new JoueurService()).testLogin(login, mdp);

            if (res != null) {
                // Ajouter le joueur connecté à la session
                HttpSession session = req.getSession();
                session.setAttribute("joueurConnecte", res);

                // Transmettre également à la requête pour cette redirection
                req.setAttribute("joueur", res);

                // Redirection vers l'accueil
                this.getServletContext().getRequestDispatcher("/accueil.jsp").forward(req, resp);
            } else {
                // Retourner à la page de connexion avec un message d'erreur
                req.setAttribute("erreur", true);
                this.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        }
    }


}
