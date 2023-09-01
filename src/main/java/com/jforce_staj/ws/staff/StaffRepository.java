package com.jforce_staj.ws.staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    //List<Staff> findByAdiContainingAndSoyadiContainingAndTcknAndBirim(String adi, String soyadi, String tckn, Staff.Birim birim);
    
    List<Staff> findByAdi(String adi);
    
    Staff findBySicilNumarasi(Long sicilNumarasi);
    
    @Query("SELECT s FROM Staff s WHERE (:adi IS NULL OR s.adi LIKE %:adi%) AND (:soyadi IS NULL OR s.soyadi LIKE %:soyadi%) AND (:tckn IS NULL OR s.tckn = :tckn) AND (:birim IS NULL OR s.birim = :birim)")
    List<Staff> findByFilters(@Param("adi") String adi, @Param("soyadi") String soyadi, @Param("tckn") String tckn, @Param("birim") Staff.Birim birim);

}
