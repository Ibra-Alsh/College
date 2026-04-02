public class Senior_Student extends Undergraduate_Student {
    public String project_Title;

    //class constructor
    public Senior_Student(String name, String id, int age, String project_Title) {
        super(name, id, age);
        this.project_Title = project_Title;
    }

    //returns a formatted string of all information stored in Senior_Student
    public String toString() {
        return "Senior Student: " + super.toString() + ", Project Title: " + project_Title + ", GPA: " + gpa;
    }

}
