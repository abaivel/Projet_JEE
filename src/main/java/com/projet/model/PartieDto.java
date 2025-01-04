package com.projet.model;

import java.util.ArrayList;
import java.util.List;

public class PartieDto {
    private Carte carte = new Carte();
    private static PartieDto partieDto;
    private List<JoueurDto> joueurs;
    private JoueurDto joueurTour;

    public PartieDto() {
        joueurs = new ArrayList<JoueurDto>();
    }

    public void addJoueur(JoueurDto joueur){
        if (joueurs.isEmpty()) {
            joueurTour = joueur;
        }
        joueurs.add(joueur);
        int xRandom = 0;
        int yRandom = 0;
        do {
            xRandom = (int) (Math.random() * 10);
            yRandom = (int) (Math.random() * 10);
        }while (!carte.IsTuileOccupable(xRandom, yRandom, joueur.getLogin()));
        carte.setTuileSoldat(xRandom,yRandom,new Soldat(xRandom,yRandom, 10, joueur));
    }

    public List<JoueurDto> getJoueurs(){
        return this.joueurs;
    }

    public Carte getCarte() {
        return this.carte;
    }

    public static PartieDto getPartieDto(){
        if (partieDto == null){
            partieDto = new PartieDto();
            partieDto.carte = new Carte();
        }
        return partieDto;
    }

    public void nouvellePartieDto(){
        partieDto = null;
    }

    public JoueurDto tourSuivant(){
        if (!joueurs.isEmpty()) {
            if (joueurTour == null) {
                return joueurs.get(0);
            }
            int indexTour = joueurs.indexOf(this.joueurTour);
            if (indexTour<joueurs.size() - 1) {
                indexTour++;
            }else{
                indexTour = 0;
            }
            joueurTour = joueurs.get(indexTour);
            return joueurTour;
        }else{
            return null;
        }
    }

    public JoueurDto getJoueurTour() {
        return joueurTour;
    }

    public void setJoueurTour(JoueurDto joueurTour) {
        this.joueurTour = joueurTour;
    }
}
