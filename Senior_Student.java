public class Senior_Student extends Undergraduate_Student {
    public String project_Title;

    public Senior_Student(String name, String id, int age, String project_Title) {
        super(name, id, age);
        this.project_Title = project_Title;
    }


    public String toString() {
        return "Senior Student: " + super.toString() + ", Project Title: " + project_Title + ", GPA: " + gpa;
    }

}
