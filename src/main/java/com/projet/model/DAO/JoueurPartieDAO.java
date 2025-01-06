package com.projet.model.DAO;

import com.projet.model.JPA.JoueurPartie;

import jakarta.persistence.*;

import java.util.List;

public class JoueurPartieDAO {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("project");
    private EntityManager entityManager;

    public JoueurPartieDAO() {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public List<JoueurPartie> getDerniersScores(int limite) {
        String jpql = "SELECT jp FROM JoueurPartie jp ORDER BY jp.score DESC";
        return entityManager.createQuery(jpql, JoueurPartie.class)
                .setMaxResults(limite)
                .getResultList();
    }

    public void close() {
        if (entityManager != null) {
            entityManager.close();
        }
    }

    public List<JoueurPartie> getScoresParJoueur(Long idJoueur) {
        String jpql = "SELECT jp FROM JoueurPartie jp WHERE jp.joueur.idJoueur = :idJoueur ORDER BY jp.partie.dateDebut DESC";
        return entityManager.createQuery(jpql, JoueurPartie.class)
                .setParameter("idJoueur", idJoueur)
                .getResultList();
    }

    public List<JoueurPartie> getScoresParLogin(String login) {
        String jpql = "SELECT jp FROM JoueurPartie jp WHERE jp.joueur.login = :login ORDER BY jp.partie.dateDebut DESC";
        return entityManager.createQuery(jpql, JoueurPartie.class)
                .setParameter("login", login)
                .getResultList();
    }
}
