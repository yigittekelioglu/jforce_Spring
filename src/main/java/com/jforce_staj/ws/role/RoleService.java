package com.jforce_staj.ws.role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jforce_staj.ws.user.User;
import com.jforce_staj.ws.user.UserController;
import com.jforce_staj.ws.user.UserRepository;

@Service
public class RoleService {
    
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

     
    
    public void assignAdminRoleToAdminUser() {
        User adminUser = userRepository.findByUsername("admin");
        Role adminRole = roleRepository.findByName(Role.RoleType.ADMIN).orElse(null);  
        adminUser.setRole(adminRole);
        userRepository.save(adminUser);
        log.info("Admin kullanıcısına ADMIN rolü başarıyla atandı.");
    }

    
    public void assignIkRoleToIkUser() {
        User ikUser = userRepository.findByUsername("ik");
        Role ikRole = roleRepository.findByName(Role.RoleType.IK).orElse(null); 
        
        ikUser.setRole(ikRole);
        userRepository.save(ikUser);
        log.info("Ik kullanıcısına IK rolü atandı.");
    }

    public void assignInventorymasterRoleToInventorymasterUser() {
        User inventorymasterUser = userRepository.findByUsername("inventorymaster");
        Role inventorymasterRole = roleRepository.findByName(Role.RoleType.INVENTORYMASTER).orElse(null); 

        inventorymasterUser.setRole(inventorymasterRole);
        userRepository.save(inventorymasterUser);
        log.info("inventorymaster kullanıcısına INVENTORYMASTER rolü atandı.");
    }

}
