import java.io.IOException;
import java.util.Scanner;

public class Testcollege {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        // startup menu
        System.out.println("*************** COLLEGE ****************");
        System.out.print("Enter College name: ");
        String name = input.nextLine();

        
        College college = new College(name);

        int option = -1;
        do {
            try {
                // inside college menu
                System.out.println("\n========== " + college.name + " - MAIN MENU ==========");
                System.out.println("1 - Add Department");
                System.out.println("2 - Select Department");
                System.out.println("3 - Total number of Students");
                System.out.println("4 - Save to File");
                System.out.println("5 - Load from File");
                System.out.println("6 - Exit");
                System.out.print("Enter your option: ");
                option = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                input.nextLine();
                System.out.println("Invalid input: " + e.getMessage());
            }

            switch (option) {
                case 1:
                    // creating department
                    System.out.print("Department name: ");
                    String deptName = input.nextLine();
                    System.out.print("Total students in department: ");
                    int totalStudents = input.nextInt();
                    System.out.print("Maximum number of Professors: ");
                    int maxProfs = input.nextInt();
                    input.nextLine();
                    try {
                        Department dept = new Department(deptName, totalStudents, maxProfs);
                        college.addDept(dept);   // throws CollegeException
                        System.out.println("Department '" + deptName + "' added successfully!");
                    } catch (CollegeException c) {
                        System.out.println("Error: " + c.getMessage());
                    }
                    break;

                case 2:
                    // checks if any department has been created
                    if (college.numOfdept == 0) {
                        System.out.println("No departments added yet.");
                        break;
                    }
                    System.out.println("\n--- Departments ---");
                    for (int i = 0; i < college.numOfdept; i++) {
                        System.out.println((i + 1) + " - " + college.getDept(i).deptName);
                    }
                    System.out.print("Select department number: ");
                    int idx = input.nextInt() - 1;
                    input.nextLine();

                    if (idx < 0 || idx >= college.numOfdept) {
                        System.out.println("Invalid selection.");
                        break;
                    }
                    try {
                        departmentMenu(college.getDept(idx));
                    } catch (CollegeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Total students across all departments: " + college.sumOfstudent());
                    break;

                case 4:
                    // save to file
                    // checked exception handled here
                    try {
                        System.out.print("Enter the file name to save: ");
                        String save = input.nextLine();
                        college.SavetoFile(save);
                    } catch (IOException e) {
                        System.out.println("Error saving file: " + e.getMessage());
                    }
                    break;

                case 5:
                    try {
                        System.out.print("Enter the file name to load: ");
                        String load = input.nextLine();
                        college.loadfromFile(load);
                    } catch (Exception e) {
                        System.out.println("Error loading file: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("Goodbye!");
                    break;

                default:
                    if (option != -1)
                        System.out.println("Invalid option. Please enter 1-6.");
            }
        } while (option != 6);

        input.close();
    }


    // Department menu - throws CollegeException
    static void departmentMenu(Department dept) throws CollegeException {
        int option;
        do {
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
                    // addProfessor throws CollegeException
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
                            System.out.println("\n===== PROFESSOR: " + p.getName() + " =====");
                            System.out.println("1 - Add papers published");
                            System.out.println("2 - Get number of papers published");
                            System.out.println("3 - Calculate Salary");
                            System.out.println("4 - Back to previous Menu");
                            System.out.print("Option: ");
                            option = input.nextInt();
                            switch (option) {
                                case 1:
                                    System.out.print("Enter number of papers to add: ");
                                    int papers = input.nextInt();
                                    p.setPapersPublished(papers);
                                    break;
                                case 2:
                                    System.out.println("Papers published: " + p.getPaper_published());
                                    break;
                                case 3:
                                    System.out.println("Salary: " + p.calcuataSalary());
                                    break;
                                case 4:
                                    System.out.println("Returning to previous menu.");
                                    break;
                                default:
                                    System.out.println("Invalid option.");
                            }
                        } while (option != 4);
                    } else {
                        System.out.println("No professor with that ID.");
                    }
                    break;

                case 4:
                    System.out.println("Reward professors in " + dept.deptName
                            + ": " + dept.countRewardProfessor(dept.numOfProfessors));
                    break;

                case 5:
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
                    System.out.println("Invalid option. Please enter 1-6.");
            }
        } while (option != 6);
    }
}
