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

import javax.swing.text.StyledEditorKit;
import java.io.IOException;
import java.util.List;

@WebServlet("/recruter")
public class RecruterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        JoueurDto joueurConnecte = (JoueurDto) session.getAttribute("joueurConnecte");

        boolean res = CarteService.addSoldat(joueurConnecte.getLogin());
        List<JoueurDto> joueurs = CarteService.getJoueurs();
        if (res) {
            Tuile[][] grille = CarteService.getCarte();
            req.setAttribute("joueur", joueurConnecte);
            req.setAttribute("joueurTour", joueurConnecte);
            req.setAttribute("grille", grille);
            req.setAttribute("joueurs", joueurs);
            resp.setStatus(200);
        }else{
            resp.setStatus(400);
        }
        this.getServletContext().getRequestDispatcher("/game.jsp").forward(req, resp);
    }
}
