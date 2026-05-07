public class College {
    protected String name;
    protected int numOfdept;
    public List deptList;

  //class Constructer
    public College (String name) {
        this.name = name;
        numOfdept = 0;
        deptList = new List();
    }

   //adds department to college
    public void addDept(Department D){
        deptList.insertAtEnd(D);
        numOfdept++;
    }

    //gets department by index
    public Department getDept(int index) {
        if (index < 0 || index >= numOfdept) return null;
        Node current = deptList.getHead();
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return (Department) current.getData();
    }

//returns total number of students
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

}
