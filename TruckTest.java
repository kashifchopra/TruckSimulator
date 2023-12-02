

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class TruckTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TruckTest
{
    /**
     * Default constructor for test class TruckTest
     */
    public TruckTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
    
    @Test
    public void testLoad() {
        Warehouse warehouseA = new Warehouse();
        Warehouse warehouseB = new Warehouse();
        Truck truck = new Truck(1, 5, 0, 0, warehouseB);
        Shipment shipmentA = new Shipment( 1, warehouseA, warehouseB, truck);
        Shipment shipmentB = new Shipment( 2, warehouseA, warehouseB, truck);

        // Normal cases
        assertTrue(truck.isEmpty());
        assertTrue(truck.load(shipmentA));
        assertFalse(truck.isEmpty());
        assertEquals(1, truck.filledSize);
        assertTrue(truck.load(shipmentB));
        assertEquals(3, truck.filledSize);

        // Edge cases
        assertFalse(truck.load(new Shipment( 5, warehouseA, warehouseB, truck))); // Exceeds capacity
        assertTrue(truck.load(new Shipment( 2, warehouseA, warehouseB, truck))); // Just fits
    }
    
    @Test
    public void testUnload() {
        Warehouse warehouseA = new Warehouse();
        Warehouse warehouseB = new Warehouse();
        Truck truck = new Truck(1, 5, 0, 0, warehouseB);
        Shipment shipmentA = new Shipment(1, warehouseA, warehouseB, truck);
        Shipment shipmentB = new Shipment(2, warehouseA, warehouseB, truck);


        // Normal cases
        assertNull(truck.unload());
        truck.load(shipmentA);
        truck.load(shipmentB);
        assertEquals(2, truck.shipStack.size());
        assertEquals(shipmentB, truck.unload());
        assertEquals(1, truck.filledSize);
        assertEquals(shipmentA, truck.unload());
        assertEquals(0, truck.filledSize);

        // Edge cases
        assertNull(truck.unload()); // Empty stack
        truck.load(new Shipment( 4, warehouseA, warehouseB, truck));
        truck.unload();
        assertNull(truck.unload()); // Empty stack again
    }
    
     @Test
    public void testGetAngle() {
        Warehouse warehouseA = new Warehouse( 1, 0, 2);
        Warehouse warehouseB = new Warehouse( 1, 4, 3);
        Truck truck = new Truck(2, 5, 0, 0, warehouseB);

        
        // Normal cases
        assertEquals((Math.PI)/2, truck.getAngle(warehouseA)); // Angle in the first quadrant
        assertEquals(Math.atan(3.0/4.0), truck.getAngle(warehouseB)); // Angle in the first quadrant

        // Edge cases
        Truck truck2 = new Truck(1, 1, 5, 5, warehouseB);
        assertEquals(Math.PI, truck2.getAngle(new Warehouse(1, 2, 5))); // Angle in the second quadrant
        assertEquals(Math.PI*7/4, truck2.getAngle(new Warehouse( 1, 8, 2))); // Angle in the fourth quadrant
        assertEquals(Math.PI*3/2, truck2.getAngle( new Warehouse(1,5,2))); //right below the truck
    }
    
     @Test
  public void testMove() {
    // Normal cases
    Warehouse warehouse1 = new Warehouse( 1, 0, 2);
    Truck truck1 = new Truck(1, 1, 0, 0, warehouse1); // Starting at (0, 0) with speed 1.0

    truck1.move(warehouse1);

    assertEquals(0, truck1.truckPosX,0.001);
    assertEquals(1, truck1.truckPosY);

    
    // Edge case: truck already at the warehouse
    Warehouse warehouse2 = new Warehouse(1, 5, 5); // Warehouse at (5, 5)
    Truck truck2 = new Truck(2, 1, 5, 5, warehouse2); // Starting at (5, 5) with speed 2
    

    truck2.move(warehouse2);

    assertEquals(5.0, truck2.truckPosX);
    assertEquals(5.0, truck2.truckPosY);
    
  }
  
  @Test
    public void testDist() {
        Warehouse warehouseA = new Warehouse(1, 0, 2);
        Warehouse warehouseB = new Warehouse( 1, 4, 3);
        Truck truck = new Truck(2, 5, 0, 0, warehouseB);

        // Normal cases
        assertEquals(2.0, truck.dist(warehouseA)); // Dist to warehouseA
        assertEquals(5.0, truck.dist(warehouseB)); // Dist to warehouseB

        // Edge cases
        assertEquals(10.0, truck.dist(new Warehouse(4, 0, 10))); // Distance to warehouseC
        assertEquals(0.0, truck.dist(new Warehouse(4, 0, 0))); // Distance to warehouseD
    }
    
    @Test
    public void testReached() {
        Warehouse warehouseA = new Warehouse(0,2, 0, 2); //2nd parameter is 2 total trucks (size of wareQ)
        Warehouse warehouseB = new Warehouse(3,2, 4, 3);
        Truck truck = new Truck(1, 5, 0, 0, warehouseB);

        // Normal cases
        assertFalse(truck.reached(warehouseA)); // Not reached yet
        assertFalse(truck.reached(warehouseB)); // Not reached yet

        truck.truckPosX = 0;
        truck.truckPosY = 2;
        
        assertTrue(truck.reached(warehouseA)); // Reached warehouseA
        
        
        assertEquals(truck,warehouseA.wareQ.dequeue()); //check if it had entered the queue
        assertFalse(truck.reached(warehouseB)); // Not reached yet
        
        // Edge cases
        truck.truckPosX = 3.1;
        truck.truckPosY = 1.2;
        assertFalse(truck.reached(new Warehouse( 4, 2, 0, 1))); // Not reached yet
        assertTrue(truck.reached(new Warehouse( 4, 2, 4, 1))); // Reached warehouseD since its within the specified radius of the warehouse
    
       
    }
}
