package com.projet.model.Element;


public class Foret extends Element {
    private int quantite_production;

    public Foret(int x, int y, int quantite_production) {
        super(x, y);
        this.quantite_production = quantite_production;
    }

    public int getQuantite_production() {
        return quantite_production;
    }

    public void setQuantite_production(int quantite_production) {
        this.quantite_production = quantite_production;
    }
}
