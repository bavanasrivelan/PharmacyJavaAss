package pharmacy;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Prescription {
private String id;
private Customer customer;
private String doctorName;
private LocalDate dateIssued;
private List<PrescriptionItem> items;

public Prescription(String id, Customer customer, String doctorName, LocalDate dateIssued) {
        this.id = id;
this.customer = customer;
this.doctorName = doctorName;
this.dateIssued = dateIssued;
this.items = new ArrayList<>();
    }

public void addItem(PrescriptionItem item) {
items.add(item);
    }

public List<PrescriptionItem>getItems() {
return items;
    }

public Customer getCustomer() {
return customer;
    }

public PrescriptionItem findItemByMedicineId(String medId) {
for (PrescriptionItem item : items) {
if (item.getMedicine().getId().equals(medId)) {
return item;
            }
        }
return null;
    }
}
