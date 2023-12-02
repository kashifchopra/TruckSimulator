import java.util.ArrayList;

/**
 * Write a description of class Warehouse here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Warehouse
{
    
    public static int id = 0;
    public int warePosX;// X coordinate of the warehouse 
    public int warePosY;// Y coordinate of the warehouse
    public int numDocks; //number of loading docks (0 to 3)?
    public int takenDocks; //MAYBE? number of loading docks with trucks in them 
    
    public Queue<Truck> wareQ; //queue of trucks entering the warehouse 
    public ArrayList<Shipment> shipments; //arraylist of shipments 

    public Truck[] docks; //these are all the docks in the warehouse containing trucks 

    public Warehouse(){
        
    }
    
    public Warehouse(int numDocks,int warePosX,int warePosY){
        
        id++;
        
        this.numDocks = numDocks;
        this.warePosX = warePosX;
        this.warePosY = warePosY;
        
        docks = new Truck[numDocks]; //creates an array where each element is a dock
    
        shipments = new ArrayList<Shipment>();
    }
    
    public Warehouse(int numDocks,int totaltrucks, int warePosX,int warePosY){
        
        id++;
        
        this.numDocks = numDocks;
        this.warePosX = warePosX;
        this.warePosY = warePosY;
        
        docks = new Truck[numDocks]; //creates an array where each element is a dock
    
        wareQ = new Queue<Truck>(totaltrucks);
    
        shipments = new ArrayList<Shipment>();
        
    }
    
    //will be used at the end to stop the similationo once all warehouses return this to be true; 
    public boolean allcomplete(){ //checks if all the shipments from this warehouse have gone where they had to or come here
        boolean out = true; 
        
        for(Shipment ship : shipments){ //checks all shipments in the array to see if theres any that doesnt belong here
            if(!ship.endLoc.equals(this)){
              out = false;
            }
        }
        
        return out; 
    }
    

    
}
