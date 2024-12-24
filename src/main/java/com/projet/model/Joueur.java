package com.projet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Set;

@Entity
public class Joueur {
    @Id
    private Long id;
    private String login;
    private int points_production;
    @OneToMany(mappedBy = "joueur")
    private Set<Soldat> unites;
    @OneToMany(mappedBy = "joueur")
    private Set<Ville> villes;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPoints_production() {
        return points_production;
    }

    public void setPoints_production(int points_production) {
        this.points_production = points_production;
    }

    public Set<Soldat> getUnites() {
        return unites;
    }

    public void setUnites(Set<Soldat> unites) {
        this.unites = unites;
    }

    public Set<Ville> getVilles() {
        return villes;
    }

    public void setVilles(Set<Ville> villes) {
        this.villes = villes;
    }
}
