import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {


    private College college;
    private JTextArea outputBox;

    //  Constructor: builds the whole window ─
    public GUI(College college) {
        this.college = college;

        // Window settings

        setTitle("College App - " + college.name);
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.darkGray);


        // Title at the top
        JLabel title = new JLabel( college.name, SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 40));
        add(title, BorderLayout.NORTH);

        // Buttons in the middle
        JPanel buttons = new JPanel(new GridLayout(2, 3, 20, 20));
        buttons.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        addBtn(buttons, "Add Department", new Runnable() {
            public void run() {
                addDepartment();
            }
        });
        addBtn(buttons, "Select Department", new Runnable() {
            public void run() {
                selectDepartment();
            }
        });
        addBtn(buttons, "Total Students", new Runnable() {
            public void run() {
                print("Total Students: " + college.sumOfstudent());
            }
        });
        addBtn(buttons, "Save", new Runnable() {
            public void run() {
                save();
            }
        });
        addBtn(buttons, "Load", new Runnable() {
            public void run() {
                load();
            }
        });
        addBtn(buttons, "Exit", new Runnable() {
            public void run() {
                System.exit(0);
            }
        });
        add(buttons, BorderLayout.CENTER);

        // Output box at the bottom
        outputBox = new JTextArea(8, 4);
        outputBox.setEditable(false);
        outputBox.setFont(new Font("Monospaced", Font.PLAIN, 13));
        add(new JScrollPane(outputBox), BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    //  Prints a message to the output box

    private void print(String msg) {
        outputBox.append(msg + "\n");
    }

    //  Creates a button and adds it to a panel
    private void addBtn(JPanel panel, String label, Runnable action) {
        JButton btn = new JButton(label);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action.run();
            }
        });
        panel.add(btn);
    }

    //  Ask user a question, get answer as String
    private String ask(String question) {
        return JOptionPane.showInputDialog(this, question);
    }

//  MAIN MENU ACTIONS

    private void addDepartment() {
        String name = ask("Department Name:");
        if (name == null) return;
        int students = Integer.parseInt(ask("Total Students:"));
        int maxProfs = Integer.parseInt(ask("Max Professors:"));
        try {
            college.addDept(new Department(name, students, maxProfs));
            print("Department '" + name + "' added!");
        } catch (CollegeException e) { print("Error: " + e.getMessage()); }
    }

    private void selectDepartment() {
        if (college.numOfdept == 0) { print("No departments yet."); return; }

        String[] names = new String[college.numOfdept];
        for (int i = 0; i < college.numOfdept; i++) names[i] = college.getDept(i).deptName;

        String chosen = (String) JOptionPane.showInputDialog(this, "Pick a Department:",
                "Departments", JOptionPane.PLAIN_MESSAGE, null, names, names[0]);
        if (chosen == null) return;

        for (int i = 0; i < college.numOfdept; i++) {
            Department dept = college.getDept(i);
            if (dept.deptName.equals(chosen)) openDeptWindow(dept);
        }
    }

    private void save() {
        String file = ask("File name to save:");
        if (file == null) return;
        try { college.SavetoFile(file); print("Saved to: " + file); }
        catch (Exception e) { print("Error: " + e.getMessage()); }
    }

    private void load() {
        String file = ask("File name to load:");
        if (file == null) return;
        try { college.loadfromFile(file); print("Loaded from: " + file); }
        catch (Exception e) { print("Error: " + e.getMessage()); }
    }


