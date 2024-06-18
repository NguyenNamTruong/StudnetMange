import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class StudentManager {
    private LinkedList<Student> students;

    public StudentManager() {
        this.students = new LinkedList<>();
    }

    public void addStudent(String studentId, String name, double marks) {
        Student student = new Student(studentId, name, marks);
        students.add(student);
    }

    public boolean editStudent(String studentId, String name, Double marks) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                if (name != null) {
                    student.setName(name);
                }
                if (marks != null) {
                    student.setMarks(marks);
                }
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String studentId) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(studentId)) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    public void displayStudents() {
        System.out.printf("%-10s%-20s%-10s%-10s%n", "ID", "Name", "Marks", "Rank");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Student student : students) {
                writer.write(student.getStudentId() + "," + student.getName() + "," + student.getMarks());
                writer.newLine();
            }
        }
    }

    public void loadFromFile(String filename) throws IOException {
        students.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String studentId = parts[0];
                    String name = parts[1];
                    double marks = Double.parseDouble(parts[2]);
                    addStudent(studentId, name, marks);
                }
            }
        }
    }

    public void sortStudentsByMarks() {
        quickSort(students, Comparator.comparingDouble(Student::getMarks));
    }

    public void sortStudentsByName() {
        quickSort(students, Comparator.comparing(Student::getName));
    }

    public LinkedList<Student> searchStudents(String searchTerm) {
        LinkedList<Student> result = new LinkedList<>();
        for (Student student : students) {
            if (student.getStudentId().contains(searchTerm) || student.getName().contains(searchTerm)) {
                result.add(student);
            }
        }
        return result;
    }

    private void quickSort(LinkedList<Student> list, Comparator<Student> comparator) {
        if (list.size() <= 1) {
            return;
        }
        Student pivot = list.getFirst();
        LinkedList<Student> lesser = new LinkedList<>();
        LinkedList<Student> greater = new LinkedList<>();
        for (Student student : list) {
            if (comparator.compare(student, pivot) < 0) {
                lesser.add(student);
            } else if (comparator.compare(student, pivot) > 0) {
                greater.add(student);
            }
        }
        quickSort(lesser, comparator);
        quickSort(greater, comparator);
        list.clear();
        list.addAll(lesser);
        list.add(pivot);
        list.addAll(greater);
    }
}
