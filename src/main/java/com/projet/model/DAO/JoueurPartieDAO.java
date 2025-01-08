package com.projet.model.DAO;

import com.projet.model.JPA.Joueur;
import com.projet.model.JPA.JoueurPartie;

import com.projet.model.JPA.Partie;
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

    public JoueurPartie getJoueurPartieByPartieIdAndJoueurId(long partieId, long joueurId) {
        String query = "select jp from JoueurPartie jp where jp.partie.idPartie=:idPartie and jp.joueur.idJoueur=:idJoueur";

        List<JoueurPartie> joueurParties = entityManager.createQuery(query, JoueurPartie.class).setParameter("idPartie", partieId).setParameter("idJoueur", joueurId).getResultList();
        return !joueurParties.isEmpty() ? joueurParties.get(0) : null;
    }

    public void addJoueurPartie(Partie partie, Joueur joueur) {
        entityManager.getTransaction().begin();
        JoueurPartie joueurPartie = new JoueurPartie();
        joueurPartie.setJoueur(joueur);
        joueurPartie.setPartie(partie);
        joueurPartie.setScore(0);
        entityManager.persist(joueurPartie);
        entityManager.getTransaction().commit();
    }

    public List<JoueurPartie> getJoueurPartieByPartieId(long partieId) {
        String query = "select jp from JoueurPartie jp where jp.partie.idPartie=:idPartie";
        return entityManager.createQuery(query, JoueurPartie.class).setParameter("idPartie", partieId).getResultList();
    }

    public void setScore(long partieJoueurId, int score) {
        entityManager.getTransaction().begin();
        entityManager.find(JoueurPartie.class, partieJoueurId).setScore(score);
        entityManager.getTransaction().commit();
    }
}
