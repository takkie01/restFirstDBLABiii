package nl.utwente.di.resources;

import nl.utwente.di.dao.BikeDao;
import nl.utwente.di.model.Bike;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static javax.ws.rs.core.MediaType.*;

@XmlRootElement
public class BikesResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;



    @POST
    @Produces(TEXT_HTML)
    @Consumes(APPLICATION_FORM_URLENCODED)
    public void createBike(Bike bike, @Context
                           HttpServletResponse servletResponse) throws IOException {
        Bike newBike = new Bike(bike);
        BikeDao.instance.getBikeList().put(newBike.getID(), newBike);
            servletResponse.sendRedirect("../create_bike.html");
    }

    @Path("{bike}")
    public BikeResource getBike(@PathParam("bike") String id) {
        return new BikeResource(uriInfo, request, id);
    }

    @GET
    public HashMap<String, Bike> getBikes(){
        return BikeDao.instance.getBikeList();
    }
}
