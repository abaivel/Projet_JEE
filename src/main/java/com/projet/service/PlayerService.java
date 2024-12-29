package com.projet.service;

import com.projet.model.Joueur;
import com.projet.util.PersistenceManager;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PlayerService {
    public static boolean testLogin(String login, String password) {
        EntityManager em = PersistenceManager.getEntityManager();
        boolean result = false;
        try {
            em.getTransaction().begin();
            String query = "select j from Joueur j where j.login = :login";

            List<Joueur> joueurs = em.createQuery(query, Joueur.class).setParameter("login", login).getResultList();
            if (joueurs==null || joueurs.isEmpty()){
                Joueur newJoueur = new Joueur();
                newJoueur.setLogin(login);
                newJoueur.setMdp(password);
                em.persist(newJoueur);
                em.getTransaction().commit();
                result = true;
            }else{
                Joueur j = joueurs.get(0);
                result = j.getMdp().equals(password);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            PersistenceManager.close();
        }
        return result;
    }
}
