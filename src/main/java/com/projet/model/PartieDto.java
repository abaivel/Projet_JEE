package com.projet.model;

import com.projet.model.Tuile.Soldat;

import java.util.ArrayList;
import java.util.List;

public class PartieDto {
    private Carte carte;
    private static PartieDto partieDto;
    private List<JoueurDto> joueurs;

    public PartieDto() {
        joueurs = new ArrayList<JoueurDto>();
    }

    public void addJoueur(JoueurDto joueur){
        joueurs.add(joueur);
        int xRandom = 0;
        int yRandom = 0;
        do {
            xRandom = (int) (Math.random() * 10);
            yRandom = (int) (Math.random() * 10);
        }while (!carte.IsCaseEmpty(xRandom, yRandom));
        carte.setTuile(xRandom,yRandom,new Soldat(xRandom,yRandom, 10, joueur));
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
}
