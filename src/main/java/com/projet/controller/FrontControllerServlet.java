package com.projet.controller;

import com.projet.model.JoueurDto;
import com.projet.model.Tuile.Tuile;
import com.projet.service.CarteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/game")
public class FrontControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        JoueurDto joueurConnecte = (JoueurDto) session.getAttribute("joueurConnecte");
        JoueurDto joueurTour = CarteService.getJoueurTour();
        Tuile[][] grille = CarteService.getCarte();
        List<JoueurDto> joueurs = CarteService.getJoueurs();
        req.setAttribute("joueur", joueurConnecte);
        req.setAttribute("joueurTour", joueurTour);
        req.setAttribute("grille", grille);
        req.setAttribute("joueurs", joueurs);
        resp.setStatus(200);
        this.getServletContext().getRequestDispatcher("/game.jsp").forward(req, resp);
    }
}
