package com.projet.model;

public class Ville extends Tuile {
    private int points_defense;
    private Joueur proprietaire;

    public Joueur getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }

    public int getPoints_defense() {
        return points_defense;
    }

    public void setPoints_defense(int points_defense) {
        this.points_defense = points_defense;
    }
}
