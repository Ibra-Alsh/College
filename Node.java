//making a node class as basis for the linked list
public class Node {
    //the data stored in the node
    private Object data;
    //connection to the next node in the list
    private Node next;

    public Node(Object data) {
        this.data = data;
        this.next = null;
    }

    //returns the data stored in the node
    public Object getData() {
        return data;
    }

    //sets the data stored in the node
    public void setData(Object data) {
        this.data = data;
    }

    //returns the next node in the list
    public Node getNext() {
        return next;
    }

    //sets the next node in the list
    public void setNext(Node next) {
        this.next = next;
    }
    
}
