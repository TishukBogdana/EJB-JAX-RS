package ifmo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Богдана on 24.11.2017.
 */
public class DBShotServ {

    EntityManagerFactory fact = Persistence.createEntityManagerFactory("JPAUNIT");
    EntityManager em = fact.createEntityManager();
    public void saveShot(Shots shot){

        em.getTransaction().begin();
        em.persist(shot);
        em.getTransaction().commit();
    }
    public List<Shots> getAllShots(){
        return  em.createQuery("from Shots ").getResultList();
    }
}
