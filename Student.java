import java.io.Serializable;
 
public abstract class Student extends Department implements reward, Serializable {
    private String name;
    private String id;
 
    // class constructor
    public Student(String name, String id) {
        super();
        this.name = name;
        this.id = id;
    }
 
    // abstract method
    protected abstract double calculateGPA(double totalGradePoints, double totalCreditHours);
 
    // abstract method
    public abstract double calculateGPA();
 
    // abstract method
    @Override
    public abstract boolean isReward();
 
    // returns a formatted string of all information stored in student
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
