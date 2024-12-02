import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * a stack using an expandable array as the
 * backing data structure.  Elements are added and removed at the
 * end of the array.
 *
 * @author kc
 * @version 1
 */

public class Stack<E>
{
  private static final int DEFAULT_SIZE = 10;
  
  /**
   * Index of next available cell in array.
   */
  private int top;
  
  /**
   * The data store.
   */
  private E[] data;
  
  /**
   * Constructs an empty stack.
   */
  public Stack()
  {
    // Unchecked warning is unavoidable
    data = (E[]) new Object[DEFAULT_SIZE];
  }
  

  public boolean isEmpty()
  {
    return top == 0;
  }

 
  public E peek() throws NoSuchElementException
  {
    if (top == 0) throw new NoSuchElementException();
    return data[top - 1];
  }

  
  public E pop()
  {
    if (top == 0) throw new NoSuchElementException();
    E ret = data[--top];
    data[top] = null;
    return ret;
  }


  public void push(E item)
  {
    checkCapacity();
    data[top++] = item;
  }

 
  public int size()
  {
    return top;
  }

  /**
   * Ensures that the backing array has space to store at least 
   * one additional element.
   */
  private void checkCapacity()
  {
    if (top == data.length)
    {
      // create a copy of the data array with double the capacity
      data = Arrays.copyOf(data, data.length * 2);
    }
  }

}


    
    

