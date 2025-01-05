package com.projet.controller;

import com.projet.model.JoueurDto;
import com.projet.model.Element.Element;
import com.projet.model.Tuile;
import com.projet.service.CarteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/move")
public class ActionsControllerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        JoueurDto joueurConnecte = (JoueurDto) session.getAttribute("joueurConnecte");
        int xOld = Integer.parseInt(req.getParameter("x_old"));
        int yOld = Integer.parseInt(req.getParameter("y_old"));
        int xNew = Integer.parseInt(req.getParameter("x_new"));
        int yNew = Integer.parseInt(req.getParameter("y_new"));
        JoueurDto res = CarteService.moveTuile(xOld, yOld , xNew, yNew, joueurConnecte.getLogin());
        List<JoueurDto> joueurs = CarteService.getJoueurs();
        if (res !=null) {
            Tuile[][] grille = CarteService.getCarte();
            req.setAttribute("joueur", joueurConnecte);
            req.setAttribute("joueurTour", res);
            req.setAttribute("grille", grille);
            req.setAttribute("joueurs", joueurs);
            resp.setStatus(200);
        }else{
            resp.setStatus(400);
        }
        this.getServletContext().getRequestDispatcher("/game.jsp").forward(req, resp);
    }
}
