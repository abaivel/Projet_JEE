package com.projet.model;

import jakarta.persistence.*;

@Entity
@Table(name = "joueur_partie")
public class JoueurPartie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJoueurPartie;

    @OneToOne
    @JoinColumn(name = "idJoueur")
    private Joueur joueur;

    @OneToOne
    @JoinColumn(name = "idPartie")
    private Partie partie;

    private int score;

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    public void setIdJoueurPartie(Long idJoueurPartie) {
        this.idJoueurPartie = idJoueurPartie;
    }

    public Long getIdJoueurPartie() {
        return idJoueurPartie;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
