package com.projet.model.Tuile;

import com.projet.model.JPA.Joueur;
import com.projet.model.JoueurDto;

public class Soldat extends Tuile {
    private int points_defence;
    private JoueurDto proprietaire;

    public Soldat(int x, int y, int points_defence, JoueurDto proprietaire) {
        super(x, y);
        this.points_defence = points_defence;
        this.proprietaire = proprietaire;
    }

    public JoueurDto getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(JoueurDto proprietaire) {
        this.proprietaire = proprietaire;
    }

    public int getPoints_defence() {
        return points_defence;
    }

    public void setPoints_defence(int points_defence) {
        this.points_defence = points_defence;
    }
}
