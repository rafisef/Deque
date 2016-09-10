import java.util.Objects;
import java.util.StringJoiner;

/**
 * Created by Rafael on 4/8/2016.
 */

class Deque<Base>{
    private class Node{ // Node class for holding information within our nodes
        private Node left;
        private Node right;
        private Base object;
        private Node(Base object,Node left,Node right){
            this.object = object;
            this.left=left;
            this.right=right;
        }
    }
    private Node head;
    public boolean isEmpty(){
        return (head.right==head); //checks to see if head.right points to itself.
    }
    public Deque(){
        head = new Node(null,null,null); // Creates an empty head node
        head.left = head; //Points the left side of the node back to itself
        head.right = head; //Points the right side of the node back to itself
        // We know have an Empty Circular List
    }
    public void enqueueFront(Base object){
        Node temp = new Node(object,head,head.right); //Creates a temp node that has temp.left pointing to head and temp.right pointing to head.right
        head.right.left = temp; // set the left block of the Node above Head to temp
        head.right = temp; // set the right side of head to point to the new node temp
        //head.right.left = head.left; // set the the left side of temp(which is head.right.left) = head.left
    }
    public void enqueueRear(Base object){
        Node temp = new Node(object,head.left,head);
        head.left.right = temp;
        head.left = temp;
        //head.left.right = head.right;
    }
    public Base dequeueFront(){
        if (isEmpty()){
            throw new IllegalArgumentException("Empty List");
        }
        Node temp = head.right;
        head.right = head.right.right;
        head.right.right.left = head;
        return temp.object;
    }
    public Base dequeueRear(){
        if(isEmpty()){
            throw new IllegalArgumentException("Empty List");
        }
        Node temp = head.left;
        head.left = head.left.left;
        head.left.left.right = head;
        return temp.object;
    }
}





public class Driver {
    public static void main(String [] args){
        Deque<String> s = new Deque<>();
        s.enqueueFront("A");
        s.enqueueFront("B");
        System.out.println(s.dequeueFront()); // B
        System.out.println(s.dequeueFront()); // A
       // System.out.println(s.dequeueFront()); // throws exception because we have have head.right == head
        System.out.println(s.isEmpty());
        s.enqueueFront("A");
        s.enqueueFront("B");
        s.enqueueRear("Z");
        s.enqueueRear("X");
        System.out.println(s.isEmpty());
        System.out.println(s.dequeueFront());
        System.out.println(s.dequeueRear());
        System.out.println(s.dequeueFront()); // B
        System.out.println(s.dequeueRear()); // throws exeoption
        //do a while loop to deque a bunch of deques
    }

}
