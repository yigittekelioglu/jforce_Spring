package com.jforce_staj.ws.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StaffService {

    @Autowired
    StaffRepository staffRepository;

    public Staff getStaffById(Long id) {
        return staffRepository.findById(id).orElse(null);
    }

    public List<Staff> filterStaff(String adi, String soyadi, String tckn, Staff.Birim birim) {
        return staffRepository.findByAdiContainingAndSoyadiContainingAndTcknAndBirim(adi, soyadi, tckn, birim);
    }

    public Staff addStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff updateStaff(Staff staff) {
        return staffRepository.save(staff);
    }
}
