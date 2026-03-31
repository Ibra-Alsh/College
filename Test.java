
Copy

import java.util.Scanner;
 
public class testCollege {
 
    static Scanner input = new Scanner(System.in);
 
    public static void main(String[] args) {
 
        System.out.println("*************** COLLEGE ****************");
        System.out.print("Enter College name: ");
        String name = input.nextLine();
        System.out.print("Enter max number of Departments: ");
        int maxDepts = input.nextInt();
        input.nextLine();
 
        College college = new College(name, maxDepts);
 
        int option;
        do {
            System.out.println("\n========== " + college.name + " - MAIN MENU ==========");
            System.out.println("1 - Add a Department");
            System.out.println("2 - Select a Department");
            System.out.println("3 - Total number of Students");
            System.out.println("4 - Exit");
            System.out.print("Option: ");
            option = input.nextInt();
            input.nextLine();
 
            switch (option) {
                case 1:
                    addDepartment(college);
                    break;
                case 2:
                    selectDepartment(college);
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
 
    // ──────────────── COLLEGE-LEVEL ACTIONS ────────────────
 
    static void addDepartment(College college) {
        System.out.print("Department name: ");
        String deptName = input.nextLine();
        System.out.print("Total students in department: ");
        int totalStudents = input.nextInt();
        System.out.print("Max number of Professors: ");
        int maxProfs = input.nextInt();
        input.nextLine();
 
        Department dept = new Department(deptName, totalStudents, maxProfs);
        college.addDept(dept);
        System.out.println("Department '" + deptName + "' added successfully!");
    }
 
    static void selectDepartment(College college) {
        if (college.numOfdept == 0) {
            System.out.println("No departments added yet.");
            return;
        }
 
        System.out.println("\n--- Departments ---");
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
    }
 
    // ──────────────── DEPARTMENT MENU ────────────────
 
    static void departmentMenu(Department dept) {
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
                    addProfessor(dept);
                    break;
                case 2:
                    removeProfessor(dept);
                    break;
                case 3:
                    searchProfessor(dept);
                    break;
                case 4:
                    System.out.println("Reward professors in " + dept.deptName
                            + ": " + dept.countRewardProfessor(dept.numOfProfessors));
                    break;
                case 5:
                    addStudent();
                    break;
                case 6:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1–6.");
            }
        } while (option != 6);
    }
 
    // ──────────────── PROFESSOR ACTIONS ────────────────
 
    static void addProfessor(Department dept) {
        System.out.print("Professor name: ");
        String profName = input.nextLine();
        System.out.print("Professor ID: ");
        String profId = input.nextLine();
        System.out.print("Salary: ");
        double salary = input.nextDouble();
        input.nextLine();
 
        Professors prof = new Professors(profName, profId, salary);
        dept.addProfessor(prof);
    }
 
    static void removeProfessor(Department dept) {
        System.out.print("Enter Professor ID to remove: ");
        String id = input.nextLine();
        dept.removeProfessor(id);
    }
 
    static void searchProfessor(Department dept) {
        System.out.print("Enter Professor ID to search: ");
        String id = input.nextLine();
        dept.Search_professor(id);
    }
 
    // ──────────────── STUDENT ACTIONS ────────────────
 
    static void addStudent() {
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
    }
}
