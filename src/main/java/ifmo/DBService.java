package ifmo;

import javax.ejb.Singleton;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Богдана on 20.11.2017.
 */


public class DBService {


    EntityManagerFactory fact = Persistence.createEntityManagerFactory("JPAUNIT");
    EntityManager em = fact.createEntityManager();
    private static DBService serv = new DBService();
    public static DBService getDBService(){
        return serv;
    }
    public void saveShot(Shots shot){

        em.getTransaction().begin();
        em.persist(shot);
        em.getTransaction().commit();
    }
    public List<Shots> getAllShots(){
                return  em.createQuery("from Shots ").getResultList();
          }
    public void saveUsr(Usr usr){
        em.getTransaction().begin();
        em.persist(usr);
        em.getTransaction().commit();
    }
  public boolean assertUser(String login, String password) throws NoResultException{
        try {
            Usr usr = (Usr) em.createQuery(" from Usr where login = :login").setParameter("login", login).getSingleResult();
            if (!(usr == null)) {
                if (password.hashCode() == usr.getPassword()) return true;
            }
            return false;
        }catch (NoResultException e){return false;}
  }

}
