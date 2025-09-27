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

    public void loadFromCSV() {
        criminals.clear();
        File file = new File(csvFile);
        if (!file.exists()) {
            System.out.println("CSV file not found. Starting fresh.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) { firstLine = false; continue; } // skip header
                Criminal c = Criminal.fromCSV(line);
                if (c != null) criminals.add(c);
            }
            System.out.println("Loaded " + criminals.size() + " records.");
        } catch (IOException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
    }

    public void saveToCSV() {
        File file = new File(csvFile);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) parent.mkdirs();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            // Write header
            bw.write("ID,Name,Age,Gender,Crime,Section,Punishment,Severity,Status,DateOfCrime,JailName,PoliceStation,CrimeLocation,PreviousConvictions,BailAmount,Accomplices,Notes,Nationality,Height,Weight,EyeColor,HairColor,FingerprintID,PhotoFile,CreatedAt");
            bw.newLine();
            // Write data
            for (Criminal c : criminals) {
                bw.write(c.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }

    public boolean addRecord(Criminal c) {
        if (findById(c.getId()) != null) return false;
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
        System.out.println("---- All Criminal Records ----");
        for (Criminal c : criminals) System.out.println(c);
    }

    public List<Criminal> searchByCrime(String keyword) {
        List<Criminal> res = new ArrayList<>();
        for (Criminal c : criminals)
            if (c.getCrime().toLowerCase().contains(keyword.toLowerCase()))
                res.add(c);
        return res;
    }
}
