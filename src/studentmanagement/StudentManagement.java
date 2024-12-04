package studentmanagement;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManagement {
    ArrayList<Student> studentList = new ArrayList<>();

    public void sortStudents(ArrayList<Student> studentList) {
        for (int i = 0; i < studentList.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < studentList.size(); j++) {
                if (studentList.get(j).getPoint() < studentList.get(minIndex).getPoint()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Student temp = studentList.get(i);
                studentList.set(i, studentList.get(minIndex));
                studentList.set(minIndex, temp);
            }
        }

        System.out.println("List of students after sorting by score:");
        for (Student student : studentList) {
            student.outputSV();
            System.out.println("Rank: " + getRank(student.getPoint()));
        }
    }

    public String getRank(float point) {
        if (point < 5.0) {
            return "Fail";
        } else if (point < 6.5) {
            return "Medium";
        } else if (point < 7.5) {
            return "Good";
        } else if (point < 9.0) {
            return "Very Good";
        } else {
            return "Excellent";
        }
    }

    public void displayStudentList(ArrayList<Student> studentList) {
        for (Student student : studentList) {
            student.outputSV();
        }
    }

    public void inputStudentList(int count) {
        for (int i = 0; i < count; i++) {
            System.out.println("Enter information for student number " + (i + 1) + ":");
            Student student = new Student();
            student.inputSV();
            studentList.add(student);
        }
    }

    public void searchStudent(ArrayList<Student> studentList, String name) {
        int found = 0;
        for (Student student : studentList) {
            if (student.getName().contains(name)) {
                student.outputSV();
                found = 1;
            }
        }
        if (found == 0) {
            System.out.println("No student found with that name.");
        }
    }

    public void updateStudent(String id) {
        for (Student student : studentList) {
            if (student.getID().equals(id)) {
                System.out.println("Re-enter information for student with ID: " + id);
                student.inputSV();
                System.out.println("Student information updated successfully!");
                return;
            }
        }
        System.out.println("No student found with ID: " + id);
    }

    public void deleteStudent(String id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getID().equals(id)) {
                studentList.remove(i);
                System.out.println("Deleted student with ID: " + id);
                return;
            }
        }
        System.out.println("No student found with ID: " + id);
    }

    public static void main(String[] args) {
        StudentManagement management = new StudentManagement();
        Scanner input = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("MENU");
            System.out.println("1. Enter student list");
            System.out.println("2. Display student information");
            System.out.println("3. Sort students by score");
            System.out.println("4. Search for a student");
            System.out.println("5. Update student information");
            System.out.println("6. Delete a student");
            System.out.println("7. Exit");

            while (true) {
                System.out.print("Enter your choice: ");
                if (input.hasNextInt()) {
                    choice = input.nextInt();
                    if (choice >= 1 && choice <= 7) {
                        break;
                    } else {
                        System.out.println("Invalid choice! Please choose between 1 and 7.");
                    }
                } else {
                    System.out.println("Invalid input! Please enter an integer.");
                    input.next();
                }
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter the number of students: ");
                    int n = input.nextInt();
                    management.inputStudentList(n);
                    break;
                case 2:
                    management.displayStudentList(management.studentList);
                    break;
                case 3:
                    management.sortStudents(management.studentList);
                    break;
                case 4:
                    input.nextLine(); 
                    System.out.print("Enter the name of the student to search for: ");
                    String name = input.nextLine();
                    management.searchStudent(management.studentList, name);
                    break;
                case 5:
                    input.nextLine(); 
                    System.out.print("Enter the ID of the student to update: ");
                    String idToUpdate = input.nextLine();
                    management.updateStudent(idToUpdate);
                    break;
                case 6:
                    input.nextLine(); 
                    System.out.print("Enter the ID of the student to delete: ");
                    String idToDelete = input.nextLine();
                    management.deleteStudent(idToDelete);
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid input! Please select a valid option.");
            }
        }
    }
}
