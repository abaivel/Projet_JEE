package com.projet.service;

import com.projet.model.DAO.JoueurDAO;
import com.projet.model.JPA.Joueur;
import com.projet.model.JoueurDto;

public class JoueurService {
    private final PartieService partieService;
    private final JoueurDAO joueurDAO;

    public JoueurService() {
        this.partieService = new PartieService();
        this.joueurDAO = new JoueurDAO();
    }

    public JoueurDto testLogin(String login, String password) {
        boolean result = false;
        Joueur joueurconnecte = null;
        JoueurDto joueurdto = null;
        joueurconnecte =  joueurDAO.getJoueurByLogin(login);
        if (joueurconnecte == null) {
            joueurconnecte = joueurDAO.createJoueur(login, password);
            result = true;
        }else{
            result = joueurconnecte.getMdp().equals(password);
        }
        if (result){
            joueurdto = partieService.addJoueurToPartieActiveIfNotInPartie(joueurconnecte);
        }
        return joueurdto;
    }

    public void addJoueurToPartieActiveIfNotInPartie(String login) {
        Joueur joueur = joueurDAO.getJoueurByLogin(login);
        if (joueur != null) {
            partieService.addJoueurToPartieActiveIfNotInPartie(joueur);
        }
    }
}
