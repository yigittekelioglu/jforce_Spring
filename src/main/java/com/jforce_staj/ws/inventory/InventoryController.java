package com.jforce_staj.ws.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    
    @GetMapping("/filter")
    public ResponseEntity<List<Inventory>> getInventoriesByType(
            @RequestParam(required = false) InventoryType type) {
        
        List<Inventory> inventories = inventoryService.getInventoryByType(type);
        return ResponseEntity.ok(inventories);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
        return inventoryService.getInventoryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

   
    @PostMapping
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
        Inventory addedInventory = inventoryService.addInventory(inventory);
        return ResponseEntity.ok(addedInventory);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, 
                                                     @RequestBody Inventory inventory) {
        if (!inventoryService.getInventoryById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        inventory.setId(id);  
        Inventory updatedInventory = inventoryService.updateInventory(inventory);
        return ResponseEntity.ok(updatedInventory);
    }
}
