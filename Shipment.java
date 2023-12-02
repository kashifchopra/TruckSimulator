
/**
 * Write a description of class Shipment here.
 *
 * @author kc
 * @version 1
 */
public class Shipment {
  
    public static int id = 0; //ID number of all the shipments 
    public int size; 
    public Warehouse startLoc; //shipments starting location
    public Warehouse endLoc;  //where the shipment must go 
    public Truck truck; //the truck the shipment is assigned to 
    public boolean delivered; //MAYBE? will return true or false if its been delivered 
    
    public Shipment(int size,Warehouse startLoc,Warehouse endLoc,Truck truck){
        id++;
        this.size = size; 
        this.startLoc = startLoc; //shipments starting location
        this.endLoc= endLoc;  //where the shipment must go 
        this.truck = truck;
    }
    
    
}
