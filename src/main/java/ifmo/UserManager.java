package ifmo;


import javax.ejb.EJB;
import javax.ejb.Singleton;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.ArrayList;


/**
 * Created by Богдана on 20.11.2017.
 */
@Singleton
@Path(value = "/usr")
public class UserManager {
@EJB
 private    DBUsrService serv  ;
@EJB
private Sender sender;
    @Path("/signup")
    @POST
    public void addUser(@FormParam("name") String name, @FormParam("surname") String surname, @FormParam("login") String login, @FormParam("password") String password, @FormParam("mail") String email, @Context HttpServletResponse resp, @Context HttpServletRequest req) {
try {
    Usr usr = new Usr(name, surname, login, password, email);
   serv.saveUsr(usr);
   req.getSession().setAttribute("login", login);
   req.getSession().setAttribute("shots", new ArrayList<Shots>());
   String msg = "user: "+login+" joined system ";
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
                req.getSession().setAttribute("shots", new ArrayList<Shots>());

                String msg = "user: "+login+" entered system";
                sender.sendMsg(msg);
                resp.sendRedirect("http://localhost:8080/laba4-1.0/check.html");
            } else {
                resp.sendRedirect("http://localhost:8080/laba4-1.0/error_page.html");
            }


        }catch (Exception e){
         e.printStackTrace();
        }

    }


    @Path("/logout")
    @POST
    public void logOut(@Context HttpServletRequest req, @Context HttpServletResponse resp){
        try {
            String msg = "user: "+req.getSession().getAttribute("login")+" escaped";
           sender.sendMsg(msg);
            req.getSession().invalidate();
            resp.sendRedirect("http://localhost:8080/laba4-1.0/index.html");
        }catch(Exception e){}
    }
}
