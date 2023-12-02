
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

/**
 * The test class StackTest.
 *
 * @author  Kashif
 * @version 1
 */
public class StackTest
{
    /**
     * Default constructor for test class StackTest
     */
    public StackTest()
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
    public void testPush() {
        Stack<Integer> stack = new Stack();

        // Normal cases
        stack.push(42);
        assertFalse(stack.isEmpty());
        assertEquals(42, stack.peek().intValue());

        stack.push(87);
        assertEquals(87, stack.peek().intValue());

        // Edge cases
        stack.pop();
        stack.pop();
        for (int i = 0; i < 20; i++) {
            stack.push(i);
        }
        assertEquals(19, stack.peek().intValue());
    }

    @Test
    public void testIsEmpty() {
        Stack<Integer> stack = new Stack();

        // Normal cases
        assertTrue(stack.isEmpty());

        stack.push(42);
        assertFalse(stack.isEmpty());

        // Edge cases
        stack.pop();
        assertTrue(stack.isEmpty());

        // Push and then pop multiple times
        stack.push(1);
        stack.pop();
        stack.push(2);
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPeek() {
        Stack<String> stack = new Stack();

        // Normal cases
        stack.push("apple");
        assertEquals("apple", stack.peek());

        stack.push("banana");
        assertEquals("banana", stack.peek());

        // Edge cases
        stack.pop();
        stack.pop();

        boolean bool = false;
        try {
            stack.peek(); // Should throw NoSuchElementException
        } catch (NoSuchElementException e) {
            bool = true;
            System.out.println(bool);
        }
        assertTrue(bool);

        stack.push("orange");
        stack.pop();
        bool = false;
        try {
            stack.peek(); // Should throw NoSuchElementException
        } catch (NoSuchElementException e) {
            bool = true;
        }
        assertTrue(bool);
    }

    @Test
    public void testPop() {
        Stack<Integer> stack = new Stack();

        // Normal cases
        stack.push(42);
        assertEquals(42, stack.pop().intValue());

        stack.push(8);
        stack.push(16);
        assertEquals(16, stack.pop().intValue());

        // Edge cases

        boolean bool = false;
        try {
            stack.pop(); 
        } catch (NoSuchElementException e) {
            bool = true;
        }
        assertFalse(bool);

        stack.push(0);
        stack.pop();
        bool = false;
        try {
            stack.pop(); // Should throw NoSuchElementException
        } catch (NoSuchElementException e) {
            bool = true;
        }
        assertTrue(bool);
    }

    @Test
    public void testSize() {
        Stack<Integer> stack = new Stack();

        // Normal cases
        assertEquals(0, stack.size());

        stack.push(1);
        assertEquals(1, stack.size());

        stack.push(2);
        stack.pop();
        assertEquals(1, stack.size());
        stack.pop();

        // Edge cases
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }
        assertEquals(100, stack.size());

        for (int i = 0; i < 50; i++) {
            stack.pop();
        }
        assertEquals(50, stack.size());
    }

}
