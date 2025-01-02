package com.projet.controller;

import com.projet.model.JoueurDto;
import com.projet.model.Tuile.Tuile;
import com.projet.service.CarteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/game")
public class FrontControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        JoueurDto j = CarteService.getJoueur(login);
        JoueurDto joueurTour = CarteService.getJoueurTour();
        Tuile[][] grille = CarteService.getCarte();
        List<JoueurDto> joueurs = CarteService.getJoueurs();
        req.setAttribute("joueur", j);
        req.setAttribute("joueurTour", joueurTour);
        req.setAttribute("grille", grille);
        req.setAttribute("joueurs", joueurs);

        this.getServletContext().getRequestDispatcher("/game.jsp").forward(req, resp);
    }
}
