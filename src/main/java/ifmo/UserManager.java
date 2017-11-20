package ifmo;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;


/**
 * Created by Богдана on 20.11.2017.
 */
@Singleton
@Path("/usr")
public class UserManager {
    @PersistenceContext
    EntityManager em ;
    @Path("/signup")
    @POST
    public String addUser(@FormParam("name") String name, @FormParam("surname") String surname,  @FormParam("login") String login, @FormParam("password") String password,@FormParam("mail") String email) {
     Usr usr = new Usr(name,surname,login,password,email);
     try{
       em.persist(usr);
       return "redirect: check.html";
     }catch (Exception e){
    return "redirect: signup.html";
     }
    }
    @Path("/login")
    @POST
    public String checkAuth(@FormParam("login") String login, @FormParam("password") String password){
        try {
            Usr usr =(Usr) em.createQuery("from Usr where login =:login").setParameter("login",login).getSingleResult();
            if(usr.getPassword()==password.hashCode()){
                return "redirect: check.html";
            }else {
                return "redirect: login.html";
            }
        }catch (Exception e){
            return "redirect: login.html";
        }

    }
    @Path("/checkLog")
    @POST
    public boolean checkLog(@FormParam("login") String login){
        List<Usr> list = (List<Usr>) em.createQuery("from Usr where login =:login").setParameter("login",login).getResultList();
        if(list.size()==0){return true;}
        return false;
    }
}
