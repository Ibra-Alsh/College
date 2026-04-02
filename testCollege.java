import java.util.Scanner;
 
public class testCollege {
 
    static Scanner input = new Scanner(System.in);
 
    public static void main(String[] args) {

        //startup menu
        System.out.println("*************** COLLEGE ****************");
        System.out.print("Enter College name: ");
        String name = input.nextLine();
        System.out.print("Enter the maximum number of Departments: ");
        int maxDepts = input.nextInt();
        input.nextLine();
 
        College college = new College(name, maxDepts);
 
        int option;
        do {
            //inside college menu
            System.out.println("\n========== " + college.name + " - MAIN MENU ==========");
            System.out.println("1 - Add Department");
            System.out.println("2 - Select Department");
            System.out.println("3 - Total number of Students");
            System.out.println("4 - Exit");
            System.out.print("Enter your option: ");
            option = input.nextInt();
            input.nextLine();
 
            switch (option) {
                case 1:
                    //creating department
                    System.out.print("Department name: ");
                    String deptName = input.nextLine();
                    System.out.print("Total students in department: ");
                    int totalStudents = input.nextInt();
                    System.out.print("Maximum number of Professors: ");
                    int maxProfs = input.nextInt();
                    input.nextLine();
            
                    Department dept = new Department(deptName, totalStudents, maxProfs);
                    college.addDept(dept);
                    System.out.println("Department '" + deptName + "' added successfully!");
                    break;
                case 2:
                    //checks if any department has been created
                    if (college.numOfdept == 0) {
                        System.out.println("No departments added yet.");
                        return;
                    }
            
                    System.out.println("\n--- Departments ---");
                    //shows available departments
                    for (int i = 0; i < college.numOfdept; i++) {
                        System.out.println((i + 1) + " - " + college.deptList[i].deptName);
                    }
                    System.out.print("Select department number: ");
                    int idx = input.nextInt() - 1;
                    input.nextLine();
            
                    if (idx < 0 || idx >= college.numOfdept) {
                        System.out.println("Invalid selection.");
                        return;
                    }
            
                    departmentMenu(college.deptList[idx]);
                    break;
                case 3:
                    System.out.println("Total students across all departments: " + college.sumOfstudent());
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1–4.");
            }
        } while (option != 4);
 
        input.close();
    }
 
 
    //Department menu
 
    static void departmentMenu(Department dept) {
        int option;
        do {
            //menu inside department
            System.out.println("\n===== DEPARTMENT: " + dept.deptName + " =====");
            System.out.println("1 - Add Professor");
            System.out.println("2 - Remove Professor");
            System.out.println("3 - Search Professor by ID");
            System.out.println("4 - Count Reward Professors");
            System.out.println("5 - Add Student");
            System.out.println("6 - Back to Main Menu");
            System.out.print("Option: ");
            option = input.nextInt();
            input.nextLine();
 
            switch (option) {
                case 1:
                    System.out.print("Professor name: ");
                    String profName = input.nextLine();
                    System.out.print("Professor ID: ");
                    String profId = input.nextLine();
                    System.out.print("Salary: ");
                    double salary = input.nextDouble();
                    input.nextLine();
            
                    Professor prof = new Professor(profName, profId, salary);
                    dept.addProfessor(prof);
                    break;
                case 2:
                    System.out.print("Enter Professor ID to remove: ");
                    String id = input.nextLine();
                    dept.removeProfessor(id);
                    break;
                case 3:
                    System.out.print("Enter Professor ID to search: ");
                    String profSearchId = input.nextLine();
                    Professor p = dept.Search_professor(profSearchId);
                    if (!p.getName().isEmpty()) {
                        do {
                            //menu inside professor
                            System.out.println("\n===== PROFESSOR: " + p.getName() + " =====");
                            System.out.println("1 - Add papers published");
                            System.out.println("2 - Get number of papers published");
                            System.out.println("3 - Calculate Salary");
                            System.out.println("4 - Back to previous Menu");
                            System.out.print("Option: ");
                            option = input.nextInt();
                            switch (option) {
                                case 1:
                                    System.out.print("Enter number of papers you want to add: ");
                                    int papers = input.nextInt();
                                    p.setPapersPublished(papers);
                                    break;

                                case 2:
                                    int published = p.getPaper_published();
                                    System.out.println("Number of papers published: " + published);
                                    break;

                                case 3:
                                    System.out.println("Amount of salary: " + p.calcuataSalary());
                                    break;
                                case 4:
                                    System.out.println("Returning to previous menu");
                                    break;

                                default:
                                    System.out.println("Invalid option. Please enter 1–6.");
                            }
                        } while (option != 4);
                    }
                    else { System.out.println("No professor with that name");}
                    break;
                case 4:
                    System.out.println("Reward professors in " + dept.deptName
                            + ": " + dept.countRewardProfessor(dept.numOfProfessors));
                    break;
                case 5:
                    //student creation menu
                    System.out.println("\n--- Student Type ---");
                    System.out.println("1 - Undergraduate Student");
                    System.out.println("2 - Graduate Student");
                    System.out.println("3 - Senior Student");
                    System.out.print("Option: ");
                    int type = input.nextInt();
                    input.nextLine();
            
                    System.out.print("Student name: ");
                    String sName = input.nextLine();
                    System.out.print("Student ID: ");
                    String sId = input.nextLine();
            
                    System.out.print("Total grade points: ");
                    double gradePoints = input.nextDouble();
                    System.out.print("Total credit hours: ");
                    double creditHours = input.nextDouble();
                    input.nextLine();
            
                    switch (type) {
                        case 1: {
                            System.out.print("Age: ");
                            int age = input.nextInt();
                            input.nextLine();
                            Undergraduate_Student ug = new Undergraduate_Student(sName, sId, age);
                            ug.calculateGPA(gradePoints, creditHours);
                            System.out.println(ug);
                            System.out.println("Reward: " + ug.isReward());
                            break;
                        }
                        case 2: {
                            System.out.print("Academic Degree (e.g. MSc, PhD): ");
                            String degree = input.nextLine();
                            Graduate_Student gs = new Graduate_Student(sName, sId, degree);
                            gs.calculateGPA(gradePoints, creditHours);
                            System.out.println(gs);
                            System.out.println("Reward: " + gs.isReward());
                            break;
                        }
                        case 3: {
                            System.out.print("Age: ");
                            int age = input.nextInt();
                            input.nextLine();
                            System.out.print("Project Title: ");
                            String title = input.nextLine();
                            Senior_Student ss = new Senior_Student(sName, sId, age, title);
                            ss.calculateGPA(gradePoints, creditHours);
                            System.out.println(ss);
                            System.out.println("Reward: " + ss.isReward());
                            break;
                        }
                        default:
                            System.out.println("Invalid student type.");
                    }
                    break;
                case 6:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1–6.");
            }
        } while (option != 6);
    }
}
