package pharmacy;


public class Pharmacist {
private String id;
private String name;
private String licenseNumber;

public Pharmacist(String id, String name, String licenseNumber) {
        this.id = id;
        this.name = name;
this.licenseNumber = licenseNumber;
    }

public String getName() { return name; }
}
