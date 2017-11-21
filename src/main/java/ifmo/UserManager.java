package ifmo;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;


/**
 * Created by Богдана on 20.11.2017.
 */
@Singleton
@Path(value = "/usr")
public class UserManager {
    @Path("/signup")
    @POST
    public void addUser(@FormParam("name") String name, @FormParam("surname") String surname, @FormParam("login") String login, @FormParam("password") String password, @FormParam("mail") String email, @Context HttpServletResponse resp) {
try {
    Usr usr = new Usr(name, surname, login, password, email);
   DBService.getDBService().saveUsr(usr);
  resp.sendRedirect("http://localhost:8080/laba4-1.0/check.html");
}catch (Exception e){e.printStackTrace();}
    }
    @Path("/login")
    @POST
    public void checkAuth(@FormParam("login") String login, @FormParam("password") String password, @Context HttpServletResponse resp){
        try {
            boolean check = DBService.getDBService().assertUser(login, password);
            if (check) {
                resp.sendRedirect("http://localhost:8080/laba4-1.0/check.html");
            } else {
                resp.sendRedirect("http://localhost:8080/laba4-1.0/index.html");
            }


        }catch (Exception e){
         e.printStackTrace();
        }

    }
    @Path("/checkLog")
    @POST
    public boolean checkLog(@FormParam("login") String login){
        //List<Usr> list = (List<Usr>) em.createQuery("from Usr where login =:login").setParameter("login",login).getResultList();
      //  if(list.size()==0){return true;}
        return false;
    }
    @Path("/logout")
    @POST
    public void logOut(@Context HttpServletRequest req, @Context HttpServletResponse resp){
        try {
            req.getSession().invalidate();
            resp.sendRedirect("http://localhost:8080/laba4-1.0/index.html");
        }catch(Exception e){}
    }
}
