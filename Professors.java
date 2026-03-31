public class Professors {
    private String name;
    private String id ;
    private double salary;
    private int paper_publidhed;

    public Professors(String name, String id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public double calcuataSalary() {
        if (isReward())
        return 10000+paper_publidhed*1500;
        else return 10000;
    }
    public boolean isReward(){
        if (paper_publidhed>=1)
        return true;
        else
            return false;
    }


    @Override
    public String toString() {
        return "Professors{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", salary=" + salary +
                ", paper_publidhed=" + paper_publidhed +
                '}';
    }

    public String getID (){
        return this.id;
    }
}
