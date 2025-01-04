package com.projet.service;

import com.projet.model.Carte;
import com.projet.model.JoueurDto;
import com.projet.model.PartieDto;
import com.projet.model.Element.Element;
import com.projet.model.Tuile;

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

    public static List<JoueurDto> getJoueurs(){
        PartieDto p = PartieDto.getPartieDto();
        return p.getJoueurs();
    }

    public static JoueurDto getJoueurTour(){
        PartieDto p = PartieDto.getPartieDto();
        return p.getJoueurTour();
    }

    public static JoueurDto moveTuile(int oldX, int oldY, int newX, int newY, String login){
        PartieDto p = PartieDto.getPartieDto();
        Carte c = p.getCarte();
        if (newX>=0 && newY>=0 && newX<10 && newY<10 && c.IsTuileOccupable(newX, newY, login)) {
            Tuile t = c.getTuile(oldX, oldY);
            t.setX(newX);
            t.setY(newY);
            t.getSoldat().setX(newX);
            t.getSoldat().setY(newY);
            c.setTuileSoldat(newX, newY, t.getSoldat());
            c.setTuileSoldat(oldX, oldY, null);
            return p.tourSuivant();
        }
        return null;
    }
}
