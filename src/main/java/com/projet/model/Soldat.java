package com.projet.model;

public class Soldat {
    private int points_defense;
    private JoueurDto proprietaire;
    private final int PVMax;

    public boolean CanPlay() {
        return canPlay;
    }

    public void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
    }

    private boolean canPlay;

    public Soldat(int x, int y, int points_defense, JoueurDto proprietaire) {
        this.points_defense = points_defense;
        this.proprietaire = proprietaire;
        proprietaire.addSoldat(this);
        this.PVMax = points_defense;
        this.canPlay = true;
        proprietaire.addSoldat(this);
    }

    public JoueurDto getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(JoueurDto proprietaire) {
        this.proprietaire = proprietaire;
        proprietaire.addSoldat(this);
    }

    public int getPoints_defense() {
        return points_defense;
    }

    public void setPoints_defense(int points_defense) {
        if(points_defense <= this.PVMax) {
            this.points_defense = points_defense;
        }else{
            this.points_defense = this.PVMax;
        }
    }
    public boolean isFullLife() {return (this.points_defense == this.PVMax);}

}
