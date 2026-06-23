package pharmacy;



public class SaleDetail {
private Medicine medicine;
private int quantity;
private double price;
private PrescriptionItem prescriptionItem;

public SaleDetail(Medicine medicine, int quantity, PrescriptionItem prescriptionItem) {
this.medicine = medicine;
this.quantity = quantity;
this.price = medicine.getPrice() * quantity;
this.prescriptionItem = prescriptionItem;
    }

public double getPrice() {
return price;
    }

public Medicine getMedicine() {
return medicine;
    }

public String getDetailLine() {
return medicine.getName() + " x" + quantity + " = $" + price +
                (medicine.isPrescriptionRequired() ? " (Prescription Required)" : "") +
                (medicine.isExpired() ? " [EXPIRED!]" : "");
    }
}

