
// Main.java
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CriminalManager mgr = new CriminalManager("data/criminals.csv");

        while (true) {
            printMenu();
            String choiceStr = sc.nextLine().trim();
            int choice = -1;
            try { choice = Integer.parseInt(choiceStr); } catch (Exception e) { System.out.println("Enter valid number."); continue; }

            switch (choice) {
                case 1: // Add Record
                    try {
                        System.out.print("ID: "); int id = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Name: "); String name = sc.nextLine().trim();
                        System.out.print("Age: "); int age = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Gender: "); String gender = sc.nextLine().trim();
                        System.out.print("Crime: "); String crime = sc.nextLine().trim();
                        System.out.print("Section: "); String section = sc.nextLine().trim();
                        System.out.print("Punishment: "); String punishment = sc.nextLine().trim();
                        System.out.print("Severity: "); String severity = sc.nextLine().trim();
                        System.out.print("Status: "); String status = sc.nextLine().trim();
                        System.out.print("Date of Crime (YYYY-MM-DD): "); String dateOfCrime = sc.nextLine().trim();
                        System.out.print("Jail Name: "); String jailName = sc.nextLine().trim();
                        System.out.print("Police Station: "); String ps = sc.nextLine().trim();
                        System.out.print("Crime Location: "); String crimeLoc = sc.nextLine().trim();
                        System.out.print("Previous Convictions: "); int prev = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Bail Amount: "); double bail = Double.parseDouble(sc.nextLine().trim());
                        System.out.print("Accomplices: "); String acc = sc.nextLine().trim();
                        System.out.print("Notes: "); String notes = sc.nextLine().trim();
                        System.out.print("Nationality: "); String nation = sc.nextLine().trim();
                        System.out.print("Height (cm): "); double h = Double.parseDouble(sc.nextLine().trim());
                        System.out.print("Weight (kg): "); double w = Double.parseDouble(sc.nextLine().trim());
                        System.out.print("Eye Color: "); String eye = sc.nextLine().trim();
                        System.out.print("Hair Color: "); String hair = sc.nextLine().trim();
                        System.out.print("Fingerprint ID: "); String fp = sc.nextLine().trim();
                        System.out.print("Photo File: "); String photo = sc.nextLine().trim();
                        String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                        Criminal c = new Criminal(id, name, age, gender, crime, section, punishment, severity, status,
                                dateOfCrime, jailName, ps, crimeLoc, prev, bail, acc, notes, nation, h, w, eye, hair, fp, photo, createdAt);

                        if (mgr.addRecord(c)) System.out.println("Record added.");
                        else System.out.println("ID already exists.");
                    } catch (Exception e) { System.out.println("Invalid input, record not added."); }
                    break;

                case 2: mgr.viewAll(); break;
                case 3:
                    System.out.print("Enter ID to search: ");
                    try {
                        int id = Integer.parseInt(sc.nextLine().trim());
                        Criminal c = mgr.findById(id);
                        if (c != null) System.out.println(c);
                        else System.out.println("Record not found.");
                    } catch (Exception e) { System.out.println("Invalid ID."); }
                    break;

                case 4:
                    System.out.print("Enter ID to delete: ");
                    try {
                        int id = Integer.parseInt(sc.nextLine().trim());
                        if (mgr.deleteById(id)) System.out.println("Record deleted.");
                        else System.out.println("Record not found.");
                    } catch (Exception e) { System.out.println("Invalid ID."); }
                    break;

                case 5:
                    System.out.print("Enter Crime keyword to search: ");
                    String keyword = sc.nextLine().trim();
                    List<Criminal> list = mgr.searchByCrime(keyword);
                    if (list.isEmpty()) System.out.println("No records found.");
                    else list.forEach(System.out::println);
                    break;

                case 6: System.out.println("Exiting..."); sc.close(); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Criminal Record System ---");
        System.out.println("1. Add Record");
        System.out.println("2. View All Records");
        System.out.println("3. Search by ID");
        System.out.println("4. Delete Record");
        System.out.println("5. Search by Crime");
        System.out.println("6. Exit");
        System.out.print("Choose: ");
    }
}
