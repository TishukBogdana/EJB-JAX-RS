package ifmo;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import sun.security.provider.SHA;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Богдана on 20.11.2017.
 */
@Stateful
@Path("/point")
public class ShotService implements Serializable{
    private List<Shots> shots;
  @POST
    public void check(@FormParam("x") float x,@FormParam("y") float y , @FormParam("r") float r){

       Shots shot = new Shots(x,y,r);
        if(((x>=-r)&&(y<=r)&&(x<=0)&&(y>=0))||((x>=0)&&(y>=0)&&(y<=-x+r))||((x<=0)&&(y<=0)&&(Math.pow(x,2)+Math.pow(y,2)<=Math.pow(r,2)))){
            shot.setFit(true);}else {shot.setFit(false);}
        shots.add(shot);
       DBService.getDBService().saveShot(shot);
    }

}
