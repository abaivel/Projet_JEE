package com.projet.service;

import com.projet.model.Carte;
import com.projet.model.Element.Foret;
import com.projet.model.Element.Ville;
import com.projet.model.JoueurDto;
import com.projet.model.PartieDto;
import com.projet.model.*;

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
        if (s.CanPlay() && newX>=0 && newY>=0 && newX<10 && newY<10 && c.IsTuileOccupable(newX, newY, login)) {
            Tuile t = c.getTuile(oldX, oldY);
            Tuile r = c.IsThereSoldatEnnemi(newX,newY, login);
            if (r!=null){
                return r;
            }
            Tuile v = c.IsThereEnnemiVille(newX, newY,login);
            if (v!=null){
                return v;
            }
            t.setX(newX);
            t.setY(newY);
            t.getSoldat().setX(newX);
            t.getSoldat().setY(newY);
            c.setTuileSoldat(newX, newY, t.getSoldat());
            c.setTuileSoldat(oldX, oldY, null);
            s.setCanPlay(false);
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
        p.setCarte(new Carte());
    }

    public static Tuile endCombat(Tuile tuileAttaque, Tuile tuileSoldat, int pointsAttaque, int pointsSoldat, JoueurDto joueur){
        tuileSoldat.getSoldat().setPoints_defence(pointsSoldat);
        if (tuileSoldat.getSoldat().getPoints_defence() <=0){
            tuileSoldat.setSoldat(null);
            joueur.setNbCombatPerdu(joueur.getNbCombatPerdu()+1);
        }
        if (tuileAttaque.getSoldat() != null){
            tuileAttaque.getSoldat().setPoints_defence(pointsAttaque);
            if (tuileAttaque.getSoldat().getPoints_defence() <=0) {
                tuileAttaque.setSoldat(null);
                joueur.setNbCombatGagne(joueur.getNbCombatGagne()+1);
                return moveTuile(tuileSoldat.getX(), tuileSoldat.getY(), tuileAttaque.getX(), tuileAttaque.getY(), joueur.getLogin());
            }
        }
        else if (tuileAttaque.getElement()!=null && tuileAttaque.getElement() instanceof Ville) {
            ((Ville) tuileAttaque.getElement()).setPoints_defense(pointsAttaque);
            if (((Ville) tuileAttaque.getElement()).getPoints_defense()<=0){
                ((Ville) tuileAttaque.getElement()).setProprietaire(joueur);
                joueur.setNbCombatGagne(joueur.getNbCombatGagne()+1);
                return moveTuile(tuileSoldat.getX(), tuileSoldat.getY(), tuileAttaque.getX(), tuileAttaque.getY(), joueur.getLogin());
            }
        }
        return null;

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
        if (s.CanPlay() && tuile.getElement() instanceof Foret) {
            j.setPoints_production(j.getPoints_production() +((Foret) tuile.getElement()).getQuantite_production());
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
