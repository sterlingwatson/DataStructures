import java.util.NoSuchElementException;

/**
 * MyStack<T> is a class that implements a stack using the SinglyLinkedList class
 * @author	Sterling Watson
 * @version	version 1.0 03/10/2021
 */

public class MyStack<T>{

    private int size;
    private T data;
    private T top;
    private SinglyLinkedList stacky = new SinglyLinkedList();

    /**
     * A generic constructor for the stack. The top is intialized to null
     */
       
    public MyStack(){
        this.top = null;
        
    }

    /**
     * a pop method for the stack. It uses the method in SinglyLinkedList that allows the data element at the beginning of the list to be removed.
     * It return the element that was popped for use
     * @return the element popped in case it is needed
     */

    public T pop(){
        T temp = (T) stacky.getHead();
        data = temp;
        stacky.removeFromBeginning();
        return data;
    }

    /**
     * A method that allows the application to peek at the top element of the stack.
     * @return the value at the top of the stack without doing anything else
     * @throws NoSuchElementException in the case that the top is empty
     */

    public T peek() throws NoSuchElementException{
        top = (T) stacky.getHead();
        if(stacky.isEmpty()){
            throw new NoSuchElementException();
        }else{
            return top;
        }
    }

    /**
     * a push method for adding an element to the beginning the stack. It uses a specific addAtBeginning method to add the element to the beginning of the stack
     * @param element the element to be pushed
     */

    public void push(T element){
      stacky.addAtBeginning(element);
    }

    /**
     * A method that evaluates if the stack is empty and returns a true or false value
     * @return a true or false value based on the evaluation of the size of the stack.
     */

    public boolean isEmpty(){
        if(stacky.size() == 0){
            return true;
        }else{
            return false;
        }
    }
}