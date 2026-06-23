package pharmacy;


import java.time.LocalDate;

public class Medicine {
private String id;
private String name;
private int stock;
private double price;
private LocalDate expiryDate;
private boolean prescriptionRequired;

public Medicine(String id, String name, int stock, double price, LocalDate expiryDate, boolean prescriptionRequired) {
        this.id = id;
        this.name = name;
this.stock = stock;
this.price = price;
this.expiryDate = expiryDate;
this.prescriptionRequired = prescriptionRequired;
    }

public boolean isExpired() {
return expiryDate.isBefore(LocalDate.now());
    }

public void reduceStock(int quantity) {
if (quantity <= stock) {
stock -= quantity;
        }
    }

public String getId() { return id; }
public String getName() { return name; }
public int getStock() { return stock; }
public double getPrice() { return price; }
public LocalDate getExpiryDate() { return expiryDate; }
public boolean isPrescriptionRequired() { return prescriptionRequired; }
}