//  DEPARTMENT WINDOW

    private void openDeptWindow(Department dept) {
        JDialog window = new JDialog(this, "Department: " + dept.deptName, true);
        window.setSize(400, 220);
        window.setLocationRelativeTo(this);
        window.setLayout(new GridLayout(2, 3, 10, 10));
        window.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().setBackground(Color. getHSBColor(0, 0 , 0));

        addBtn2(window, "Add Professor", new Runnable() {
            public void run() {
                addProfessor(dept);
            }
        });
        addBtn2(window, "Remove Professor", new Runnable() {
            public void run() {
                removeProfessor(dept);
            }
        });
        addBtn2(window, "Search Professor", new Runnable() {
            public void run() {
                searchProfessor(dept, window);
            }
        });
        addBtn2(window, "Reward Count", new Runnable() {
            public void run() {
                print("Reward Profs: " + dept.countRewardProfessor(dept.numOfProfessors));
            }
        });
        addBtn2(window, "Add Student", new Runnable() {
            public void run() {
                addStudent(dept, window);
            }
        });
        addBtn2(window, "Back", new Runnable() {
            public void run() {
                window.dispose();
            }
        });

        window.setVisible(true);
    }

    private void addBtn2(JDialog window, String label, Runnable action) {
        JButton btn = new JButton(label);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action.run();
            }
        });
        window.add(btn);
    }

    private void addProfessor(Department dept) {
        String name = ask("Professor Name:");
        if (name == null) return;
        String id   = ask("Professor ID:");
        if (id == null) return;
        double sal  = Double.parseDouble(ask("Salary:"));
        try { dept.addProfessor(new Professor(name, id, sal)); print("Professor added!"); }
        catch (Exception e) { print("Error: " + e.getMessage()); }
    }

    private void removeProfessor(Department dept) {
        String id = ask("Professor ID to remove:");
        if (id == null) return;

        boolean removed = dept.removeProfessor(id);
        if (removed) {
            print("Professor removed.");
        } else {
            print("Professor not found.");
        }
    }

    private void searchProfessor(Department dept, JDialog parent) {
        String id = ask("Professor ID to search:");
        if (id == null) return;
        try {
            Professor p = dept.Search_professor(id);
            if (p != null && !p.getName().isEmpty()) openProfWindow(p, parent);
            else print("Professor not found.");
        } catch (Exception e) { print("Error: " + e.getMessage()); }
    }


//  PROFESSOR WINDOW

    private void openProfWindow(Professor p, JDialog parent) {
        JDialog window = new JDialog(parent, "Professor: " + p.getName(), true);
        window.setSize(300, 200);
        window.setLocationRelativeTo(parent);
        window.setLayout(new GridLayout(4, 1, 8, 8));
        window.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addBtn2(window, "Add Papers", new Runnable() {
            public void run() {
                p.setPapersPublished(Integer.parseInt(ask("Papers to add:")));
                print("Papers updated.");
            }
        });
        addBtn2(window, "Get Papers", new Runnable() {
            public void run() {
                print(p.getName() + " papers: " + p.getPaper_published());
            }
        });
        addBtn2(window, "Calculate Salary", new Runnable() {
            public void run() {
                print(p.getName() + " salary: " + p.calcuataSalary());
            }
        });
        addBtn2(window, "Back", new Runnable() {
            public void run() {
                window.dispose();
            }
        });

        window.setVisible(true);
    }

//  ADD STUDENT

    private void addStudent(Department dept, JDialog parent) {
        String[] types = {"Undergraduate", "Graduate", "Senior"};
        String type = (String) JOptionPane.showInputDialog(parent, "Student Type:",
                "Add Student", JOptionPane.PLAIN_MESSAGE, null, types, types[0]);
        if (type == null) return;

        String name = ask("Student Name:");
        if (name == null) return;
        String id   = ask("Student ID:");
        if (id == null) return;
        double gp   = Double.parseDouble(ask("Total Grade Points:"));
        double ch   = Double.parseDouble(ask("Total Credit Hours:"));

        try {
            if (type.equals("Undergraduate")) {
                int age = Integer.parseInt(ask("Age:"));
                Undergraduate_Student ug = new Undergraduate_Student(name, id, age);
                ug.calculateGPA(gp, ch);
                print(ug + " | Reward: " + ug.isReward());
            } else if (type.equals("Graduate")) {
                String degree = ask("Degree (MSc, PhD...):");
                Graduate_Student gs = new Graduate_Student(name, id, degree);
                gs.calculateGPA(gp, ch);
                print(gs + " | Reward: " + gs.isReward());
            } else if (type.equals("Senior")) {
                int age = Integer.parseInt(ask("Age:"));
                String title = ask("Project Title:");
                Senior_Student ss = new Senior_Student(name, id, age, title);
                ss.calculateGPA(gp, ch);
                print(ss + " | Reward: " + ss.isReward());
            }
        } catch (Exception e) { print("Error: " + e.getMessage()); }
    }

}
