package pharmacy;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sale {
private String id;
private LocalDate date;
private Customer customer;
private Pharmacist pharmacist;
private List<SaleDetail> details;

public Sale(String id, Customer customer, Pharmacist pharmacist) {
        this.id = id;
this.date = LocalDate.now();
this.customer = customer;
this.pharmacist = pharmacist;
this.details = new ArrayList<>();
    }

public void addDetail(SaleDetail detail) {
details.add(detail);
    }

public double getTotal() {
double total = 0;
for (SaleDetail d : details) {
total += d.getPrice();
        }
return total;
    }

public void printReceipt() {
System.out.println("\n=== Sale Receipt ===");
System.out.println("Customer: " + customer.getName());
System.out.println("Pharmacist: " + pharmacist.getName());
for (SaleDetail d : details) {
System.out.println(d.getDetailLine());
        }
System.out.printf("Total: $%.2f\n", getTotal());
System.out.println("=====================");
    }
}

