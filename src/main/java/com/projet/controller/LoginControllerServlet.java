package com.projet.controller;

import com.projet.model.JoueurDto;
import com.projet.service.JoueurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/accueil")
public class LoginControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        JoueurDto joueurConnecte = (JoueurDto) session.getAttribute("joueurConnecte");
        req.setAttribute("joueur", joueurConnecte);
        this.getServletContext().getRequestDispatcher("/accueil.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String mdp = req.getParameter("mdp");
        if (login==null || mdp==null){
            resp.setStatus(400);
        }else{
            JoueurDto res = JoueurService.testLogin(login, mdp);
            if(res!=null){
                req.setAttribute("joueur", res);
                HttpSession session = req.getSession();
                session.setAttribute("joueurConnecte", res);
                this.getServletContext().getRequestDispatcher("/accueil.jsp").forward(req, resp);
            }else{
                req.setAttribute("erreur",true);
                this.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        }
    }


}
