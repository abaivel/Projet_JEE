package com.projet.model.DAO;

import com.projet.model.JPA.Partie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Date;
import java.util.List;

public class PartieDAO {private static final EntityManagerFactory entityManagerFactory =
        Persistence.createEntityManagerFactory("project");
    private EntityManager entityManager;

    public PartieDAO() {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public Partie getPartieActive(){
        String query = "select p from Partie p where p.dateFin IS NULL";

        List<Partie> parties = entityManager.createQuery(query, Partie.class).getResultList();
        return !parties.isEmpty() ? parties.get(0) : null;
    }

    public Partie addPartie(){
        entityManager.getTransaction().begin();
        Partie newPartie = new Partie();
        newPartie.setDateDebut(new Date());
        entityManager.persist(newPartie);
        entityManager.getTransaction().commit();
        return newPartie;
    }

    public void finishPartie(Partie partie){
        entityManager.getTransaction().begin();
        entityManager.find(Partie.class,partie.getIdPartie()).setDateFin(new Date());
        entityManager.getTransaction().commit();
    }

}
