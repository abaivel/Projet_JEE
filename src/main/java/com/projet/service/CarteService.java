package com.projet.service;

import com.projet.model.Carte;
import com.projet.model.JoueurDto;
import com.projet.model.PartieDto;
import com.projet.model.Tuile.Tuile;

public class CarteService {
    public static Tuile[][] getCarte(){
        PartieDto p = PartieDto.getPartieDto();
        return p.getCarte().getGrille();
    }

    public static void addJoueur(String login){
        PartieDto p = PartieDto.getPartieDto();
        JoueurDto j = new JoueurDto(login);
        p.addJoueur(j);
    }
}
