package com.projet.service;

import com.projet.model.Carte;
import com.projet.model.Element.Ville;
import com.projet.model.JoueurDto;
import com.projet.model.PartieDto;
import com.projet.model.*;
import com.projet.model.Element.Element;

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

    public static Tuile moveTuile(int oldX, int oldY, int newX, int newY, String login){
        PartieDto p = PartieDto.getPartieDto();
        Carte c = p.getCarte();
        Tuile tuile = c.getTuile(oldX, oldY);
        Soldat s = tuile.getSoldat();
        if (newX>=0 && newY>=0 && newX<10 && newY<10 && c.IsTuileOccupable(newX, newY, login)) {
            Tuile t = c.getTuile(oldX, oldY);
            t.setX(newX);
            t.setY(newY);
            t.getSoldat().setX(newX);
            t.getSoldat().setY(newY);
            c.setTuileSoldat(newX, newY, t.getSoldat());
            c.setTuileSoldat(oldX, oldY, null);
            s.setCanPlay(false);
            Tuile r = c.IsNextToSoldat(newX,newY,login);
            if (r!=null){
                return r;
            }
            Tuile v = c.IsNextToVille(newX, newY,login);
            if (v!=null){
                return v;
            }
        }
        return null;
    }

    public static JoueurDto tourSuivant(){
        PartieDto p = PartieDto.getPartieDto();
        return p.tourSuivant();
    }

    public static Tuile getTuile(int x, int y){
        PartieDto p = PartieDto.getPartieDto();
        return p.getCarte().getTuile(x, y);
    }

    public static void generateNewPartie(){
        PartieDto p = PartieDto.getPartieDto();
        p.getCarte().setGrilleAleatoire();
    }

    public static void endCombat(Tuile tuileAttaque, Tuile tuileSoldat, int pointsAttaque, int pointsSoldat, JoueurDto joueur){
        tuileSoldat.getSoldat().setPoints_defence(pointsSoldat);
        if (tuileSoldat.getSoldat().getPoints_defence() <=0){
            tuileSoldat.setSoldat(null);
        }
        if (tuileAttaque.getElement() instanceof Ville) {
            ((Ville) tuileAttaque.getElement()).setPoints_defense(pointsAttaque);
            if (((Ville) tuileAttaque.getElement()).getPoints_defense()<=0){
                ((Ville) tuileAttaque.getElement()).setProprietaire(joueur);
            }
        }

    }

    public static JoueurDto soignerSoldat(int x, int y, String login){
        PartieDto p = PartieDto.getPartieDto();
        Carte c = p.getCarte();
        Tuile tuile = c.getTuile(x, y);
        Soldat s = tuile.getSoldat();
        if (s.CanPlay()) {
            s.setPoints_defence(tuile.getSoldat().getPoints_defence() + 10);
            s.setCanPlay(false);
        }
        return p.getJoueurTour();
    }

    public static JoueurDto passerTour(String login){
        CarteService.getJoueur(login).resetSoldats();
        return PartieDto.getPartieDto().tourSuivant();
    }
    public static JoueurDto fourrage(int x, int y, String login){
        PartieDto p = PartieDto.getPartieDto();
        JoueurDto j = CarteService.getJoueur(login);
        Carte c = p.getCarte();
        Tuile tuile = c.getTuile(x, y);
        Soldat s = tuile.getSoldat();
        if (s.CanPlay()) {
            j.setPoints_production(j.getPoints_production() + 10);
            s.setCanPlay(false);
        }

        return p.getJoueurTour();
    }
    public static boolean addSoldat(String login){
        PartieDto p = PartieDto.getPartieDto();
        JoueurDto j = CarteService.getJoueur(login);
        Carte c = p.getCarte();
        assert j != null;
        if (j.getPoints_production()>=15){
            boolean res = c.addSoldat(new Soldat(0,0,10,j));
            if(res)
                j.setPoints_production(j.getPoints_production()-15);
            return res;
        }
        return true;
    }
}
