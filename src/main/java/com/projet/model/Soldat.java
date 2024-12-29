package com.projet.model;

public class Soldat {
    private int x;
    private int y;
    private int points_defence;
    private Joueur proprietaire;

    public Joueur getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPoints_defence() {
        return points_defence;
    }

    public void setPoints_defence(int points_defence) {
        this.points_defence = points_defence;
    }
}
