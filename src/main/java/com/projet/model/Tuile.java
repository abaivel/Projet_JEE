package com.projet.model;

import com.projet.model.Element.Element;

public class Tuile {
    private int x;
    private int y;
    private Soldat soldat;
    private Element element;

    public Tuile(int x, int y) {
        this.x = x;
        this.y = y;
        soldat = null;
        element = null;
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

    public Soldat getSoldat() {
        return soldat;
    }

    public void setSoldat(Soldat soldat) {
        this.soldat = soldat;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
}
