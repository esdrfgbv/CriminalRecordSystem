// CriminalManager.java
import java.io.*;
import java.util.*;

public class CriminalManager {
    private List<Criminal> criminals = new ArrayList<>();
    private final String csvFile;

    public CriminalManager(String csvFile) {
        this.csvFile = csvFile;
        loadFromCSV();
    }

    // Load all records from CSV
    public void loadFromCSV() {
        criminals.clear();
        File file = new File(csvFile);
        if (!file.exists()) {
            System.out.println("⚠️ CSV file not found. Starting fresh.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Criminal c = Criminal.fromCSV(line);
                if (c != null) criminals.add(c);
                else System.out.println("⚠️ Skipped malformed line: " + line);
            }
            System.out.println("✅ Loaded " + criminals.size() + " records from CSV.");
        } catch (IOException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
    }

    // Save all records to CSV
    public void saveToCSV() {
        File file = new File(csvFile);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) parent.mkdirs();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Criminal c : criminals) {
                bw.write(c.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }

    public boolean addRecord(Criminal c) {
        if (findById(c.getId()) != null) return false; // duplicate
        criminals.add(c);
        saveToCSV();
        return true;
    }

    public Criminal findById(int id) {
        for (Criminal c : criminals) if (c.getId() == id) return c;
        return null;
    }

    public boolean deleteById(int id) {
        Criminal c = findById(id);
        if (c == null) return false;
        criminals.remove(c);
        saveToCSV();
        return true;
    }

    public void viewAll() {
        if (criminals.isEmpty()) {
            System.out.println("No records to show.");
            return;
        }
        System.out.println("---- All Records ----");
        for (Criminal c : criminals) System.out.println(c);
    }

    public List<Criminal> searchByCrime(String keyword) {
        List<Criminal> res = new ArrayList<>();
        for (Criminal c : criminals) {
            if (c.getCrime().toLowerCase().contains(keyword.toLowerCase())) res.add(c);
        }
        return res;
    }
}
