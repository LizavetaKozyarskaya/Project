package com.shop.web.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_Proposal;

    private int  id_Users, id_building, phoneP;
    private String sNameP, fNameP, pNameP;

    public Proposal() {

    }

    public Proposal(int id_building, int id_Users, String sNameP, String fNameP, String pNameP,int phoneP){
        this.id_building = id_building;
        this.id_Users = id_Users;
        this.sNameP = sNameP;
        this.fNameP = fNameP;
        this.pNameP = pNameP;
        this.phoneP = phoneP;
    }

    public Long getId_Proposal() {
        return id_Proposal;
    }

    public void setId_Proposal(Long id_Proposal) {
        this.id_Proposal = id_Proposal;
    }

    public int getId_Users() {
        return id_Users;
    }

    public void setId_Users(int id_Users) {
        this.id_Users = id_Users;
    }

    public int getId_building() {
        return id_building;
    }

    public void setId_building(int id_building) {
        this.id_building = id_building;
    }

    public int getPhoneP() {
        return phoneP;
    }

    public void setPhoneP(int phoneP) {
        this.phoneP = phoneP;
    }

    public String getsNameP() {
        return sNameP;
    }

    public void setsNameP(String sNameP) {
        this.sNameP = sNameP;
    }

    public String getfNameP() {
        return fNameP;
    }

    public void setfNameP(String fNameP) {
        this.fNameP = fNameP;
    }

    public String getpNameP() {
        return pNameP;
    }

    public void setpNameP(String pNameP) {
        this.pNameP = pNameP;
    }
}
