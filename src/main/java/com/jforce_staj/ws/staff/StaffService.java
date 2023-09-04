package com.jforce_staj.ws.staff;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    @Autowired
    StaffRepository staffRepository;

    
    
    public Optional<Staff> getStaffById(Long id) {
        return staffRepository.findById(id);
    }

    
    public List<Staff> filterStaff(String adi, String soyadi, String tckn, Staff.Birim birim) {
        return staffRepository.findByFilters(adi, soyadi, tckn, birim);
    }

    //ekleme ve güncelleme methodlarını void yapsam ne fark eder?
    public Staff addStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff updateStaff(Staff staff) {
        return staffRepository.save(staff);
    }
}
 