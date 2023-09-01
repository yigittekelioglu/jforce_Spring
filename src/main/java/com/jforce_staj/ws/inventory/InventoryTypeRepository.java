package com.jforce_staj.ws.inventory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryTypeRepository extends JpaRepository<InventoryType, Long> {
	
   
    Optional<InventoryType> findByType(String type);
   
}

