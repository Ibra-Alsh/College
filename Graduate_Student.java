public class Graduate_Student extends Student {
    public String academic_Degree;
    public double gpa;

    //class constructor
    public Graduate_Student(String name, String id, String academic_Degree) {
        super(name, id);
        this.academic_Degree = academic_Degree;
    }

    //calculates GPA using total grades and class hours then sets
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

    //returns a formatted string of all information stored in graduate_student
    public String toString() {
        return "Graduate Student: " + super.toString() + ", Academic Degree: " + academic_Degree + ", GPA: " + gpa;
    }

    //returns stored GPA
    public double calculateGPA() {
        return this.gpa;
    }

}
