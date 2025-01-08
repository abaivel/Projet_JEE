package com.projet.model.DAO;

import com.projet.model.JPA.Joueur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class JoueurDAO {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("project");
    private EntityManager entityManager;

    public JoueurDAO() {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public Joueur getJoueurByLogin(String login) {
        String query = "select j from Joueur j where j.login = :login";

        List<Joueur> joueurs = entityManager.createQuery(query, Joueur.class).setParameter("login", login).getResultList();
        return joueurs.isEmpty() ? null : joueurs.get(0);
    }

    public Joueur createJoueur(String login, String password) {
        entityManager.getTransaction().begin();
        Joueur newJoueur = new Joueur();
        newJoueur.setLogin(login);
        newJoueur.setMdp(password);
        entityManager.persist(newJoueur);
        entityManager.getTransaction().commit();
        return newJoueur;
    }
}

