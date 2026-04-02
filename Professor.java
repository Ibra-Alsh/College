public class Professor implements reward{
    private String name;
    public String id;         
    private double salary;
    private int paper_published = 0;

    //class constructor
    public Professor(String name, String id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }
    
 //returns stored id
    public String getId() {
        return id;
    }
    
 	//adds an amount of papers published by the professor
    public void setPapersPublished(int count) {
        this.paper_published += count;
        this.salary += paper_published * 1500;
    }
    
 //returns salary of professor with respect to base salary plus the reward
    public double calcuataSalary() {
        return this.salary;
    }
    
 //if papers published is one or more it returns true
    public boolean isReward() {
        return paper_published >= 1;
    }
    
    //returns number of papers published by the professor
    public int getPaper_published(){
        return paper_published;
    }
    
//returns name of Professor
    public String getName(){
        return this.name;
    }
    
 //returns a formatted string of all information stored in Professor
    @Override
    public String toString() {
        return "Professors{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", salary=" + salary +
                ", paper_published=" + paper_published +
                '}';
    }
}
