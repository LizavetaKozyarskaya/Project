package com.shop.web.models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_Users;

    private String suName, fuName,  puName, login, password, role;
    private int uphone;

    public Users() {
    }

    public Users(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Users(String suName, String fuName, String puName, String login, String password, int uphone, String role) {
        this.suName = suName;
        this.fuName = fuName;
        this.puName = puName;
        this.login = login;
        this.password = password;
        this.uphone = uphone;
        this.role = role;
    }


    public Long getId_Users() {
        return id_Users;
    }

    public void setId_Users(Long id_Users) {
        this.id_Users = id_Users;
    }

    public String getSuName() {
        return suName;
    }

    public void setSuName(String suName) {
        this.suName = suName;
    }

    public String getFuName() {
        return fuName;
    }

    public void setFuName(String fuName) {
        this.fuName = fuName;
    }

    public String getPuName() {
        return puName;
    }

    public void setPuName(String puName) {
        this.puName = puName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUphone() {
        return uphone;
    }

    public void setUphone(int uphone) {
        this.uphone = uphone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
