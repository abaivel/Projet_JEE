package com.projet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Carte {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
