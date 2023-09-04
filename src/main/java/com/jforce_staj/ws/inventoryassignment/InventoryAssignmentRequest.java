package com.jforce_staj.ws.inventoryassignment;

import lombok.Data;
import java.util.Date;

@Data
public class InventoryAssignmentRequest {
    private Long staffId;
    private Long inventoryId;
    private Date pickupDate;
}