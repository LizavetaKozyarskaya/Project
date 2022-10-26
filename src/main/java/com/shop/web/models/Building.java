package com.shop.web.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Building {


    public Building() {
    }

    public Building(String address, String walls, int id_Seller,double price, int rooms, double space, String type) {
        Address = address;
        Walls = walls;
        this.id_Seller = id_Seller;
        this.price = price;
        this.rooms = rooms;
        this.space = space;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_building;

    private double space, price;
    private int rooms, id_Seller;
    private String Walls, Address, type;

    public Long getId_building() {
        return id_building;
    }

    public void setId_building(Long id_building) {
        this.id_building = id_building;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSpace() {
        return space;
    }

    public void setSpace(double space) {
        this.space = space;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public double getId_Seller() {
        return id_Seller;
    }

    public void setId_Seller(int id_Seller) {
        this.id_Seller = id_Seller;
    }

    public String getWalls() {
        return Walls;
    }

    public void setWalls(String walls) {
        Walls = walls;
    }



}
