import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("========================");
            System.out.println("|  STUDENT MANAGEMENT  |");
            System.out.println("========================");
            System.out.println("|1. Add Student        |");
            System.out.println("|2. Edit Student       |");
            System.out.println("|3. Delete Student     |");
            System.out.println("|4. Display Students   |");
            System.out.println("|5. Sort Students      |");
            System.out.println("|6. Search Students    |");
            System.out.println("|7. Save Data          |");
            System.out.println("|8. Load Data          |");
            System.out.println("|9. Exit               |");
            System.out.println("========================");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student marks: ");
                    double marks = Double.parseDouble(scanner.nextLine());
                    manager.addStudent(studentId, name, marks);
                    break;
                case "2":
                    System.out.print("Enter student ID to edit: ");
                    String editId = scanner.nextLine();
                    System.out.print("Enter new name : ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new marks : ");
                    String newMarksInput = scanner.nextLine();
                    Double newMarks = newMarksInput.isEmpty() ? null : Double.parseDouble(newMarksInput);
                    if (!manager.editStudent(editId, newName.isEmpty() ? null : newName, newMarks)) {
                        System.out.println("Student not found.");
                    }
                    break;
                case "3":
                    System.out.print("Enter student ID to delete: ");
                    String deleteId = scanner.nextLine();
                    if (!manager.deleteStudent(deleteId)) {
                        System.out.println("Student not found.");
                    }
                    break;
                case "4":
                    manager.displayStudents();
                    break;
                case "5":
                    System.out.println("\nSort Options:");
                    System.out.println("1. Sort by Marks");
                    System.out.println("2. Sort by Name");
                    System.out.print("Enter your choice: ");
                    String sortChoice = scanner.nextLine();
                    switch (sortChoice) {
                        case "1":
                            manager.sortStudentsByMarks();
                            System.out.println("Students sorted by Marks.");
                            break;
                        case "2":
                            manager.sortStudentsByName();
                            System.out.println("Students sorted by Name.");
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            break;
                    }
                    break;
                case "6":
                    System.out.print("Enter search term: ");
                    String searchTerm = scanner.nextLine();
                    System.out.println("Search Results:");
                    manager.searchStudents(searchTerm).forEach(System.out::println);
                    break;
                case "7":
                    System.out.print("Enter filename to save: ");
                    String saveFilename = scanner.nextLine();
                    try {
                        manager.saveToFile(saveFilename);
                        System.out.println("Data saved successfully.");
                    } catch (IOException e) {
                        System.out.println("An error occurred while saving the file.");
                    }
                    break;
                case "8":
                    System.out.print("Enter filename to load: ");
                    String loadFilename = scanner.nextLine();
                    try {
                        manager.loadFromFile(loadFilename);
                        System.out.println("Data loaded successfully.");
                    } catch (IOException e) {
                        System.out.println("An error occurred while loading the file.");
                    }
                    break;
                case "9":
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
}
