public class Department {
    protected String deptName;
    protected int totalStudent;
    protected int numOfProfessors;
    public Professors [] profList;

    public Department (String deptName , int totalStudent, int numOFProfessors ) {
        this.deptName = deptName;
        this.totalStudent = totalStudent;
        this.numOfProfessors= 0;
        profList = new Professors[numOFProfessors];
    }

    public Department() {

    }

    public void Search_professor (String d) {
        boolean f = true;
        for (int i = 0 ; i<numOfProfessors;i++) {
            if(profList[i].getID().equalsIgnoreCase(d)) {
                System.out.println(profList[i].toString());
                f =false;
                return;
            }
        }
        if(f)
            System.out.println("No results found.");

    }

    public void addProfessor(Professors p) {
        if(numOfProfessors<profList.length) {
            profList[numOfProfessors]=p;
            numOfProfessors++;
            System.out.println("the professor is added successfully");
        }
    }
    public void removeProfessor(String k) {
        for(int i = 0 ; i<numOfProfessors;i++) {
            if(profList[i].getID().equalsIgnoreCase(k)) {
                for(int j =i; j<numOfProfessors-1;j++) {
                    profList[j]=profList[j+1];
                }
                profList[numOfProfessors-1]=null;
                numOfProfessors--;
                System.out.println("the professor is removed successfully");
                return;
            }
        }
        System.out.println("the professr is not found");
    }
    public int countRewardProfessor(int n) {
        if(n<=0) {
            return 0;
        }
        int count=0;
        if(profList[n-1] != null) {
            if(profList[n-1].isReward()) {
                count=1;
            }
        }
        return count+countRewardProfessor(n-1);
    }
}
