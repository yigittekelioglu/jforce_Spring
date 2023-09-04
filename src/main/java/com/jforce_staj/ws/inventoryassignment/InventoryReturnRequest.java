package com.jforce_staj.ws.inventoryassignment;

import java.util.Date;

import lombok.Data;

@Data
public class InventoryReturnRequest {
    private Long inventoryId;
    private Date returnDate;
}