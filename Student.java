public abstract class Student extends Department implements reward {

    private String name;
    private String id ;

    public Student (String name , String id ){
        super();
        this.name=name;
        this.id=id;
    }

    protected abstract double calculateGPA(double totalGradePoints, double totalCreditHours);

    public abstract double calculateGPA();

    @Override
    public abstract boolean isReward();

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}

