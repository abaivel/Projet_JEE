package com.projet.controller;

import com.projet.service.PartieService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Map;

@WebServlet("/finPartie")
public class PartieControllerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PartieService partieService = new PartieService();
        Map<String, Integer> dicoScores =  partieService.finirPartie();
        partieService.generateNewPartieDto();
        req.setAttribute("scores", dicoScores);
        this.getServletContext().getRequestDispatcher("/recapScores.jsp").forward(req, resp);
    }
}
