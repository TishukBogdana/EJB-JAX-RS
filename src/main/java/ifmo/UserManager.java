package ifmo;


import javax.ejb.EJB;
import javax.ejb.Singleton;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;



/**
 * Created by Богдана on 20.11.2017.
 */
@Singleton
@Path(value = "/usr")
public class UserManager {
@EJB
 private    DBUsrService serv =  new DBUsrService() ;
@EJB
private Sender sender;
    @Path("/signup")
    @POST
    public void addUser(@FormParam("name") String name, @FormParam("surname") String surname, @FormParam("login") String login, @FormParam("password") String password, @FormParam("mail") String email, @Context HttpServletResponse resp, @Context HttpServletRequest req) {
try {
    Usr usr = new Usr(name, surname, login, password, email);
   serv.saveUsr(usr);
   req.getSession().setAttribute("login", login);
   String msg = login+"присоединился к системе ";
   sender.sendMsg(msg);
  resp.sendRedirect("http://localhost:8080/laba4-1.0/check.html");
}catch (Exception e){e.printStackTrace();}
    }

    @Path("/login")
    @POST
    public void checkAuth(@FormParam("login") String login, @FormParam("password") String password, @Context HttpServletResponse resp, @Context HttpServletRequest req){
        try {
            boolean check = serv.assertUser(login, password);
            if (check) {
                req.getSession().setAttribute("login", login);
                String msg = login+"вошел в систему";
                sender.sendMsg(msg);
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
            String msg = req.getSession().getAttribute("login")+"вышел из сети";
            sender.sendMsg(msg);
            req.getSession().invalidate();
            resp.sendRedirect("http://localhost:8080/laba4-1.0/index.html");
        }catch(Exception e){}
    }
}
