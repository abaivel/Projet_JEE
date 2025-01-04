package com.projet.model;


import com.projet.model.Element.Foret;
import com.projet.model.Element.Montagne;
import com.projet.model.Element.Element;
import com.projet.model.Element.Ville;


public class Carte {
    public Tuile[][] grille = new Tuile[10][10];

    public Carte() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grille[i][j] = new Tuile(i,j);
            }
        }
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
            }while (!IsTuileEmpty(xRandom, yRandom));
            grille[xRandom][yRandom].setElement(new Foret(xRandom, yRandom, (int) (Math.random() * 10)));
        }
        for (int i = 0; i<5;i++){
            int xRandom = 0;
            int yRandom = 0;
            do {
                xRandom = (int) (Math.random() * 10);
                yRandom = (int) (Math.random() * 10);
            }while (!IsTuileEmpty(xRandom, yRandom));
            grille[xRandom][yRandom].setElement(new Montagne(xRandom, yRandom));
        }
        for (int i = 0; i<5;i++){
            int xRandom = 0;
            int yRandom = 0;
            do {
                xRandom = (int) (Math.random() * 10);
                yRandom = (int) (Math.random() * 10);
            }while (!IsTuileEmpty(xRandom, yRandom));
            grille[xRandom][yRandom].setElement(new Ville(xRandom, yRandom, (int) (Math.random() * 10)));
        }
    }

    public boolean IsTuileEmpty(int x, int y){
        return grille[x][y].getElement()==null && grille[x][y].getSoldat()==null;
    }

    public boolean IsTuileOccupable(int x, int y, String login){
        Tuile t = grille[x][y];
        if (t.getSoldat() != null){
            return false;
        }else if (t.getElement()==null){
            return true;
        }else if (t.getElement() instanceof Foret){
            return true;
        }else if (t.getElement() instanceof Ville){
            if (((Ville) t.getElement()).getProprietaire()==null){
                return false;
            }else if (((Ville) t.getElement()).getProprietaire().getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }

    public Tuile getTuile(int x, int y) {
        return grille[x][y];
    }

    public void setTuileElement(int x, int y, Element element) {
        grille[x][y].setElement(element);
    }
    public void setTuileSoldat(int x, int y, Soldat soldat) {
        grille[x][y].setSoldat(soldat);
    }
}
