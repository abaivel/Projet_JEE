package com.projet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Soldat {
    @Id
    private Long id;
    private int x;
    private int y;
    private int points_defence;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPoints_defence() {
        return points_defence;
    }

    public void setPoints_defence(int points_defence) {
        this.points_defence = points_defence;
    }
}
