package com.projet.service;

import com.projet.model.JPA.Joueur;
import com.projet.model.JPA.JoueurPartie;
import com.projet.model.JPA.Partie;
import com.projet.model.JoueurDto;
import com.projet.util.PersistenceManager;
import jakarta.persistence.EntityManager;

import java.util.*;

public class PartieService {
    public static Partie getPartieActive(){
        EntityManager em = PersistenceManager.getEntityManager();
        Partie partie = null;
        try {
            em.getTransaction().begin();
            String query = "select p from Partie p where p.dateFin IS NULL";

            List<Partie> parties = em.createQuery(query, Partie.class).getResultList();
            if (parties==null || parties.isEmpty()){
                Partie newPartie = new Partie();
                newPartie.setDateDebut(new Date());
                em.persist(newPartie);
                em.getTransaction().commit();
                partie = newPartie;
                CarteService.generateNewPartie();
            }else {
                partie = parties.get(0);
            }

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return partie;
    }

    public static boolean IsJoueurInPartie(Partie partie, Joueur joueur){
        EntityManager em = PersistenceManager.getEntityManager();
        boolean joueurInPartie = false;
        try {
            em.getTransaction().begin();
            String query = "select jp from JoueurPartie jp where jp.partie.idPartie=:idPartie and jp.joueur.idJoueur=:idJoueur";

            List<JoueurPartie> joueurParties = em.createQuery(query, JoueurPartie.class).setParameter("idPartie", partie.getIdPartie()).setParameter("idJoueur", joueur.getIdJoueur()).getResultList();
            joueurInPartie = !joueurParties.isEmpty();

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return joueurInPartie;
    }

    public static void addJoueurToPartie(Partie partie, Joueur joueur){
        EntityManager em = PersistenceManager.getEntityManager();
        try {
            em.getTransaction().begin();
            JoueurPartie joueurPartie = new JoueurPartie();
            joueurPartie.setJoueur(joueur);
            joueurPartie.setPartie(partie);
            joueurPartie.setScore(0);
            em.persist(joueurPartie);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static JoueurDto addJoueurToPartieActiveIfNotInPartie(Joueur joueur){
        Partie partieActive = PartieService.getPartieActive();
        if (!PartieService.IsJoueurInPartie(partieActive, joueur)){
            PartieService.addJoueurToPartie(partieActive, joueur);
            JoueurDto joueurDto = new JoueurDto(joueur.getLogin());
            CarteService.addJoueur(joueurDto);
            return joueurDto;
        }
        return CarteService.getJoueur(joueur.getLogin());
    }

    public static void generateNewPartieDto(){
        CarteService.generateNewPartie();
        EntityManager em = PersistenceManager.getEntityManager();
        Partie partieActive = PartieService.getPartieActive();
        if (partieActive != null){
            String query = "select jp from JoueurPartie jp where jp.partie.idPartie=:idPartie";
            List<JoueurPartie> joueurParties = em.createQuery(query, JoueurPartie.class).setParameter("idPartie", partieActive.getIdPartie()).getResultList();
            if (joueurParties!=null) {
                for (JoueurPartie jp : joueurParties) {
                    Joueur j = jp.getJoueur();
                    JoueurDto joueurDto = new JoueurDto(j.getLogin());
                    CarteService.addJoueur(joueurDto);
                    jp.setScore(0);
                }
            }
        }
    }

    public static Map<String, Integer> finirPartie(){
        Map<String, Integer> dicoScores = new Hashtable<String, Integer>();
        EntityManager em = PersistenceManager.getEntityManager();
        Partie partieActive = PartieService.getPartieActive();
        em.getTransaction().begin();
        em.find(Partie.class,partieActive.getIdPartie()).setDateFin(new Date());
        String query = "select jp from JoueurPartie jp where jp.partie.idPartie=:idPartie";
        List<JoueurPartie> joueurParties = em.createQuery(query, JoueurPartie.class).setParameter("idPartie", partieActive.getIdPartie()).getResultList();
        for (JoueurPartie jp : joueurParties){
            JoueurDto j = CarteService.getJoueur(jp.getJoueur().getLogin());
            if (j!=null) {
                em.find(JoueurPartie.class, jp.getIdJoueurPartie()).setScore(j.getScore());
            }
            dicoScores.put(jp.getJoueur().getLogin(),jp.getScore());
        }
        em.getTransaction().commit();
        return dicoScores;
    }

}
