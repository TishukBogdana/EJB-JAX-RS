package ifmo;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import java.io.Console;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Богдана on 20.11.2017.
 */
@Stateful
@SessionScoped
@Path("/point")
public class ShotManager {

    private DBShotServ serv =new DBShotServ();

  @POST
  @Path("/check")
    public void check(@FormParam("x") float x,@FormParam("y") float y , @FormParam("r") float r, @Context HttpServletRequest req, @Context HttpServletResponse resp){
try {
    Shots shot = new Shots(x, y, r);
    if (((x >= -r) && (y <= r) && (x <= 0) && (y >= 0)) || ((x >= 0) && (y >= 0) && (y <= -x + r)) || ((x <= 0) && (y <= 0) && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2)))) {
        shot.setFit(true);
    } else {
        shot.setFit(false);

    }
 List<Shots> list = (List<Shots>) req.getSession().getAttribute("shots");
    list.add(shot);
    serv.saveShot(shot);
    resp.sendRedirect("http://localhost:8080/laba4-1.0/check.html");
}catch (Exception e){}
    }
    @GET
    @Path("/getpoints")
public List<Shots>  getShots(@Context HttpServletRequest req){

 return (List<Shots>) req.getSession().getAttribute("shots") ;

    }
}
