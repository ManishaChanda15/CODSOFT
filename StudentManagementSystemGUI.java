
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Student {
    private int id;
    private String grade;
    private int rollNo;
    private int age;
    private String section;

    public Student(int id, String grade, int rollNo, int age, String section) {
        this.id = id;
        this.grade = grade;
        this.rollNo = rollNo;
        this.age = age;
        this.section = section;
    }

    // Getters and Setters (You can generate them automatically in an IDE)
    public int getId() {
        return id;
    }

    public String getGrade() {
        return grade;
    }

    public int getRollNo() {
        return rollNo;
    }

    public int getAge() {
        return age;
    }

    public String getSection() {
        return section;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Grade: " + grade + ", Roll No: " + rollNo + ", Age: " + age + ", Section: " + section;
    }
}

public class StudentManagementSystemGUI extends JFrame implements ActionListener {
    private List<Student> students;
    private JTextField idField, gradeField, rollNoField, ageField, sectionField;
    private JTextArea outputArea;

    public StudentManagementSystemGUI() {
        students = new ArrayList<>();

        setTitle("Student Management System");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Student ID:"));
        idField = new JTextField(10);
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Grade:"));
        gradeField = new JTextField(10);
        inputPanel.add(gradeField);

        inputPanel.add(new JLabel("Roll No:"));
        rollNoField = new JTextField(10);
        inputPanel.add(rollNoField);

        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField(10);
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Section:"));
        sectionField = new JTextField(10);
        inputPanel.add(sectionField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(this);
        inputPanel.add(addButton);

        JButton removeButton = new JButton("Remove Student");
        removeButton.addActionListener(this);
        inputPanel.add(removeButton);

        // Output Area
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Student")) {
            int id = Integer.parseInt(idField.getText());
            String grade = gradeField.getText();
            int rollNo = Integer.parseInt(rollNoField.getText());
            int age = Integer.parseInt(ageField.getText());
            String section = sectionField.getText();

            Student newStudent = new Student(id, grade, rollNo, age, section);
            students.add(newStudent);

            outputArea.append("Student added: " + newStudent.toString() + "\n");

            // Clear input fields after adding
            idField.setText("");
            gradeField.setText("");
            rollNoField.setText("");
            ageField.setText("");
            sectionField.setText("");
        } else if (e.getActionCommand().equals("Remove Student")) {
            int rollNoToRemove = Integer.parseInt(rollNoField.getText());
            boolean removed = false;

            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getRollNo() == rollNoToRemove) {
                    students.remove(i);
                    removed = true;
                    break;
                }
            }

            if (removed) {
                outputArea.append("Student with Roll No " + rollNoToRemove + " removed successfully.\n");
            } else {
                outputArea.append("Student with Roll No " + rollNoToRemove + " not found.\n");
            }

            // Clear input fields after removing
            rollNoField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementSystemGUI frame = new StudentManagementSystemGUI();
            frame.setVisible(true);
        });
    }
}
