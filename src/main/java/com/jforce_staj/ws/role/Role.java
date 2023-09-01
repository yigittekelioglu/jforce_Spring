package com.jforce_staj.ws.role;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity; 
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.jforce_staj.ws.shared.Views;
import com.jforce_staj.ws.user.User;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue
    @JsonView(Views.Base.class)// bunu kullanmadıgımda gelmiyor role bilgisi neden?
    private Long id;

    
    @Enumerated(EnumType.STRING)
    @JsonView(Views.Base.class)
    private RoleType name;
    
    
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<User> users = new ArrayList<>();
    
    public Role() {
        
    }

    public enum RoleType {
        ADMIN,
        IK,
        INVENTORYMASTER
    }
    
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
