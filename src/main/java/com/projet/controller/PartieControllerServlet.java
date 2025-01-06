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
        Map<String, Integer> dicoScores =  PartieService.finirPartie();
        PartieService.generateNewPartieDto();
        req.setAttribute("scores", dicoScores);
        this.getServletContext().getRequestDispatcher("/score.jsp").forward(req, resp);
    }
}
