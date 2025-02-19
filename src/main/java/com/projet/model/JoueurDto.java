package com.projet.model;

import com.projet.model.Element.Ville;

import java.util.ArrayList;
import java.util.List;

public class JoueurDto {
    private final String login;
    private int nbCombatGagne;
    private int nbCombatPerdu;
    private int points_production;
    private List<Soldat> soldats;
    private List<Ville> villes;

    public JoueurDto(String login) {
        this.login = login;
        nbCombatGagne = 0;
        nbCombatPerdu = 0;
        this.points_production = 0;
        this.soldats = new ArrayList<Soldat>();
        this.villes = new ArrayList<>();
    }

    public int getScore() {
        return nbCombatGagne * 5 - nbCombatPerdu*6 + villes.size()*10;
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

    public int getNbCombatGagne() {
        return nbCombatGagne;
    }

    public void setNbCombatGagne(int nbCombatGagne) {
        this.nbCombatGagne = nbCombatGagne;
    }

    public int getNbCombatPerdu() {
        return nbCombatPerdu;
    }

    public void setNbCombatPerdu(int nbCombatPerdu) {
        this.nbCombatPerdu = nbCombatPerdu;
    }
}
