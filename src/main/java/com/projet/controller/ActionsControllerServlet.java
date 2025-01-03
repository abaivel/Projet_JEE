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

@WebServlet("/move")
public class ActionsControllerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JoueurDto j = CarteService.getJoueur(req.getParameter("login"));
        int xOld = Integer.parseInt(req.getParameter("x_old"));
        int yOld = Integer.parseInt(req.getParameter("y_old"));
        int xNew = Integer.parseInt(req.getParameter("x_new"));
        int yNew = Integer.parseInt(req.getParameter("y_new"));
        JoueurDto res = CarteService.moveTuile(xOld, yOld , xNew, yNew);
        if (res !=null) {
            Tuile[][] grille = CarteService.getCarte();
            req.setAttribute("joueur", j);
            req.setAttribute("joueurTour", res);
            req.setAttribute("grille", grille);
            resp.setStatus(200);
        }else{
            resp.setStatus(400);
        }
        this.getServletContext().getRequestDispatcher("/game.jsp").forward(req, resp);
    }
}
