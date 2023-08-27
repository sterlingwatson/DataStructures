import java.util.Iterator;
import java.util.NoSuchElementException;

/**
* A class that models a SinglyLinkedList. It has methods to add Nodes at the beginning and end of the list.
* It also contains methods to remove Nodes by index or from the beginning of the list. There is also a clear() method which will erase the whole list.
* The SinglyLinkedList class contains inner classes of type Node() and SinglyLinkedListIterator().
*
* @author	Sterling Watson
* @version	version 1.0 03/10/2021
*/
public class SinglyLinkedList<T> implements Iterable<T>{

	private Node nodeAtIndex;
    private Node head;
    private Node tail;
    private int size;

    /**
     * A constructor to create a singly linked list.
     */
	
	public SinglyLinkedList() {
		this.nodeAtIndex = null;
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * A method that adds an element to the end of the linked list
     * @param element a generic piece of data to be added at the end of the linked list
     */

    public void add(T element) {

        Node node = new Node(element, null);

        if (head == null) {
            head = node;
            tail = node;
        }else{
            tail.next = new Node(element, null);
            tail = tail.next;
        }
        size++;
    }

    /**
     * A method that adds an element to the beginning of the linked list. Important for implmenting a stack, and keeping constant time
     * @param element a generic piece of data to be added at the beginning of the linked list
     */

    public void addAtBeginning(T element){
        Node node = new Node(element, null);

        if (head == null && tail == null) {
            head = node;
            tail = node;
        }else{
            node.next = head;
            head = node;
        }
        size++;
    }

    /**
     * A method that adds an element at a specific index in a linked list
     * @param element a generic data to be added at the specified index of a linked list
     * @param index the index value to add the element as an int
     */
    public void addAtIndex(T element, int index) {
        int printIndex = index;
		Node newNode = new Node(element);
		
		if(index == 0){
			newNode.setNext(this.head);
			this.head = newNode;
		}else{
			Node current = this.head;
			while(index > 1){
				current = current.getNext();
				index--;
			}
			newNode.setNext(current.getNext());
			current.setNext(newNode);
		}
    }

    /**
     * A method that returns the held data of a Node in the list at a given index
     * @param index the index value of the Node who's data is to be returned
     * @return the data held in the Node
     */
    public T get(int index) {
        int counter = 0;
        Node current = head;
        while (current != null) {
            if (counter == index || counter < 0 )
                break;
            current = current.getNext();
            counter++;
        }
        if(current != null){
        return current.getData();
    }
        else return null;
    }

    /**
     * A method that returns the data held in the Node located at the head of the list.
     * Important for implementation of a stack
     * @return The data held in the head node
     * @throws NullPointerException in the case that the List is empty
     */

    public T getHead() throws NullPointerException{
        if(isEmpty() == false){
            return head.getData();
        }else{
            throw new NullPointerException();
        }
    }

    /**
     * A method that removes a data element in a liked by searching through list for a particular element
     * @param element the piece of data to be searched for
     * @throws NoSuchElementException in the case that the element is not found
     */

    public void removeByType(T element) throws NoSuchElementException{
        Node current = this.head;
		Node previous = null;
		
		if (current != null && current.data == element) {
			this.head = current.getNext();
		} else {
			while(current != null && current.data != element) {
				previous = current;
				current = current.getNext(); 
			}
			if (current != null) {
				previous.setNext(current.getNext ()); 
			}
			if (current == null) {
				throw new NoSuchElementException("The element you are trying to remove does not exist");
			}
		} 		
    }

    /**
     * A method that removes the element at the beginning of the linked list.
     * This method runs at constant time, and it important for the implementation of a stack
     * @return the removed node, returns null is 
     * @throws NullPointerException in the case that the head and therefor the list is empty
     */

    public Node removeFromBeginning(){
        if(head != null){
            Node toRemoveNode = head;
            head = head.next;
            size--;
            return toRemoveNode;
        }
        throw new NullPointerException();
    }

    /**
     * A method that erases all the data in the list and sets the size to zero
     */

    public void clear(){
        Node temp = this.head;
        while (temp != null){
            temp = temp.next;
            head = null;
            head = temp;
        }
        size = 0;
    }

    /**
     * A method that returns a boolean value true if the list is empty and false if the list has at least one element
     */

    public boolean isEmpty(){
        if (size == 0){
        return true;
        }else{
            return false;
        }
    }

    /**
     * A method that returns the size of the linked list as an int
     * @return the size of the linked list as an int
     */

    public int size() {
        return this.size;
    }

    /**
     * A method returns a Node that is int n distance away from the beginning of the linked list
     * @throws IndexOutOfBoundsException if the search takes the method out of bounds of the size of the list
     */

    public T getNthFromFirst(int n) throws IndexOutOfBoundsException{
        int search = n;
        if(n > size){
            throw new IndexOutOfBoundsException("This index is out of bounds");
        }
        return get(search);
    }

    /**
     * A method returns a Node that is int n distance away from the tail of the linked list
     * @throws IndexOutOfBoundsException if the search takes the method out of bounds of the size of the list
     */

    public T getNthFromLast(int n) throws IndexOutOfBoundsException{
        int search = n;
        if(n > size){
            throw new IndexOutOfBoundsException("This index is out of bounds");
        }
        search = (size - search);
        return get(search);
    }

    /**
     * A toString method that prints out the linked list
     */

    @Override
    public String toString() {
        String returnVal = "";
        Node current = this.head;
        if (size != 0 ) {
            while (current != null ) {
                returnVal += current.toString();
                returnVal += "\n";
                current = current.getNextNode();
            }
        }
        return returnVal;
    }

    /**
     * A method that creates and returns a new Iterator object
     * @return a new iterator object
     */
    
     @Override
    public Iterator<T> iterator() {
        return new SinglyLinkedListIterator<T>(this);
    }

    /**
     * A method that returns the Node Object specified by the index passed to it
     * @param index the int value of the Node in the linked list
     * @return the Node object and its data
     */

    public Node getNode(int index) {
        int counter = 0;
        Node current = head;
        while (current != null) {
            if (counter == index || counter < 0 )
                break;
            current = current.getNextNode();
            counter++;
        }
        if(current != null){
        return current;}
        else return null;
    }

    /**
     * A inner class that contains constructors for Iterators for a singly linked list.
     * It also implements Iterator and overrides the methods contained within
     */

    public class SinglyLinkedListIterator<T> implements Iterator<T>{
        private SinglyLinkedList<T> singlyLinkedList;
        private SinglyLinkedList<T>.Node currentNode;

        /**
         * A constructor for a singly linked list iterator that takes in a list
         */

        public SinglyLinkedListIterator(SinglyLinkedList<T> list){
            singlyLinkedList = list;
            currentNode = singlyLinkedList.head;
        } 

        /**
         * A constructor for a singly linked list iterator that takes in a arguement for a list and for an index to start 
         */
        public SinglyLinkedListIterator(SinglyLinkedList<T> list, int index){
            singlyLinkedList = list;
            currentNode = singlyLinkedList.nodeAtIndex;
        }

        /**
         * method that returns true if the interator's point has a next node
         */

        public boolean hasNext() {
            if (currentNode != null)
                return true;
            return false;
        }

        /**
         * A method that overwrites the next() method in iterable. Returns the data held in the next node from the current pointer.
         * @returns data held in the next node
         * @throws NoSuchElementException if there is no next element.
         */

        public T next() throws NoSuchElementException{
            if(next() == null){
                throw new NoSuchElementException("There is no next element");
            }
            T data = currentNode.getData();
            currentNode = currentNode.getNextNode();
            return data;
        }

        /**
         * A method that overwrites the remove() method in Iterator. Is a dummy method that throws an exception if used
         * @throws UnsupportedOperationException in the case that the method is used.
         */

         public void remove(){
            throw new UnsupportedOperationException("remove operation is not supported by this iterator");
        }
    }
    
    /**
     * An inner class Node which holds the data contianed within the Node and a refernce to the node's next
     */

    public class Node{
        private T data;
        private Node next;

        /**
         * Constructor for the Node class. Simply takes in the data to be held in a node
         * @param data the data that is to be held in the node
         */

        public Node(T data){
            this.data = data;
        }

        /**
         * Constructor for the Node class that creates a new Node with a refence to both the data within and to the next node.
         * @param data
         * @param next
         */

        public Node(T data, Node next){
            this.next = next;
            this.data = data;
        }

        /**
         * Helper method that sets the next for a given Node
         * @param next the next node to be set
         */

        public void setNext(Node next){
            this.next = next;
        }

        /**
         * A Helper method that returns a Node's next
         * @return The refence to the Node's next
         */

        public Node getNext(){
            return this.next;
        }

        /**
         * A helper method that returns the data held in a specific Node
         * @return The data held within the Node
         */

        public T getData(){
            return this.data;
        }

        /**
         * helper method that returns the next Node in refence to the Node called
         * @return the Next Node in the list
         */
    
        public Node getNextNode() {
            return next;
        }

        /**
         * To string method for returning the data held in a Node as a String
         */

        public String toString() {
            return data.toString();
        }
    }
}
	