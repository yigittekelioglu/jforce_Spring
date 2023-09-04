package com.jforce_staj.ws.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired//bu oldugu ıcın constructor'a gerek yok
    StaffService staffService;

    @GetMapping("/filter")
    public ResponseEntity<List<Staff>> filterStaffs( @RequestParam(required = false) String adi, @RequestParam(required = false) String soyadi, @RequestParam(required = false) String tckn, @RequestParam(required = false) Staff.Birim birim){

        List<Staff> filteredStaffs = staffService.filterStaff(adi, soyadi, tckn, birim);
        return new ResponseEntity<>(filteredStaffs, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable Long id) {
    	/*
        Optional<Staff> staff = staffService.getStaffById(id);
        if(staff.isPresent()) {
            return new ResponseEntity<>(staff.get(), HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/
    	return new ResponseEntity<>(staffService.getStaffById(id).get(), HttpStatus.OK);
    	
    }

    //error mesajı
    @PostMapping
    public ResponseEntity<Staff> addStaff(@RequestBody Staff staff) {
        Staff addedStaff = staffService.addStaff(staff);
        return new ResponseEntity<>(addedStaff, HttpStatus.CREATED);
        //return ResponseEntity.ok(addedStaff);
        //return new ResponseEntity<>(staffService.addStaff(staff), HttpStatus.CREATED);
    }

    
    //error mesajı
    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable Long id, @RequestBody Staff staff) {
    	/*
        if (!staffService.getStaffById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
        	staff.setId(id); 
            Staff updatedStaff = staffService.updateStaff(staff);
            return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
        }*/
    	staff.setId(id); 
        Staff updatedStaff = staffService.updateStaff(staff);
        return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
        
    }
}
