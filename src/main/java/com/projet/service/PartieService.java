package com.projet.service;

import com.projet.model.DAO.JoueurPartieDAO;
import com.projet.model.DAO.PartieDAO;
import com.projet.model.JPA.Joueur;
import com.projet.model.JPA.JoueurPartie;
import com.projet.model.JPA.Partie;
import com.projet.model.JoueurDto;

import java.util.*;

public class PartieService {
    private final PartieDAO partieDAO;
    private final JoueurPartieDAO joueurPartieDAO;

    public PartieService() {
        this.partieDAO = new PartieDAO();
        this.joueurPartieDAO = new JoueurPartieDAO();
    }

    public Partie getPartieActive(){
        Partie partie = null;
        partie = partieDAO.getPartieActive();
        if (partie == null) {
            partie = partieDAO.addPartie();
            CarteService.generateNewPartie();
        }
        return partie;
    }

    public boolean IsJoueurInPartie(Partie partie, Joueur joueur){
        return joueurPartieDAO.getJoueurPartieByPartieIdAndJoueurId(partie.getIdPartie(), joueur.getIdJoueur())!=null;
    }

    public void addJoueurToPartie(Partie partie, Joueur joueur) {
        joueurPartieDAO.addJoueurPartie(partie, joueur);
    }
    public JoueurDto addJoueurToPartieActiveIfNotInPartie(Joueur joueur){
        Partie partieActive = getPartieActive();
        if (!IsJoueurInPartie(partieActive, joueur)){
            addJoueurToPartie(partieActive, joueur);
            JoueurDto joueurDto = new JoueurDto(joueur.getLogin());
            CarteService.addJoueur(joueurDto);
            return joueurDto;
        }
        return CarteService.getJoueur(joueur.getLogin());
    }

    public void generateNewPartieDto(){
        CarteService.generateNewPartie();
        Partie partieActive = getPartieActive();
        if (partieActive != null){
            List<JoueurPartie> joueurParties = joueurPartieDAO.getJoueurPartieByPartieId(partieActive.getIdPartie());
            if (joueurParties!=null) {
                for (JoueurPartie jp : joueurParties) {
                    Joueur j = jp.getJoueur();
                    JoueurDto joueurDto = new JoueurDto(j.getLogin());
                    CarteService.addJoueur(joueurDto);
                    jp.setScore(0);
                }
            }
        }
    }

    public Map<String, Integer> finirPartie(){
        Map<String, Integer> dicoScores = new Hashtable<String, Integer>();
        Partie partieActive = getPartieActive();
        partieDAO.finishPartie(partieActive);
        List<JoueurPartie> joueurParties = joueurPartieDAO.getJoueurPartieByPartieId(partieActive.getIdPartie());
        for (JoueurPartie jp : joueurParties){
            JoueurDto j = CarteService.getJoueur(jp.getJoueur().getLogin());
            if (j!=null) {
                joueurPartieDAO.setScore(jp.getIdJoueurPartie(), j.getScore());
            }
            dicoScores.put(jp.getJoueur().getLogin(),jp.getScore());
        }
        return dicoScores;
    }

}
