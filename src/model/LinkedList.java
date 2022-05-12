package model;

import java.util.Iterator;

/**
 * A LinkedList object is a linked list that stores multiple generic Node objects
 *
 * @author Foothill College, Tanvi Waghela
 */
public class LinkedList<E> implements Iterable<E>{
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * Initializes a new LinkedList object that will store multiple generic Node objects
     */
    public LinkedList(){
        head = null;
        size = 0;
    }

    /**
     * Adds a new generic Node to the LinkedList object
     * @param newCountry  The new object that the user would like to add to the LinkedList
     */
    public void add(E newCountry){
        Node<E> newNode = new Node(newCountry);
        if(head == null){
            head = newNode;
            tail = newNode;
            size++;
        }
        else{
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    /**
     * Checks whether a specific object is in the list or not
     * @param country The object that the user would like to check if it's in the list or not
     * @return Returns The object if the object exists in the list, returns null otherwise
     */
    public E contains(E country){
        Node<E> current = head;
        while(current != null) {
            if(country instanceof Country){
                if(((Country) current.getData()).equals((Country) country)){
                    return current.getData();
                }
            }
            else if(country instanceof CountryFemaleCompletion){
                if(((CountryFemaleCompletion) current.getData()).equals((CountryFemaleCompletion) country)){
                    return current.getData();
                }
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Gives the user the object that is in the requested index
     * @param indexWanted The index that the user would like the object of
     * @return Returns the specific object located at the requested index
     */
    public E getIndex(int indexWanted){
        Node<E> current = head;
        int indexNum = 0;
        if (indexWanted < 0) {
            throw new IndexOutOfBoundsException("The index provided is out of range");
        }
        while(indexNum < size) {
            if(indexNum == indexWanted){
                return current.getData();
            }
            current = current.next;
            indexNum++;
        }
        throw new IndexOutOfBoundsException("The index provided is out of range");
    }

    /**
     * Lets the user insert a object into the linked list at a certain index
     * @param wantToInsert The object the user wants to insert
     * @param wantedIndex  The index the user wants to insert the object at
     */
    public void insertAtIndex(E wantToInsert, int wantedIndex){
        if(wantedIndex < 0){
            throw new IndexOutOfBoundsException();
        }
        else if(wantedIndex > size){
            add(wantToInsert);
        }
        else{
            Node<E> current = head;
            Node<E> newNode = new Node<E>(wantToInsert);
            int index = 1;
            while(index < size) {
                if(index == wantedIndex){
                    newNode.setNext(current.next);
                    current.setNext(newNode);
                }
                current = current.next;
                index++;
            }
        }
    }

    /**
     * Creates a list iterator object which will allow the user to iterate through the linked list
     * @return Returns a list iterator object
     */
    public ListIterator iterator(){
        ListIterator<E> listIterator = new ListIterator<E>();
        return listIterator;
    }

    /**
     * Gives the user the size of the list
     * @return Returns the user the size of the list
     */
    public int size(){
        return size;
    }

    /**
     * Represents the CountryList object using a string representation
     * @return Returns a string representation of a CountryList object
     */
    public String toString(){
        Node current = head;
        String tableOutput = "";
        while(current != null) {
            tableOutput += current.getData().toString();
            current = current.next;
        }
        return tableOutput;
    }

    /**
     * A LinkedIterator object allows the user to iterate through a LinkedList
     *
     * @author Foothill College, Tanvi Waghela
     */
    private class ListIterator<E> implements Iterator<E>{
        private Node<E> current;

        /**
         * Initializes a new ListIterator object
         */
        public ListIterator(){
            current = (Node<E>) head;
        }

        /**
         * Checks if there is another object next to the current object
         * @return  Return true if there is an object next to the current and false otherwise
         */
        @Override
        public boolean hasNext() {
            if (current == null) {
                return false;
            }
            return true;
        }

        /**
         * Iterates the current object to the next object
         * @return Returns the data stored in the current object
         */
        @Override
        public E next(){
            E data;
            if(!hasNext()){
                data = current.getData();
                return data;
            }
            data = current.getData();
            current = current.getNext();
            return data;
        }

        /**
         * Throws a Unsupported Operation Exception since this feature is not available yet
         */
        public void remove(){
            throw new UnsupportedOperationException();
        }

    }

    /**
     * A Node stores the data, name of any object e, and the year labels for a single object e and has a reference to the next Node object
     *
     * @author Foothill College, Tanvi Waghela
     */
    private class Node<E> {
        private E data;
        protected Node<E> next;

        /**
         * Initializes a new Node object that will store the data
         * @param data The data for any object e
         */
        public Node(E data){
            this.data = data;
            next = null;
        }

        /**
         * Initializes a new Node object that will store the data
         * @param data The data for any object e
         * @param next The reference to the next Node object
         */
        public Node(E data, Node<E> next){
            this.data = data;
            this.next = next;
        }

        /**
         * Gives the user the data for any object e
         * @return Returns the data for any object e
         */
        public E getData(){
            return data;
        }

        /**
         * Gives the user the reference to the next Node object
         * @return Returns the reference to the next Node object
         */
        public Node<E> getNext(){
            return next;
        }

        /**
         * Sets next to be a reference to a new Node object
         * @param next The reference to a new Node
         */
        public void setNext(Node<E> next){
            this.next = next;
        }

        /**
         * Represents the Node object using a string representation
         * @return Returns a string representation of a Node object
         */
        public String toString(){
            return data.toString();
        }

    }
}

