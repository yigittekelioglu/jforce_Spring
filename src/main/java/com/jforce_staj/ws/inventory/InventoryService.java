package com.jforce_staj.ws.inventory;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    InventoryTypeRepository inventoryTypeRepository;

    public List<InventoryType> getAllInventoryTypes() {
        return inventoryTypeRepository.findAll();
    }

    public List<Inventory> getInventoryByTypeName(String typeName) {
    	
    	Optional<InventoryType> filteredType = inventoryTypeRepository.findByType(typeName);
    	if (filteredType.isPresent()) {
            return inventoryRepository.findByType(filteredType.get());
        }
    	else {
    		return getAllInventory();
    	}
    	
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory addInventory(Inventory inventory) {
        InventoryType type = inventory.getType();
        Optional<InventoryType> existingType = inventoryTypeRepository.findByType(type.getType());

        if (existingType.isPresent()) {
            inventory.setType(existingType.get());
        } 
        else {
            InventoryType newType = new InventoryType(type.getType());
            inventory.setType(inventoryTypeRepository.save(newType));
        }
        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }
}
