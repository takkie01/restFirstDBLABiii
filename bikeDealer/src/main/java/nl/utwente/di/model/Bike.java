package nl.utwente.di.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bike {
    protected String ID;
    protected String ownerName;
    protected String colour;
    protected String gender;

    public Bike(String ID, String ownerName, String colour, String gender){
        this.ID = ID;
        this.ownerName = ownerName;
        this.colour = colour;
        this.gender = gender;
    }

    public Bike(Bike bike){
        new Bike(bike.ID, bike.ownerName, bike.colour, bike.gender);
    }

    public String getID() { return ID; }

    public void setID(String ID) { this.ID = ID; }

    public String getColour() { return colour; }

    public void setColour(String colour) { this.colour = colour; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getOwnerName() { return ownerName; }

    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
}
