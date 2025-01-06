package com.projet.controller;

import com.projet.model.DAO.JoueurPartieDAO;
import com.projet.service.ScoreService;
import com.projet.model.JPA.JoueurPartie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/recapScores")
public class RecapScoresController extends HttpServlet {
    private ScoreService scoreService;

    @Override
    public void init() throws ServletException {
        JoueurPartieDAO joueurPartieDAO = new JoueurPartieDAO();
        scoreService = new ScoreService(joueurPartieDAO);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int limite = 10; // Par exemple, les 10 derniers joueurs
            List<JoueurPartie> scores = scoreService.getScoresDesDerniersJoueurs(limite);
            req.setAttribute("scores", scores);
            req.getRequestDispatcher("/recapScores.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur est survenue");
        }
    }

}