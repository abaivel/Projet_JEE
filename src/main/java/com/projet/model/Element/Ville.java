package com.projet.model.Element;

import com.projet.model.JoueurDto;

public class Ville extends Element {
    private int points_defense;
    private int points_production;
    private JoueurDto proprietaire;

    public Ville(int x, int y, int points_defense, int points_production) {
        super(x, y);
        this.points_defense = points_defense;
        this.proprietaire = null;
        this.points_production = points_production;
    }

    public JoueurDto getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(JoueurDto proprietaire) {
        this.proprietaire = proprietaire;
        if (proprietaire!=null) {
            proprietaire.addVille(this);
        }
    }

    public int getPoints_defense() {
        return points_defense;
    }

    public void setPoints_defense(int points_defense) {
        this.points_defense = points_defense;
    }

    public int getPoints_production() {
        return points_production;
    }
}
