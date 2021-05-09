package nl.utwente.di.dao;

import nl.utwente.di.model.Bike;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@XmlRootElement
public enum BikeDao {
    instance;
//    private List<Bike> bikeList = new ArrayList<>();
    private HashMap<String, Bike> bikeList;

//    private TodoDao(){
//        Todo todo = new Todo
//    }

    public HashMap<String, Bike> getBikeList(){return bikeList;}


}
