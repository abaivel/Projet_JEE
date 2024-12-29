package com.projet.model;

public class Tuile {
    private int x;
    private int y;
    private Carte carte;
    private EnumTypeTuile type;

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
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

    public EnumTypeTuile getType() {
        return type;
    }

    public void setType(EnumTypeTuile type) {
        this.type = type;
    }

}
