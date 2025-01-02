package com.projet.service;

import com.projet.model.Carte;
import com.projet.model.JPA.Joueur;
import com.projet.model.JoueurDto;
import com.projet.model.PartieDto;
import com.projet.model.Tuile.Tuile;

import java.util.List;

public class CarteService {
    public static Tuile[][] getCarte(){
        PartieDto p = PartieDto.getPartieDto();
        return p.getCarte().getGrille();
    }

    public static void addJoueur(JoueurDto joueur){
        PartieDto p = PartieDto.getPartieDto();
        p.addJoueur(joueur);
    }

    public static JoueurDto getJoueur(String login){
        PartieDto p = PartieDto.getPartieDto();
        List<JoueurDto> joueurs= p.getJoueurs();
        for (JoueurDto joueur : joueurs) {
            if (joueur.getLogin().equals(login)) {
                return joueur;
            }
        }
        return null;
    }

    public static JoueurDto getJoueurTour(){
        PartieDto p = PartieDto.getPartieDto();
        return p.getJoueurTour();
    }

    public static JoueurDto moveTuile(int oldX, int oldY, int newX, int newY){
        PartieDto p = PartieDto.getPartieDto();
        Carte c = p.getCarte();
        if (newX>=0 && newY>=0 && newX<10 && newY<10 && c.getTuile(newX, newY) == null) {
            Tuile t = c.getTuile(oldX, oldY);
            t.setX(newX);
            t.setY(newY);
            c.setTuile(newX, newY, t);
            c.setTuile(oldX, oldY, null);
            JoueurDto jTour = p.tourSuivant();
            return jTour;
        }
        return null;
    }
}
