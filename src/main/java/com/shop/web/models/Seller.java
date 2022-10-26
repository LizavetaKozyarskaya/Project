package com.shop.web.models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_Seller;

    private String fName, sName, pName;
    private int phone;

    public Seller() {
    }

    public Seller(String fName,  String pName, int phone, String sName) {
        this.fName = fName;
        this.pName = pName;
        this.phone = phone;
        this.sName = sName;
    }

    public Long getId_Seller() {
        return id_Seller;
    }

    public void setId_Seller(Long id_Seller) {
        this.id_Seller = id_Seller;
    }

    public String get_fName() {
        return fName;
    }

    public void set_fName(String fName) {
        this.fName = fName;
    }

    public String get_sName() {
        return sName;
    }

    public void set_sName(String sName) {
        this.sName = sName;
    }

    public String get_pName() {
        return pName;
    }

    public void set_pName(String pName) {
        this.pName = pName;
    }

    public int get_phone() {
        return phone;
    }

    public void set_phone(int phone) {
        this.phone = phone;
    }
}
