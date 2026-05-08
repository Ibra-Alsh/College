import java.io.*;
 
public class College implements Serializable {
    protected String name;
    protected int numOfdept;
    public List deptList;
 
    // class Constructor linked list implemented
    public College(String name) {
        this.name = name;
        numOfdept = 0;
        deptList = new List();
    }
 
    // adds department to college / throws CollegeException if college has no space
    public void addDept(Department D) throws CollegeException {
        if (D == null) {
            throw new CollegeException("Cannot add a null department.");
        }
        deptList.insertAtEnd(D);   // from your version: linked list
        numOfdept++;
    }
 
    // gets department by index
    public Department getDept(int index) {
        if (index < 0 || index >= numOfdept) return null;
        Node current = deptList.getHead();
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return (Department) current.getData();
    }
 
    // returns total number of students
    public int sumOfstudent() {
        int sum = 0;
        Node current = deptList.getHead();
        while (current != null) {
            Department dept = (Department) current.getData();
            sum += dept.totalStudent;
            current = current.getNext();
        }
        return sum;
    }
 
    // saves college data to a file
    // checked exception
    public void SavetoFile(String fileName) throws IOException {
        File f = new File(fileName);
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream obs = new ObjectOutputStream(fos);
 
        obs.writeInt(numOfdept);
 
        // traverse linked list and write each department
        Node current = deptList.getHead();
        while (current != null) {
            Department dept = (Department) current.getData();
            obs.writeObject(dept);
            current = current.getNext();
        }
 
        obs.close();
        System.out.println("Saved successfully.");
    }
 
    // loads college data from a file
    // checked exception
    public void loadfromFile(String fileName) throws Exception {
        File f = new File(fileName);
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
 
        int count = ois.readInt();
 
        // reset linked list before loading
        deptList = new List();
        numOfdept = 0;
 
        for (int i = 0; i < count; i++) {
            Department dept = (Department) ois.readObject();
            deptList.insertAtEnd(dept);
            numOfdept++;
        }
 
        ois.close();
        System.out.println("Loaded successfully.");
    }
}
