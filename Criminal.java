// Criminal.java
public class Criminal {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String crime;
    private String section;
    private String punishment;
    private String severity;
    private String status;
    private String dateOfCrime;
    private String jailName;
    private String policeStation;
    private String crimeLocation;
    private int previousConvictions;
    private double bailAmount;
    private String accomplices;
    private String notes;
    private String nationality;
    private double height;
    private double weight;
    private String eyeColor;
    private String hairColor;
    private String fingerprintId;
    private String photoFile;
    private String createdAt;

    private static final String DELIM = ",";

    public Criminal(int id, String name, int age, String gender, String crime, String section, String punishment,
                    String severity, String status, String dateOfCrime, String jailName, String policeStation,
                    String crimeLocation, int previousConvictions, double bailAmount, String accomplices, String notes,
                    String nationality, double height, double weight, String eyeColor, String hairColor,
                    String fingerprintId, String photoFile, String createdAt) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.crime = crime;
        this.section = section;
        this.punishment = punishment;
        this.severity = severity;
        this.status = status;
        this.dateOfCrime = dateOfCrime;
        this.jailName = jailName;
        this.policeStation = policeStation;
        this.crimeLocation = crimeLocation;
        this.previousConvictions = previousConvictions;
        this.bailAmount = bailAmount;
        this.accomplices = accomplices;
        this.notes = notes;
        this.nationality = nationality;
        this.height = height;
        this.weight = weight;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.fingerprintId = fingerprintId;
        this.photoFile = photoFile;
        this.createdAt = createdAt;
    }

    // ------------------- GETTERS -------------------
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getCrime() { return crime; }
    public String getSection() { return section; }
    public String getPunishment() { return punishment; }
    public String getSeverity() { return severity; }
    public String getStatus() { return status; }
    public String getDateOfCrime() { return dateOfCrime; }
    public String getJailName() { return jailName; }
    public String getPoliceStation() { return policeStation; }
    public String getCrimeLocation() { return crimeLocation; }
    public int getPreviousConvictions() { return previousConvictions; }
    public double getBailAmount() { return bailAmount; }
    public String getAccomplices() { return accomplices; }
    public String getNotes() { return notes; }
    public String getNationality() { return nationality; }
    public double getHeight() { return height; }
    public double getWeight() { return weight; }
    public String getEyeColor() { return eyeColor; }
    public String getHairColor() { return hairColor; }
    public String getFingerprintId() { return fingerprintId; }
    public String getPhotoFile() { return photoFile; }
    public String getCreatedAt() { return createdAt; }

    // ------------------- CSV Methods -------------------
    public String toCSV() {
        return id + DELIM + sanitize(name) + DELIM + age + DELIM + sanitize(gender) + DELIM + sanitize(crime) + DELIM +
               sanitize(section) + DELIM + sanitize(punishment) + DELIM + sanitize(severity) + DELIM +
               sanitize(status) + DELIM + sanitize(dateOfCrime) + DELIM + sanitize(jailName) + DELIM +
               sanitize(policeStation) + DELIM + sanitize(crimeLocation) + DELIM + previousConvictions + DELIM +
               bailAmount + DELIM + sanitize(accomplices) + DELIM + sanitize(notes) + DELIM + sanitize(nationality) +
               DELIM + height + DELIM + weight + DELIM + sanitize(eyeColor) + DELIM + sanitize(hairColor) + DELIM +
               sanitize(fingerprintId) + DELIM + sanitize(photoFile) + DELIM + sanitize(createdAt);
    }

    private String sanitize(String s) {
        return s == null ? "" : s.replace(DELIM, " ");
    }

    public static Criminal fromCSV(String line) {
        if (line == null || line.trim().isEmpty()) return null;
        String[] parts = line.split(",", -1);
        if (parts.length < 25) return null;
        try {
            return new Criminal(
                Integer.parseInt(parts[0].trim()),
                parts[1].trim(),
                Integer.parseInt(parts[2].trim()),
                parts[3].trim(),
                parts[4].trim(),
                parts[5].trim(),
                parts[6].trim(),
                parts[7].trim(),
                parts[8].trim(),
                parts[9].trim(),
                parts[10].trim(),
                parts[11].trim(),
                parts[12].trim(),
                Integer.parseInt(parts[13].trim()),
                Double.parseDouble(parts[14].trim()),
                parts[15].trim(),
                parts[16].trim(),
                parts[17].trim(),
                Double.parseDouble(parts[18].trim()),
                Double.parseDouble(parts[19].trim()),
                parts[20].trim(),
                parts[21].trim(),
                parts[22].trim(),
                parts[23].trim(),
                parts[24].trim()
            );
        } catch (Exception e) { return null; }
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Age: " + age + " | Crime: " + crime + " | Section: " + section +
               " | Punishment: " + punishment + " | Severity: " + severity + " | Status: " + status +
               " | Jail: " + jailName + " | Police Station: " + policeStation + " | Date: " + dateOfCrime;
    }
}
