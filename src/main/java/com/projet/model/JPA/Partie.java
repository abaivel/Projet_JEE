package com.projet.model.JPA;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "partie")
public class Partie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPartie;
    private Date dateDebut;
    private Date dateFin;

    public void setIdPartie(Long id) {
        this.idPartie = id;
    }

    public Long getIdPartie() {
        return idPartie;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}
