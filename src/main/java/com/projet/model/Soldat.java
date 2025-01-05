package com.projet.model;

public class Soldat {
    private int x;
    private int y;
    private int points_defence;
    private JoueurDto proprietaire;

    public Soldat(int x, int y, int points_defence, JoueurDto proprietaire) {
        this.points_defence = points_defence;
        this.proprietaire = proprietaire;
        proprietaire.addSoldat(this);
    }

    public JoueurDto getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(JoueurDto proprietaire) {
        this.proprietaire = proprietaire;
        proprietaire.addSoldat(this);
    }

    public int getPoints_defence() {
        return points_defence;
    }

    public void setPoints_defence(int points_defence) {
        this.points_defence = points_defence;
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
}
