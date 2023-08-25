package com.jforce_staj.ws.staff;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="staff")
public class Staff {

    public enum Cinsiyet {
        M,  
        F   
    }

    public enum MezuniyetDurumu {
        LISANS,
        ONLISANS,
        YUKSEK_LISANS,
        DOKTORA
    }

    public enum Birim {
        YAZILIM_GELISTIRME,
        ARGE
    }

    public enum Gorev {
        YAZILIM_GELISTIRME_UZMANI,
        YONETMEN_YARDIMCISI,
        YONETMEN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String adi;

    @Column(nullable = false, length = 50)
    private String soyadi;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Cinsiyet cinsiyet;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dogumTarihi;

    @Column(nullable = false)
    private String medeniDurumu;

    @Column(nullable = false, unique = true, length = 11)
    private String tckn;

    @Column(nullable = false)
    private Long sicilNumarasi;

    @Column
    @Enumerated(EnumType.STRING)
    private MezuniyetDurumu mezuniyetDurumu;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Birim birim;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gorev gorev;

    @Column(nullable = false)
    private boolean calismaDurumu;

    @Lob 
    private byte[] profilFoto;

    @Temporal(TemporalType.DATE)
    private Date iseGirisTarihi;

    private String iseBasladigiPozisyon;

    private String iseBasladigiUnvan;

    @Temporal(TemporalType.DATE)
    private Date istenAyrilmaTarihi;

    private String istenAyrilmaNedeni;
}
