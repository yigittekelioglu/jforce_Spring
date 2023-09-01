package com.jforce_staj.ws.inventory;

import org.springframework.data.jpa.repository.JpaRepository;



import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByType(InventoryType type);
    List<Inventory> findByStatus(Inventory.InventoryStatus status);
    

}
