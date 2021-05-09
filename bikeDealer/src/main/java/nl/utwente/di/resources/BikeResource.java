package nl.utwente.di.resources;

import nl.utwente.di.dao.BikeDao;
import nl.utwente.di.model.Bike;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement

@Path("/Bikes")
public class BikeResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String id;

    public BikeResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.id = id;
        this.request = request;
    }

    @Path("/{bikeid}")
    @GET
    public void getBikeDetails(@PathParam("bikeid") String bikeid){
//todo
//        for (Bike bikeIL: bikeList) {
//            if (bikeIL.getID() == bikeid){
//                return bikeIL;
//            }
//        }
//        return null;
    }

    @Path("/{bikeid}")
    @PUT
    public Bike updateBike(Bike bike, @PathParam("bikeid") String bikeid){
//      Bike bikeToUpdate = getBikeDetails(bikeid);
//      bikeList.remove(bikeToUpdate);
//      bikeToUpdate = bike;
//      bikeList.add(bikeToUpdate);
        Bike toupdate  = BikeDao.instance.getBikeList().get(bikeid);
        if (BikeDao.instance.getBikeList().containsKey(bikeid)){
            BikeDao.instance.getBikeList().remove(bikeid);
        }
        BikeDao.instance.getBikeList().put(bikeid, bike);
      return bike;
    }

    @Path("/{bikeid}")
    @DELETE
    public void deleteBike(@PathParam("bikeid") String bikeid){
        Bike toRemove = BikeDao.instance.getBikeList().remove(bikeid);
        if(toRemove==null)
            throw new RuntimeException("Delete: Todo with " + id +  " not found");
    }
}
