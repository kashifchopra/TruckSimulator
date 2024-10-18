
/**
 *  class Queue using circular array
 *
 * @author kc
 * @version 1
 */

public class Queue<E> { 

    private E[] a;  // number of elements on queue  
    private int n = 0;  // index of first element of queue  
    private int first = 0;  // index of next available slot  
    private int last  = 0;  

    public Queue(int capacity){    // cast needed 
        a = (E[]) new Object[capacity];  
    }

    public boolean isEmpty(){   
        return n == 0;
    }    

    public int size() {
        return n; 
    }  

    public void enqueue(E item) {  
        if (n == a.length){  
            throw new RuntimeException("Ring buffer overflow"); 
        }   
        a[last] = item;    // wrap-around    
        last = (last + 1) % a.length;  
        n++; 
    }

    // remove the least recently added item 
    // doesn't check for underflow  
    public E dequeue(){  

        if (isEmpty()){   
            throw new RuntimeException("Ring buffer underflow");  
        }    
        E item = a[first];   
        a[first] = null;   
        n--;    // wrap-around    
        first = (first + 1) % a.length;   
        return item;  
    } 
}