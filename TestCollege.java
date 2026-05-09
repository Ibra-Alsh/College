import javax.swing.JOptionPane;

public class TestCollege {
    public static void main(String[] args) {
        //user enters name and max number of deps
        String name = JOptionPane.showInputDialog("Enter College name:");
        int max = Integer.parseInt(JOptionPane.showInputDialog("Enter maximum number of departments:"));
        
        //create college object and launch GUI
        College college = new College(name);
        new GUI(college);
    }
}
