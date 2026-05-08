import java.io.Serializable;

public class Department implements Serializable {
    protected String deptName;
    protected int totalStudent;
    protected int numOfProfessors;
    public List profList;

    // class constructor
    public Department(String deptName, int totalStudent, int numOFProfessors) {
        this.deptName = deptName;
        this.totalStudent = totalStudent;
        this.numOfProfessors = 0;
        profList = new List();   // implemented linked list
    }

    // default constructor
    public Department() {
    }

    // searches for a professor by ID
    public Professor Search_professor(String d) {
        boolean f = true;
        Node current = profList.getHead();
        while (current != null) {
            Professor prof = (Professor) current.getData();
            if (prof.getId().equalsIgnoreCase(d)) {
                System.out.println(prof.toString());
                f = false;
                return prof;
            }
            current = current.getNext();
        }
        if (f)
            System.out.println("No results found.");
        return new Professor("", "", 0);
    }

    // adds a professor to the department
    // throws CollegeException
    public void addProfessor(Professor p) throws CollegeException {
        if (p == null) {
            throw new CollegeException("Cannot add a null professor.");
        }
        profList.insertAtEnd(p);
        numOfProfessors++;
        System.out.println("The professor is added successfully.");
    }

    // removes a professor from the department
    public void removeProfessor(String k) {
        Node current = profList.getHead();
        while (current != null) {
            Professor prof = (Professor) current.getData();
            if (prof.getId().equalsIgnoreCase(k)) {
                profList.removeNode(current);   // handles head, tail, and middle
                numOfProfessors--;
                System.out.println("The professor is removed successfully.");
                return;
            }
            current = current.getNext();
        }
        System.out.println("The professor is not found.");
    }

    // calculates rewards based on papers published
    public int countRewardProfessor(int n) {
        if (n <= 0) {
            return 0;
        }
        int count = 0;
        Node current = profList.getHead();
        while (current != null) {
            Professor prof = (Professor) current.getData();
            if (prof.getPaper_published() >= n) {
                count++;
            }
            current = current.getNext();
        }
        return count + countRewardProfessor(n - 1);
    }
}
