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

    public void emptyGrille() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grille[i][j].setElement(null);
                grille[i][j].setSoldat(null);
            }
        }
    }

    public void setGrilleAleatoire(){
        emptyGrille();
        // On ajoute 10 Forets à la grille
        for (int i = 0; i<10;i++){
            int xRandom = 0;
            int yRandom = 0;
            do {
                xRandom = (int) (Math.random() * 10);
                yRandom = (int) (Math.random() * 10);
            }while (!IsTuileEmpty(xRandom, yRandom));
            grille[xRandom][yRandom].setElement(new Foret(xRandom, yRandom, (int) (Math.random() * 10)));
        }
        // On ajoute 5 montagnes à la grille
        for (int i = 0; i<5;i++){
            int xRandom = 0;
            int yRandom = 0;
            do {
                xRandom = (int) (Math.random() * 10);
                yRandom = (int) (Math.random() * 10);
            }while (!IsTuileEmpty(xRandom, yRandom));
            grille[xRandom][yRandom].setElement(new Montagne(xRandom, yRandom));
        }
        // On ajoute 5 villes à la grille
        for (int i = 0; i<5;i++){
            int xRandom = 0;
            int yRandom = 0;
            do {
                xRandom = (int) (Math.random() * 10);
                yRandom = (int) (Math.random() * 10);
            }while (!IsTuileEmpty(xRandom, yRandom));
            grille[xRandom][yRandom].setElement(new Ville(xRandom, yRandom, (int) (Math.random() * 10)+5, (int) (Math.random() * 10)+1));
        }
    }

    public boolean IsTuileEmpty(int x, int y){
        return grille[x][y].getElement()==null && grille[x][y].getSoldat()==null;
    }
    public boolean NoSoldat(int x, int y){
        return grille[x][y].getSoldat()==null && !(grille[x][y].getElement() instanceof Montagne);
    }


    public boolean IsTuileOccupable(int x, int y, String login){
        Tuile t = grille[x][y];
        if (t.getSoldat() != null) {
            if (t.getSoldat().getProprietaire().getLogin().equals(login)){
                return false;
            }
        }
        if (t.getElement() != null) {
            if (t.getElement() instanceof Montagne){
                return false;
            }
        }
        return true;
    }

    public Tuile IsThereEnnemiVille(int x, int y, String login){
        if (grille[x][y].getElement()!=null){
            if (grille[x][y].getElement() instanceof Ville){
                if (((Ville) grille[x][y].getElement()).getProprietaire()==null || !((Ville) grille[x][y].getElement()).getProprietaire().getLogin().equals(login)){
                    return grille[x][y];
                }
            }
        }
        return null;
    }

    public Tuile IsThereSoldatEnnemi(int x, int y, String login){
        if (grille[x][y].getSoldat()!=null){
            if (!grille[x][y].getSoldat().getProprietaire().getLogin().equals(login)){
                return grille[x][y];
            }
        }
        return null;
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
    public boolean addSoldat(Soldat soldat) {
        int tailleX = grille.length;
        int tailleY = grille[0].length;

        // Choisir une case initiale au hasard
        int startX = (int) (Math.random() * tailleX);
        int startY = (int) (Math.random() * tailleY);

        // Parcourir toutes les cases en commençant par la case aléatoire
        for (int i = 0; i < tailleX; i++) {
            for (int j = 0; j < tailleY; j++) {
                int x = (startX + i) % tailleX; // Gérer le débordement pour revenir au début
                int y = (startY + j) % tailleY;

                if (NoSoldat(x, y)) {
                    grille[x][y].setSoldat(soldat);
                    return true; // Soldat placé avec succès
                }
            }
        }

        return false;
    }

}
