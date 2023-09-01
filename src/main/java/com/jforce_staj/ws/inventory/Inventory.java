package com.jforce_staj.ws.inventory;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "inventory_type_id")
    private InventoryType type;  

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date entryDate;  

    @Column(nullable = false, length = 50)
    private String brand;  

    @Column(nullable = false, length = 50)
    private String model;  

    @Column(length = 50)
    private String serialNumber;  

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InventoryStatus status;  

    
    public enum InventoryStatus {
        IN_PERSON, IN_OFFICE, IN_STORAGE
    }
}
