package com.projet.service;

import com.projet.model.JPA.Joueur;
import com.projet.model.JoueurDto;
import com.projet.util.PersistenceManager;
import jakarta.persistence.EntityManager;

import java.util.List;

public class JoueurService {
    public static JoueurDto testLogin(String login, String password) {
        EntityManager em = PersistenceManager.getEntityManager();
        boolean result = false;
        Joueur joueurconnecte = null;
        JoueurDto joueurdto = null;
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
                joueurconnecte = newJoueur;
                result = true;
            }else{
                Joueur j = joueurs.get(0);
                result = j.getMdp().equals(password);
                joueurconnecte = j;
            }
            if (result){
                joueurdto = PartieService.addJoueurToPartieActiveIfNotInPartie(joueurconnecte);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return joueurdto;
    }

    public static void addJoueurToPartieActiveIfNotInPartie(String login) {
        EntityManager em = PersistenceManager.getEntityManager();
        String query = "select j from Joueur j where j.login = :login";
        List<Joueur> joueurs = em.createQuery(query, Joueur.class).setParameter("login", login).getResultList();
        if (joueurs!=null || !joueurs.isEmpty()) {
            PartieService.addJoueurToPartieActiveIfNotInPartie(joueurs.get(0));
        }
    }
}
