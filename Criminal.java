// Criminal.java
public class Criminal {
    private int id;
    private String name;
    private int age;
    private String crime;
    private String section;
    private String jail;

    private static final String DELIM = ","; // CSV delimiter

    public Criminal(int id, String name, int age, String crime, String section, String jail) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.crime = crime;
        this.section = section;
        this.jail = jail;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCrime() { return crime; }
    public String getSection() { return section; }
    public String getJail() { return jail; }

    // Convert object to CSV line
    public String toCSV() {
        return id + DELIM +
               sanitize(name) + DELIM +
               age + DELIM +
               sanitize(crime) + DELIM +
               sanitize(section) + DELIM +
               sanitize(jail);
    }

    private String sanitize(String s) {
        return s == null ? "" : s.replace(DELIM, " "); // prevent CSV break
    }

    // Create Criminal object from CSV line
    public static Criminal fromCSV(String line) {
        if (line == null || line.trim().isEmpty()) return null;
        String[] parts = line.split(",", -1);
        if (parts.length < 6) return null;
        try {
            int id = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            int age = Integer.parseInt(parts[2].trim());
            String crime = parts[3].trim();
            String section = parts[4].trim();
            String jail = parts[5].trim();
            return new Criminal(id, name, age, crime, section, jail);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Age: %d | Crime: %s | %s | Jail: %s",
                id, name, age, crime, section, jail);
    }
}
