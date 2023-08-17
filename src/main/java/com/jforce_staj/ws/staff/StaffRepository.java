package com.jforce_staj.ws.staff;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    List<Staff> findByAdiContainingAndSoyadiContainingAndTcknAndBirim(String adi, String soyadi, String tckn, Staff.Birim birim);
    
    List<Staff> findByAdi(String adi);
    
    Staff findBySicilNumarasi(Long sicilNumarasi);
}
