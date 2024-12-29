package com.projet.controller;

import com.projet.service.PlayerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginControllerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String mdp = req.getParameter("mdp");
        if (login==null || mdp==null){
            resp.setStatus(400);
        }else{
            boolean res = PlayerService.testLogin(login, mdp);
            if(res){
                this.getServletContext().getRequestDispatcher("/game.jsp").forward(req, resp);
            }else{
                req.setAttribute("erreur",true);
                this.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        }
    }
}
