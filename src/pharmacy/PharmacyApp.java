package pharmacy;



import java.time.LocalDate;
import java.util.*;

public class PharmacyApp {
private static final Scanner scanner = new Scanner(System.in);

private static Map<String, Medicine> medicines = new HashMap<>();
private static Map<String, Customer> customers = new HashMap<>();
private static Map<String, Prescription> prescriptions = new HashMap<>();

private static Pharmacist pharmacist = new Pharmacist("P001", "Alice", "LIC12345");

public static void main(String[] args) {
boolean exit = false;

while (!exit) {
System.out.println("\n--- Pharmacy Management System ---");
System.out.println("1. Add Medicine");
System.out.println("2. Add Customer");
System.out.println("3. Add Prescription");
System.out.println("4. Make Sale");
System.out.println("5. Display Medicines");
System.out.println("6. Exit");
System.out.print("Enter your choice: ");
int choice = scanner.nextInt();
scanner.nextLine(); // consume newline

switch (choice) {
case 1 ->addMedicine();
case 2 ->addCustomer();
case 3 ->addPrescription();
case 4 ->makeSale();
case 5 ->displayMedicines();
case 6 -> exit = true;
default ->System.out.println("Invalid choice!");
            }
        }
    }

private static void addMedicine() {
System.out.print("Medicine ID: ");
        String id = scanner.nextLine();
System.out.print("Name: ");
        String name = scanner.nextLine();
System.out.print("Stock: ");
int stock = scanner.nextInt();
System.out.print("Price: ");
double price = scanner.nextDouble();
scanner.nextLine(); // consume newline
System.out.print("Expiry Date (yyyy-mm-dd): ");
LocalDate expiry = LocalDate.parse(scanner.nextLine());
System.out.print("Requires Prescription? (true/false): ");
boolean requiresPrescription = scanner.nextBoolean();
scanner.nextLine(); // consume newline

        Medicine med = new Medicine(id, name, stock, price, expiry, requiresPrescription);
medicines.put(id, med);
System.out.println("✅ Medicine added.");
    }

private static void addCustomer() {
System.out.print("Customer ID: ");
        String id = scanner.nextLine();
System.out.print("Name: ");
        String name = scanner.nextLine();
System.out.print("Age: ");
int age = scanner.nextInt();
scanner.nextLine(); // consume newline

        Customer customer = new Customer(id, name, age);
customers.put(id, customer);
System.out.println("✅ Customer added.");
    }

private static void addPrescription() {
System.out.print("Prescription ID: ");
        String prescId = scanner.nextLine();
System.out.print("Customer ID: ");
        String customerId = scanner.nextLine();
        Customer customer = customers.get(customerId);

if (customer == null) {
System.out.println("❌ Customer not found.");
return;
        }

System.out.print("Doctor Name: ");
        String doctor = scanner.nextLine();

        Prescription prescription = new Prescription(prescId, customer, doctor, LocalDate.now());

boolean addMore = true;
while (addMore) {
System.out.print("Medicine ID: ");
            String medId = scanner.nextLine();
            Medicine med = medicines.get(medId);
if (med == null) {
System.out.println("❌ Medicine not found.");
continue;
            }

System.out.print("Quantity: ");
int qty = scanner.nextInt();
scanner.nextLine(); // consume newline

prescription.addItem(new PrescriptionItem(med, qty));

System.out.print("Add another medicine to prescription? (yes/no): ");
addMore = scanner.nextLine().equalsIgnoreCase("yes");
        }

prescriptions.put(prescId, prescription);
System.out.println("✅ Prescription added.");
    }

private static void makeSale() {
System.out.print("Customer ID: ");
        String customerId = scanner.nextLine();
        Customer customer = customers.get(customerId);
if (customer == null) {
System.out.println("❌ Customer not found.");
return;
        }

        Sale sale = new Sale(UUID.randomUUID().toString(), customer, pharmacist);

        Prescription prescription = null;
System.out.print("Do you have a prescription? (yes/no): ");
        String hasPresc = scanner.nextLine();
if (hasPresc.equalsIgnoreCase("yes")) {
System.out.print("Prescription ID: ");
            String prescId = scanner.nextLine();
prescription = prescriptions.get(prescId);
if (prescription == null || !prescription.getCustomer().getId().equals(customerId)) {
System.out.println("❌ Invalid prescription.");
return;
            }
        }

boolean addMore = true;
while (addMore) {
System.out.print("Medicine ID: ");
            String medId = scanner.nextLine();
            Medicine med = medicines.get(medId);

if (med == null) {
System.out.println("❌ Medicine not found.");
continue;
            }

if (med.isExpired()) {
System.out.println("⚠️ This medicine is expired and cannot be sold.");
continue;
            }

System.out.print("Quantity: ");
int qty = scanner.nextInt();
scanner.nextLine(); // consume newline

if (qty>med.getStock()) {
System.out.println("❌ Not enough stock.");
continue;
            }

if (med.isPrescriptionRequired()) {
if (prescription == null) {
System.out.println("❌ This medicine requires a valid prescription.");
continue;
                }

PrescriptionItem prescItem = prescription.findItemByMedicineId(medId);
if (prescItem == null || qty>prescItem.getQuantity()) {
System.out.println("❌ Quantity exceeds prescription allowance or medicine not found in prescription.");
continue;
                }

sale.addDetail(new SaleDetail(med, qty, prescItem));
            } else {
sale.addDetail(new SaleDetail(med, qty, null));
            }

med.reduceStock(qty);

System.out.print("Add another medicine to sale? (yes/no): ");
addMore = scanner.nextLine().equalsIgnoreCase("yes");
        }

sale.printReceipt();
    }

private static void displayMedicines() {
System.out.println("\n--- Medicine List ---");
for (Medicine med : medicines.values()) {
System.out.println(med.getId() + " | " + med.getName() +
" | Stock: " + med.getStock() +
" | Price: $" + med.getPrice() +
                    (med.isPrescriptionRequired() ? " | Rx" : "") +
                    (med.isExpired() ? " | [EXPIRED]" : ""));
        }
    }
}

