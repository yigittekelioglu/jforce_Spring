package com.jforce_staj.ws.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import java.util.Optional;

import java.beans.PropertyEditorSupport;

@ControllerAdvice
public class InventoryControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(InventoryType.class, new InventoryTypeEditor());
    }

    @Component
    public class InventoryTypeEditor extends PropertyEditorSupport {

        @Autowired
        private InventoryTypeRepository inventoryTypeRepository;

        @Override
        public void setAsText(String typeName) {
            Optional<InventoryType> optionalType = inventoryTypeRepository.findByType(typeName);
            
            InventoryType type;
            
            if (!optionalType.isPresent()) {
                type = new InventoryType(typeName);
                inventoryTypeRepository.save(type);
            } else {
                type = optionalType.get();
            }
            
            setValue(type);
        }
    }
}
