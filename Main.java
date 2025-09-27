// Main.java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CriminalManager mgr = new CriminalManager("data/criminals.csv"); // CSV file

        while (true) {
            printMenu();
            String choiceStr = sc.nextLine().trim();
            int choice = -1;
            try { choice = Integer.parseInt(choiceStr); } catch (Exception e) { System.out.println("Enter valid number."); continue; }

            switch (choice) {
                case 1: // Add
                    try {
                        System.out.print("Enter ID: "); int id = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Name: "); String name = sc.nextLine().trim();
                        System.out.print("Age: "); int age = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Crime: "); String crime = sc.nextLine().trim();
                        System.out.print("Section: "); String section = sc.nextLine().trim();
                        System.out.print("Jail: "); String jail = sc.nextLine().trim();
                        Criminal c = new Criminal(id, name, age, crime, section, jail);
                        if (mgr.addRecord(c)) System.out.println("Added and saved.");
                        else System.out.println("ID already exists! Use a unique ID.");
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid number input. Try again.");
                    }
                    break;

                case 2: // View all
                    mgr.viewAll();
                    break;

                case 3: // Search by ID
                    System.out.print("Enter ID to search: ");
                    try {
                        int id = Integer.parseInt(sc.nextLine().trim());
                        Criminal found = mgr.findById(id);
                        if (found != null) System.out.println("Found: " + found);
                        else System.out.println("No record found for ID " + id);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid numeric ID.");
                    }
                    break;

                case 4: // Delete
                    System.out.print("Enter ID to delete: ");
                    try {
                        int id = Integer.parseInt(sc.nextLine().trim());
                        if (mgr.deleteById(id)) System.out.println("Deleted and saved.");
                        else System.out.println("No record with that ID.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID.");
                    }
                    break;

                case 5: // Exit
                    System.out.println("Exiting. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Pick between 1-5.");
            }
        }
    }

    static void printMenu() {
        System.out.println("\n--- Criminal Record System ---");
        System.out.println("1. Add Record");
        System.out.println("2. View Records");
        System.out.println("3. Search Record (by ID)");
        System.out.println("4. Delete Record");
        System.out.println("5. Exit");
        System.out.print("Choose: ");
    }
}
