package com.jforce_staj.ws.inventory;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor  
@AllArgsConstructor 
@Table(name = "inventory_type")
public class InventoryType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String type;
    
    public InventoryType(String type) {
        this.type = type;
    }
}
