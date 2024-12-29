package com.projet.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "joueur")
public class Joueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJoueur;
    private String login;
    private String mdp;

    public Joueur(String login, String mdp) {
        super();
        this.login = login;
        this.mdp = mdp;
    }

    public Joueur() {
        super();
    }

    public void setIdJoueur(Long id) {
        this.idJoueur = id;
    }

    public Long getIdJoueur() {
        return idJoueur;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
