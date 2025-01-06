package com.projet.controller;

import com.projet.model.DAO.JoueurPartieDAO;
import com.projet.model.JPA.Joueur;
import com.projet.model.JoueurDto;
import com.projet.service.ScoreService;
import com.projet.model.JPA.JoueurPartie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/recapScoresJoueur")
public class RecapScoresJoueurController extends HttpServlet {
    private JoueurPartieDAO joueurPartieDAO;

    @Override
    public void init() throws ServletException {
        joueurPartieDAO = new JoueurPartieDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        JoueurDto joueurConnecte = (JoueurDto) session.getAttribute("joueurConnecte");

        if (joueurConnecte == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        try {
            List<JoueurPartie> scores = joueurPartieDAO.getScoresParLogin(joueurConnecte.getLogin());
            req.setAttribute("scores", scores);
            req.getRequestDispatcher("/recapScoresJoueur.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des scores.");
        }
    }

    @Override
    public void destroy() {
        joueurPartieDAO.close();
    }
}