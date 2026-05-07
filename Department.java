public class Department {
    protected String deptName;
    protected int totalStudent;
    protected int numOfProfessors;
    public List profList;

    //class constructor
    public Department (String deptName , int totalStudent, int numOFProfessors ) {
        this.deptName = deptName;
        this.totalStudent = totalStudent;
        this.numOfProfessors= 0;
        profList = new List();
    }

    //default constructor
    public Department() {

    }
    
//searches for professors that have been added by name in the department
    public Professor Search_professor (String d) {
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
        if(f)
            System.out.println("No results found.");

        return new Professor("", "", 0);
    }

//adds a professor to the department
    public void addProfessor(Professor p) {
        profList.insertAtEnd(p);
        numOfProfessors++;
    }
    
    //removes professor from the department--Altered because of use of linked list instead of array.
    public void removeProfessor(String k) {
        Node current = profList.getHead();
        while (current != null) {
            Professor prof = (Professor) current.getData();
            if (prof.getId().equalsIgnoreCase(k)) {
                profList.removeNode(current); // handles head, tail, and middle correctly
                numOfProfessors--;
                System.out.println("the professor is removed successfully");
                return;
            }
            current = current.getNext();
        }
        System.out.println("Professor with ID " + k + " not found.");
    }
    
    //Calculates rewards based on papers published by professors
    public int countRewardProfessor(int n) {
        if(n<=0) {
            return 0;
        }
        int count=0;
        Node current = profList.getHead();
        while (current != null) {
            Professor prof = (Professor) current.getData();
            if(prof.getPaper_published() >= n) {
                count++;
            }
            current = current.getNext();
        }
        return count+countRewardProfessor(n-1);
    }
}
}
