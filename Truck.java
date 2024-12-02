
/**
 * Write a description of class Truck here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Truck implements Schedule {

    public Stack<Shipment> shipStack; //internal stack of shipments
    public int capacity; //total capacity of the truck
    public int filledSize; //capacity fo truck currently filled up by shipments
    public int speed; //speed of the truck 
    public static int id = 0;

    public double truckPosX; //X position of the truck on the map
    public double truckPosY; //Y position of the truck on the map 

    public Warehouse dest;
    public double nextDestX; // X position of the next warehouse to be visited
    public double nextDestY; // Y position of the next warehouse to be visited  

    public State state; //state of the truck using enum

    public Truck(int speed,int capacity,double truckPosX,double truckPosY, Warehouse dest){
        //the random generator will give all these random values to the constructor
        this.capacity = capacity;
        this.speed = speed;
        this.truckPosX = truckPosX; 
        this.truckPosY = truckPosY;
        this.dest = dest;

        filledSize = 0;
        shipStack = new Stack<Shipment>();

        id++;
    }

    public enum State{
        WAIT,MOVE,LOAD,UNLOAD
    }

    public void action(){

        if(state == State.MOVE){

        } else if (state == State.UNLOAD){
            unload();
        } else if (state == State.LOAD){
            //load();
        } else if (state == State.WAIT){

        }

        //Finish later
    }
    public int log_status(){
        return -1;
    }

    public boolean isEmpty(){ //  helper method -checks if the truck has no shipments in it 
        return shipStack.isEmpty(); 
    }

    public boolean load(Shipment shipment){
        //push ship to shipStack only if
        // filledSize + shipment.size <= capacity 

        if( filledSize + shipment.size <= capacity){
            shipStack.push(shipment);
            filledSize = filledSize + shipment.size;
            return true;
        }

        return false;
    }

    public Shipment unload(){

        //if its not an empty stack:

        if(!shipStack.isEmpty()){ //aka filledSize = 0
            Shipment out = shipStack.pop();
            filledSize = filledSize - out.size;
            return out;
        }

        return null;

    }

    public void move(Warehouse warehouse){

        double warePosX = warehouse.warePosX;
        double warePosY = warehouse.warePosY;

        if(!(warePosX==truckPosX && warePosY==truckPosY)){ //it wont move if theyre on the same spot

            // get angle bw truck and warehouse = theta
            double theta = getAngle(warehouse);

            double xspeed = speed*Math.cos(theta);
            double yspeed = speed*Math.sin(theta);

            truckPosX = truckPosX + xspeed;
            truckPosY = truckPosY + yspeed; //(will always be + as sin and cos will automatically give + or - value dep on quad) 
        }

    }

    public double getAngle(Warehouse warehouse){ //gets the angle between the truck and the warehouse 
        double warePosX = warehouse.warePosX;
        double warePosY = warehouse.warePosY;
        double angle = 0; 

        double xlength = 0;
        double ylength = 0;
        //check if 1st quadrant

        if(warePosX>=truckPosX && warePosY>=truckPosY){ //then ware is in the 1st quadrant wrt truck

            xlength =  warePosX -truckPosX;
            ylength =  warePosY -truckPosY;

            angle = Math.atan(ylength/xlength);

        } else if (warePosX<=truckPosX && warePosY>=truckPosY){ //if its in quad 2
            xlength =  truckPosX - warePosX;
            ylength =  warePosY -truckPosY;

            angle = Math.atan(ylength/xlength);
            angle = Math.PI - angle;

        } else if(warePosX<=truckPosX && warePosY<=truckPosY){ //3rd quad
            xlength =  truckPosX - warePosX;
            ylength =  truckPosY- warePosY;

            angle = Math.atan(ylength/xlength);
            angle = Math.PI + angle;
        } else { //4th quad
            xlength =  warePosX - truckPosX;
            ylength =  truckPosY- warePosY;

            angle = Math.atan(ylength/xlength);
            angle = Math.PI*2 - angle;
        }

        return angle;
    }

    public double dist(Warehouse warehouse){

        double warePosX = warehouse.warePosX;
        double warePosY = warehouse.warePosY;
        double dist = 0; 

        double xlength = 0;
        double ylength = 0;

        
        xlength =  Math.abs(warePosX -truckPosX);
        ylength =  Math.abs(warePosY -truckPosY);

        //distance formula
        dist = Math.sqrt(Math.pow(xlength,2) + Math.pow(ylength,2));

        return dist;
    }

    /**
     * checks if the truck reached the warehouse and then enqueues the truck into the warehouse queue (wareQ)
     */
    public boolean reached(Warehouse warehouse){
        boolean reached = false;
        if(dist(warehouse)<speed){ //so it doesnt skip over the warehouse. Should be within a close range of it
            //if reached, then join the warehouse queue (wareQ)
            
            warehouse.wareQ.enqueue(this);
            
            reached = true;
        }
        
        return reached; 
    }
}
