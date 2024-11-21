import java.io.*;
import java.util.*;

class Student {
    String name, rollNumber;
    int marks;

    public Student(String name, String rollNumber, int marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }

    public String toString() {
        return name + "," + rollNumber + "," + marks;
    }
}

public class StudentRecordsManager {
    private static final String FILE_PATH = "students.csv";
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        loadStudents();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. View\n2. Search\n3. Add\n4. Update\n5. Delete\n6. Exit");
            switch (sc.nextInt()) {
                case 1 -> students.forEach(System.out::println);
                case 2 -> search(sc.next());
                case 3 -> students.add(new Student(sc.next(), sc.next(), sc.nextInt()));
                case 4 -> update(sc.next(), sc.next(), sc.nextInt());
                case 5 -> students.removeIf(s -> s.rollNumber.equals(sc.next()));
                case 6 -> { saveStudents(); return; }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private static void loadStudents() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            br.lines().map(line -> line.split(",")).forEach(parts ->
                    students.add(new Student(parts[0], parts[1], Integer.parseInt(parts[2]))));
        } catch (IOException e) {
            System.err.println("Error loading: " + e.getMessage());
        }
    }

    private static void saveStudents() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student s : students) bw.write(s + "\n");
        } catch (IOException e) {
            System.err.println("Error saving: " + e.getMessage());
        }
    }

    private static void search(String roll) {
        students.stream().filter(s -> s.rollNumber.equals(roll)).forEach(System.out::println);
    }

    private static void update(String roll, String name, int marks) {
        students.stream().filter(s -> s.rollNumber.equals(roll)).findFirst()
                .ifPresentOrElse(s -> { s.name = name; s.marks = marks; },
                        () -> System.out.println("Student not found"));
    }
}
