package com.jforce_staj.ws.inventoryassignment;


import com.jforce_staj.ws.inventory.Inventory; 
import com.jforce_staj.ws.staff.Staff;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "inventory_assignment")
public class InventoryAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;  

    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;  

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date pickupDate;  

    @ManyToOne
    @JoinColumn(name = "delivered_by_staff_id")
    private Staff deliveredBy;  

    @Temporal(TemporalType.DATE)
    private Date returnDate;  
    
    @ManyToOne
    @JoinColumn(name = "received_by_staff_id")
    private Staff receivedBy;  

}
