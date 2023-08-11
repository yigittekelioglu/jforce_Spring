package com.jforce_staj.ws.user;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType name;
    
    
    public Role() {
        
    }

    public enum RoleType {
        ADMIN,
        IK,
        INVENTORYMASTER
    }
}
