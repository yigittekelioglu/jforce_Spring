package com.jforce_staj.ws.inventoryassignment;

import com.jforce_staj.ws.inventory.Inventory;
import com.jforce_staj.ws.inventory.InventoryRepository;
import com.jforce_staj.ws.staff.Staff;
import com.jforce_staj.ws.staff.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InventoryAssignmentService {

    @Autowired
    private InventoryAssignmentRepository inventoryAssignmentRepo;

    @Autowired
    private StaffRepository staffRepo;

    @Autowired
    private InventoryRepository inventoryRepo;

    public List<Staff> findStaffByAdi(String adi) {
        return staffRepo.findByAdi(adi);
    }

    public Staff findStaffBySicilNumarasi(Long sicilNumarasi) {
        return staffRepo.findBySicilNumarasi(sicilNumarasi);
    }

    public List<InventoryAssignment> findInventoryAssignmentByStaff(Staff staff) {
        return inventoryAssignmentRepo.findByStaff(staff);
    }

    public void returnInventory(InventoryAssignment inventoryAssignment, Date returnDate) {
        inventoryAssignment.setReturnDate(returnDate);
        inventoryAssignmentRepo.save(inventoryAssignment);

        Inventory inventory = inventoryAssignment.getInventory();
        inventory.setStatus(Inventory.InventoryStatus.IN_STORAGE);
        inventoryRepo.save(inventory);
    }


    public InventoryAssignment assignInventoryToStaff(Staff staff, Inventory inventory, Date pickupDate) {
        InventoryAssignment assignment = new InventoryAssignment();
        assignment.setStaff(staff);
        assignment.setInventory(inventory);
        assignment.setPickupDate(pickupDate);
        inventoryAssignmentRepo.save(assignment);

        inventory.setStatus(Inventory.InventoryStatus.IN_PERSON);
        inventoryRepo.save(inventory);

        return assignment;
    }


    public List<Inventory> findUnassignedInventories() {
        return inventoryRepo.findByStatus(Inventory.InventoryStatus.IN_STORAGE);
    }

}
