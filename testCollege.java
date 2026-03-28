import java.util.Scanner;
public class testCollege {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
int nunI =0;

        System.out.println("*************** COLLEGE ****************");
        System.out.println("Add COLLEGE name .  ");
        String name = input.nextLine();
        System.out.println("Add number of Department .  ");
        int numOfdept = input.nextInt();
        College College = new College(name, numOfdept);
        int option;
        do {
            System.out.println(" here is your option.");
            option = input.nextInt();
            System.out.println("1-Add your Departments  .  ");
            System.out.println("2-sum of Student.");
            System.out.println("3  ------------LEAVE------------.");
            switch (option) {
                case '1':
                    //String deptName , int totalStudent, int numOFProfessors

                    System.out.println("add  Departments information 1- Name  ");
                    String deptName = input.nextLine();
                    System.out.println("2- total Student  ");
                    int totalStudent = input.nextInt();
                    System.out.println("3- number of Professors  ");
                    int numOFProfessors = input.nextInt();
                  Department newDept = new Department(deptName,totalStudent,numOFProfessors);
                     College.addDept(newDept);



                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + option);
            }


        }while (option!=3);
    }
}
