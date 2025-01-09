package com.projet.controller;

import com.projet.service.PartieService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppStartupListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        PartieService partieService = new PartieService();
        partieService.finirPartie();
    }
}
