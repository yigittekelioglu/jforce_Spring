package com.jforce_staj.ws.inventoryassignment;

import com.jforce_staj.ws.inventory.Inventory; 
import com.jforce_staj.ws.inventory.InventoryRepository;
import com.jforce_staj.ws.staff.Staff;
import com.jforce_staj.ws.staff.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

@Service
public class InventoryAssignmentService {

    @Autowired
    InventoryAssignmentRepository inventoryAssignmentRepo;

    @Autowired
    StaffRepository staffRepo;

    @Autowired
    InventoryRepository inventoryRepo;
    
    public List<Staff> filterStaff(String adi, Long sicilNumarasi){
    	return staffRepo.findByAdiandSicilNumarasi(adi, sicilNumarasi);
    }

    
    public List<InventoryAssignment> findInventoryAssignmentByStaff(Staff staff) {
        return inventoryAssignmentRepo.findByStaff(staff);
    }

    public void returnInventory(InventoryAssignment inventoryAssignment, Date returnDate) {
        
        inventoryAssignment.setReturnDate(returnDate);
        Inventory inventory = inventoryAssignment.getInventory();
        inventory.setStatus(Inventory.InventoryStatus.IN_STORAGE);
        inventoryRepo.save(inventory);
        
        inventoryAssignmentRepo.delete(inventoryAssignment);
    }
   
    
    public InventoryAssignment assignInventoryToStaff(InventoryAssignmentRequest request) {
    	
    	System.out.println("Staff ID: " + request.getStaffId());
        System.out.println("Inventory ID: " + request.getInventoryId());
    	
        Staff staff = staffRepo.findById(request.getStaffId()).orElse(null);
        Inventory inventory = inventoryRepo.findById(request.getInventoryId()).orElse(null);
   

        InventoryAssignment assignment = new InventoryAssignment();
        assignment.setStaff(staff);
        assignment.setInventory(inventory);
        assignment.setPickupDate(request.getPickupDate());
        
        inventoryAssignmentRepo.save(assignment);

        inventory.setStatus(Inventory.InventoryStatus.IN_PERSON);
        inventoryRepo.save(inventory);

        return inventoryAssignmentRepo.save(assignment);
    }


    public List<Inventory> findUnassignedInventories() {
        return inventoryRepo.findByStatus(Inventory.InventoryStatus.IN_STORAGE);
    }

}
