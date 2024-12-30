package com.projet.controller;

import com.projet.model.Tuile.Tuile;
import com.projet.service.CarteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class FrontControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        Tuile[][] grille = CarteService.getCarte();
        req.setAttribute("grille", grille);
        this.getServletContext().getRequestDispatcher("/game.jsp").forward(req, resp);
    }
}
