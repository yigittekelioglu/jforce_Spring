package com.jforce_staj.ws.inventoryassignment;

import com.jforce_staj.ws.inventory.Inventory;
import com.jforce_staj.ws.staff.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/inventory-assignment")
public class InventoryAssignmentController {

    @Autowired
    private InventoryAssignmentService inventoryAssignmentService;

    @GetMapping("/staff-search")
    public ResponseEntity<List<Staff>> searchStaff(@RequestParam(required = false) String adi, @RequestParam(required = false) Long sicilNumarasi) {
        if (adi != null) {
            return ResponseEntity.ok(inventoryAssignmentService.findStaffByAdi(adi));
        } else if (sicilNumarasi != null) {
            Staff staff = inventoryAssignmentService.findStaffBySicilNumarasi(sicilNumarasi);
            return ResponseEntity.ok(List.of(staff)); 
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/assignments/{staffId}")
    public ResponseEntity<List<InventoryAssignment>> getAssignmentsByStaff(@PathVariable Long staffId) {
        Staff staff = new Staff();
        staff.setId(staffId);
        return ResponseEntity.ok(inventoryAssignmentService.findInventoryAssignmentByStaff(staff));
    }

    @PostMapping("/return-inventory")
    public ResponseEntity<InventoryAssignment> returnInventory(@RequestBody InventoryAssignment inventoryAssignment, @RequestParam Date returnDate) {
        inventoryAssignmentService.returnInventory(inventoryAssignment, returnDate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/assign")
    public ResponseEntity<InventoryAssignment> assignInventoryToStaff(@RequestBody Staff staff, @RequestBody Inventory inventory, @RequestParam Date pickupDate) {
        InventoryAssignment assignment = inventoryAssignmentService.assignInventoryToStaff(staff, inventory, pickupDate);
        return ResponseEntity.ok(assignment);
    }

    @GetMapping("/unassigned-inventories")
    public ResponseEntity<List<Inventory>> getUnassignedInventories() {
        return ResponseEntity.ok(inventoryAssignmentService.findUnassignedInventories());
    }
}
