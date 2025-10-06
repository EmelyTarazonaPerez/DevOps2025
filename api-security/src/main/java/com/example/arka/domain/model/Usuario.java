package com.example.arka.domain.model;
import java.time.LocalDate;


public class Usuario {
    private String userName;
    private String emailAddress;
    private String password;
    private String role;

    public Usuario(String userName, String emailAddress, String password, String role) {
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.role = role;
    }



    public String getUserName() {
        return userName;
    }

    public Usuario setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Usuario setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Usuario setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public Usuario setRole(String role) {
        this.role = role;
        return this;
    }
}
