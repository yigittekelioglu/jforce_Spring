package com.jforce_staj.ws.inventoryassignment;

import com.jforce_staj.ws.inventory.Inventory;
import com.jforce_staj.ws.staff.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryAssignmentRepository extends JpaRepository<InventoryAssignment, Long> {

    List<InventoryAssignment> findByStaff(Staff staff);
   

    

}
