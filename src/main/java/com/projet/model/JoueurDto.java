package com.projet.model;

import com.projet.model.Element.Ville;

import java.util.ArrayList;
import java.util.List;

public class JoueurDto {
    private String login;
    private int score;
    private int points_production;
    private List<Soldat> soldats;
    private List<Ville> villes;

    public JoueurDto(String login) {
        this.login = login;
        this.score = 0;
        this.points_production = 0;
        this.soldats = new ArrayList<Soldat>();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPoints_production() {
        return points_production;
    }

    public void setPoints_production(int points_production) {
        this.points_production = points_production;
    }

    public List<Soldat> getSoldats() {
        return soldats;
    }

    public void setSoldats(List<Soldat> soldats) {
        this.soldats = soldats;
    }

    public void addSoldat(Soldat soldat) {
        this.soldats.add(soldat);
    }

    public void removeSoldat(Soldat soldat) {
        this.soldats.remove(soldat);
    }

    public List<Ville> getVilles() {
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }

    public void addVille(Ville ville) {
        this.villes.add(ville);
    }
    public void removeVille(Ville ville) {
        this.villes.remove(ville);
    }

    public String getLogin() {
        return login;
    }
    public void resetSoldats() {
        for (Soldat soldat : soldats) {
            soldat.setCanPlay(true);
        }
    }
}
