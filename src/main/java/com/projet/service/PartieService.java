package com.projet.service;

import com.projet.model.JPA.Joueur;
import com.projet.model.JPA.JoueurPartie;
import com.projet.model.JPA.Partie;
import com.projet.model.JoueurDto;
import com.projet.util.PersistenceManager;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

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
                newPartie.setDateDebut(LocalDate.now());
                em.persist(newPartie);
                em.getTransaction().commit();
                partie = newPartie;
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
        EntityManager em = PersistenceManager.getEntityManager();
        Partie partieActive = PartieService.getPartieActive();
        if (partieActive != null){
            String query = "select jp from JoueurPartie jp where jp.partie.idPartie=:idPartie";
            List<JoueurPartie> joueurParties = em.createQuery(query, JoueurPartie.class).setParameter("idPartie", partieActive.getIdPartie()).getResultList();
            for (JoueurPartie jp : joueurParties){
                Joueur j = jp.getJoueur();
                JoueurDto joueurDto = new JoueurDto(j.getLogin());
                CarteService.addJoueur(joueurDto);
            }
        }
    }

}
