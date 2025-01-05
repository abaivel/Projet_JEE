package com.projet.controller;

import com.projet.model.JoueurDto;
import com.projet.model.Tuile;
import com.projet.service.CarteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/combat")
public class CombatControllerServlet extends HttpServlet {
    Tuile TuileAttaque = null;
    Tuile TuileSoldat  = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        JoueurDto joueurConnecte = (JoueurDto) session.getAttribute("joueurConnecte");
        int xTuile = Integer.parseInt(req.getParameter("xTuile"));
        int yTuile = Integer.parseInt(req.getParameter("yTuile"));
        int xSoldat = Integer.parseInt(req.getParameter("xSoldat"));
        int ySoldat = Integer.parseInt(req.getParameter("ySoldat"));
        Tuile tuileAttaque = CarteService.getTuile(xTuile, yTuile);
        Tuile tuileSoldat = CarteService.getTuile(xSoldat, ySoldat);
        TuileAttaque = tuileAttaque;
        TuileSoldat = tuileSoldat;
        req.setAttribute("joueurConnecte",joueurConnecte);
        req.setAttribute("tuileAttaque",tuileAttaque);
        req.setAttribute("tuileSoldat",tuileSoldat);
        resp.setStatus(200);
        this.getServletContext().getRequestDispatcher("/combat.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        JoueurDto joueurConnecte = (JoueurDto) session.getAttribute("joueurConnecte");
        int attaquePoints = Integer.parseInt(req.getParameter("tuileAttaquePoints"));
        int soldatPoints = Integer.parseInt(req.getParameter("soldatPoints"));
        CarteService.endCombat(TuileAttaque, TuileSoldat, attaquePoints, soldatPoints, joueurConnecte);
        CarteService.tourSuivant();
        resp.setStatus(200);
        resp.getWriter().write("{\"redirect\": \"game\"}");

    }
}
