package com.jforce_staj.ws.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

 
    
    @GetMapping("/types")
    public ResponseEntity<List<InventoryType>> getAllInventoryTypes() {
        List<InventoryType> allTypes = inventoryService.getAllInventoryTypes();
        return new ResponseEntity<>(allTypes, HttpStatus.OK);
        //return ResponseEntity.ok(allTypes);
    }

    
    @GetMapping("/filter")
    public ResponseEntity<List<Inventory>> getInventoriesByType(
            @RequestParam(required = false) String typeName) {

        List<Inventory> filterInventories = inventoryService.getInventoryByTypeName(typeName);
        return new ResponseEntity<>(filterInventories, HttpStatus.OK);
    }


    
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
        return new ResponseEntity<>(inventoryService.getInventoryById(id).get(), HttpStatus.OK);
    }

    //error
    @PostMapping
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
        Inventory addedInventory = inventoryService.addInventory(inventory);
        return new ResponseEntity<>(addedInventory, HttpStatus.OK);
    }

    //error
    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, 
                                                     @RequestBody Inventory inventory) {
    	/*
        if (!inventoryService.getInventoryById(id).isPresent()) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
        	inventory.setId(id);  
            Inventory updatedInventory = inventoryService.updateInventory(inventory);
            return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
        }*/
        inventory.setId(id);  
        Inventory updatedInventory = inventoryService.updateInventory(inventory);
        return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
    }
}