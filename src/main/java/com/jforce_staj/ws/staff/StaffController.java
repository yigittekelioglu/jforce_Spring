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

    @Autowired
    private StaffService staffService;

    @GetMapping("/filter")
    public ResponseEntity<List<Staff>> filterStaffs(
            @RequestParam(required = false) String adi, 
            @RequestParam(required = false) String soyadi, 
            @RequestParam(required = false) String tckn, 
            @RequestParam(required = false) Staff.Birim birim) {

        List<Staff> staffs = staffService.filterStaff(adi, soyadi, tckn, birim);
        if(staffs.isEmpty()) {
            return new ResponseEntity<>(staffs, HttpStatus.OK);
        }
        return new ResponseEntity<>(staffs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable Long id) {
        Optional<Staff> staff = staffService.getStaffById(id);
        if(staff.isPresent()) {
            return new ResponseEntity<>(staff.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Staff> addStaff(@RequestBody Staff staff) {
        Staff addedStaff = staffService.addStaff(staff);
        return new ResponseEntity<>(addedStaff, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable Long id, @RequestBody Staff staff) {
        if (!staffService.getStaffById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        staff.setId(id); 
        Staff updatedStaff = staffService.updateStaff(staff);
        return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
    }
}
