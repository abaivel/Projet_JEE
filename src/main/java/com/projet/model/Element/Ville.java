package com.projet.model.Element;

import com.projet.model.JoueurDto;

public class Ville extends Element {
    private int points_defense;
    private JoueurDto proprietaire;

    public Ville(int x, int y, int points_defense) {
        super(x, y);
        this.points_defense = points_defense;
        this.proprietaire = null;
    }

    public JoueurDto getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(JoueurDto proprietaire) {
        this.proprietaire = proprietaire;
    }

    public int getPoints_defense() {
        return points_defense;
    }

    public void setPoints_defense(int points_defense) {
        this.points_defense = points_defense;
    }
}
