package com.jforce_staj.ws.inventoryassignment;

import com.jforce_staj.ws.inventory.Inventory;
import com.jforce_staj.ws.inventory.InventoryRepository;
import com.jforce_staj.ws.staff.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory-assignment")
public class InventoryAssignmentController {

    @Autowired
    InventoryAssignmentService inventoryAssignmentService;
    
    @Autowired
    InventoryRepository inventoryRepository;
    
    @Autowired
    InventoryAssignmentRepository inventoryAssignmentRepository;


    
    
    @GetMapping("/staff-search")
    public ResponseEntity<List<Staff>> searchStaff(@RequestParam(required = false) String adi, @RequestParam(required = false) Long sicilNumarasi) {
    	List<Staff> filteredStaffs = inventoryAssignmentService.filterStaff(adi, sicilNumarasi);
        return new ResponseEntity<>(filteredStaffs, HttpStatus.OK);
    }

    @GetMapping("/assignments/{staffId}")
    public ResponseEntity<List<InventoryAssignment>> getAssignmentsByStaff(@PathVariable Long staffId) {
    	Staff staff = new Staff();
        staff.setId(staffId);
        
        List<InventoryAssignment> staffInventory = inventoryAssignmentService.findInventoryAssignmentByStaff(staff);
        return new ResponseEntity<>(staffInventory, HttpStatus.OK);
    	
        
    }
    
    @PostMapping("/return-inventory")
    public ResponseEntity<Void> returnInventory(@RequestBody InventoryReturnRequest request) {
        
    	Optional<InventoryAssignment> returnAssignment = inventoryAssignmentRepository.findByInventoryId(request.getInventoryId());

        InventoryAssignment assignInventory = returnAssignment.get();
        inventoryAssignmentService.returnInventory(assignInventory, request.getReturnDate());
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/assign")
    public ResponseEntity<InventoryAssignment> assignInventoryToStaff(@RequestBody InventoryAssignmentRequest request) {
        InventoryAssignment assignment = inventoryAssignmentService.assignInventoryToStaff(request);
        return new ResponseEntity<>(assignment, HttpStatus.OK);
    }


    
    @GetMapping("/unassigned-inventories")
    public ResponseEntity<List<Inventory>> getUnassignedInventories() {
    	List<Inventory> unassignedInventories = inventoryAssignmentService.findUnassignedInventories();
    	return new ResponseEntity<>(unassignedInventories, HttpStatus.OK);
    }
}
