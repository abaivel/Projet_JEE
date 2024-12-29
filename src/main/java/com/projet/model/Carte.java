package com.projet.model;


import com.projet.model.Tuile.Foret;
import com.projet.model.Tuile.Montagne;
import com.projet.model.Tuile.Tuile;
import com.projet.model.Tuile.Ville;

public class Carte {
    public Tuile[][] grille = new Tuile[10][10];

    public Carte() {
        setGrilleAleatoire();
    }

    public Tuile[][] getGrille() {
        return grille;
    }

    public void setGrille(Tuile[][] grille) {
        this.grille = grille;
    }

    public void setGrilleAleatoire(){
        for (int i = 0; i<10;i++){
            int xRandom = 0;
            int yRandom = 0;
            do {
                xRandom = (int) (Math.random() * 10);
                yRandom = (int) (Math.random() * 10);
            }while (!IsCaseEmpty(xRandom, yRandom));
            grille[xRandom][yRandom] = new Foret(xRandom, yRandom, (int) (Math.random() * 10));
        }
        for (int i = 0; i<5;i++){
            int xRandom = 0;
            int yRandom = 0;
            do {
                xRandom = (int) (Math.random() * 10);
                yRandom = (int) (Math.random() * 10);
            }while (!IsCaseEmpty(xRandom, yRandom));
            grille[xRandom][yRandom] = new Montagne(xRandom, yRandom);
        }
        for (int i = 0; i<5;i++){
            int xRandom = 0;
            int yRandom = 0;
            do {
                xRandom = (int) (Math.random() * 10);
                yRandom = (int) (Math.random() * 10);
            }while (!IsCaseEmpty(xRandom, yRandom));
            grille[xRandom][yRandom] = new Ville(xRandom, yRandom, (int) (Math.random() * 10));
        }
    }

    public boolean IsCaseEmpty(int x, int y){
        return grille[x][y]==null;
    }

    public void emptyGrille(){
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                grille[i][j] = null;
            }
        }
    }

    public Tuile getTuile(int x, int y) {
        return grille[x][y];
    }

    public void setTuile(int x, int y, Tuile tuile) {
        grille[x][y] = tuile;
    }
}
