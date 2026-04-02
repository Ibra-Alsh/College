public class Undergraduate_Student extends Student {
    public int age;
    public double gpa;

    //class constructor
    public Undergraduate_Student(String name, String id, int age) {
        super(name, id);
        this.age = age;
    }

    // if abstract method is protected in the parent class, it must be implemented as protected in the child class
    //calculates GPA using total grades and class hours then sets
    @Override
    protected double calculateGPA(double totalGradePoints, double totalCreditHours) {
        double gpa = 0.0;
        if (totalCreditHours > 0) {
            gpa = totalGradePoints / totalCreditHours;
        }
        this.gpa = gpa;
        return gpa;
    }

    //Checks students GPA, if it is over 4.5 it returns true for reward
    public boolean isReward(){
        if (this.gpa >= 4.5) {
            return true;
        }
        return false;
    }

	//returns a formatted string of all information stored in undergraduate_student
    public String toString() {
        return "Undergraduate Student: " + super.toString() + ", Age: " + age + ", GPA: " + gpa;
    }

    //returna stored gpa
    @Override
    public double calculateGPA() {
        return this.gpa;
    }
}
