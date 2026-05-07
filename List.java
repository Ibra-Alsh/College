//list class to be able to fully implement linked list
public class List {
    //head of the linked list
    private Node head;
    //tail of the linked list
    private Node tail;
    //name of the list
    private String name;

    //default constructor to initialize the list
    public List() {
        head = null;
        tail = null;
        name = "no name";
    }

    public List(String name) {
        head = null;
        tail = null;
        this.name = name;
    }

    //checks if the list is empty
    public boolean isEmpty() {
        return head == null;
    }

    //inserts a new node at the end of the list
    public void insertAtEnd(Object data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    //removes the last node from the list and returns its data
    public Object removeFromEnd() {
        if (isEmpty()) return null;
        Node current = head;
        Node previous = null;
        while (current != tail) {
            previous = current;
            current = current.getNext();
        }
        if (previous != null) {
            previous.setNext(null);
            tail = previous;
        } else {
            head = tail = null;
        }
        return current.getData();
    }

    //in some use cases we need to remove the first node of the list
    public void removeFromFront() {
        if (isEmpty()) return;
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
    }

    //removes a specific node from the list
    public void removeNode(Node node) {
        if (isEmpty() || node == null) return;
        if (node == head) {
            removeFromFront();
            return;
        }
        Node current = head;
        Node previous = null;
        while (current != null && current != node) {
            previous = current;
            current = current.getNext();
        }
        if (current != null) {
            previous.setNext(current.getNext());
            if (current == tail) {
                tail = previous;
            }
        }
    }

    //removes a node at a specific index from the list
    public void removeAt(int index) {
        if (isEmpty()) return;
        if (index == 0) {
            removeFromFront();
            return;
        }
        Node current = head;
        Node previous = null;
        int count = 0;
        while (current != null && count < index) {
            previous = current;
            current = current.getNext();
            count++;
        }
        if (current != null) {
            previous.setNext(current.getNext());
            if (current == tail) {
                tail = previous;
            }
        } else {
            System.out.println("Index out of bounds.");
        }
    }

    public int size() {
        if (isEmpty()) return 0;
        Node current = head;
        int c = 1;
        while (current.getNext() != null) {
            current = current.getNext();
            c++;
        }
        return c;
    }

    public String getName() {
        return name;
    }

    public Node getHead() {
        return head;
    }
}
