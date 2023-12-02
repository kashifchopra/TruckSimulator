

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class QueueTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class QueueTest
{
    /**
     * Default constructor for test class QueueTest
     */
    public QueueTest()
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
    public void testIsEmpty() {
        Queue<Integer> queue = new Queue(5);

        // Normal cases
        assertTrue(queue.isEmpty());

        queue.enqueue(4);
        assertFalse(queue.isEmpty());

        // Edge cases
        queue.dequeue();
        assertTrue(queue.isEmpty());

        // Enqueue and then dequeue multiple times
        queue.enqueue(1);
        queue.dequeue();
        queue.enqueue(2);
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testSize() {
        Queue<String> queue = new Queue(10);

        // Normal cases
        assertEquals(0, queue.size());

        queue.enqueue("one");
        assertEquals(1, queue.size());

        queue.enqueue("two");
        queue.dequeue();
        assertEquals(1, queue.size());

        // Edge cases
         for (int i = 0; i < 9; i++) {
             queue.enqueue("item");
         }
         assertEquals(10, queue.size());

    }

    @Test
    public void testEnqueue() {
        Queue<Integer> queue = new Queue(3);

        // Normal cases
        queue.enqueue(42);
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());

        queue.enqueue(87);
        assertEquals(2, queue.size());

        // Edge cases
        try {
            // Enqueue beyond the capacity
            queue.enqueue(99);
            queue.enqueue(100);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            // Expected exception
        }
    }

    @Test
    public void testDequeue() {
        Queue<String> queue = new Queue(4);

        // Normal cases
        queue.enqueue("apple");
        assertEquals("apple", queue.dequeue());

        queue.enqueue("banana");
        queue.enqueue("cherry");
        assertEquals("banana", queue.dequeue());

        // Edge cases
        
        
        queue.dequeue();
        // Dequeue from an empty queue
        
        boolean bool = false;
        try {
            
            queue.dequeue();
        } catch (RuntimeException e) {
            bool = true;
        }
        assertTrue(bool);

        queue.enqueue("orange");
        queue.enqueue("grape");
        queue.dequeue();
        assertEquals("grape", queue.dequeue());
    }
}
