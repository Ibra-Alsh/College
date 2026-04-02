public class Undergraduate_Student extends Student {
    public int age;
    public double gpa;

    //class constructor
    public Undergraduate_Student(String name, String id, int age) {
        super(name, id);
        this.age = age;
    }

    // if abstract method is protected in the parent class, it must be implemented as protected in the child class
    @Override
    protected double calculateGPA(double totalGradePoints, double totalCreditHours) {
        double gpa = 0.0;
        if (totalCreditHours > 0) {
            gpa = totalGradePoints / totalCreditHours;
        }
        this.gpa = gpa;
        return gpa;
    }

    public boolean isReward(){
        if (this.gpa >= 4.5) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "Undergraduate Student: " + super.toString() + ", Age: " + age + ", GPA: " + gpa;
    }

    @Override
    public double calculateGPA() {
        return this.gpa;
    }
}
