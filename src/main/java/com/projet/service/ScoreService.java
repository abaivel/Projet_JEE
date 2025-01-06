package com.projet.service;

import com.projet.model.DAO.JoueurPartieDAO;
import com.projet.model.JPA.JoueurPartie;

import java.util.List;

public class ScoreService {
    private JoueurPartieDAO joueurPartieDAO;

    public ScoreService(JoueurPartieDAO joueurPartieDAO) {
        this.joueurPartieDAO = joueurPartieDAO;
    }

    public List<JoueurPartie> getScoresDesDerniersJoueurs(int limite) {
        return joueurPartieDAO.getDerniersScores(limite);
    }
}
