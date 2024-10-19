package com.ltm.lmt.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String masv;      // Mã sinh viên
    private String firstname; // Họ
    private String lastname;  // Tên
    private String status;    // Trạng thái (online/offline)

    // Constructor
    public User(int id, String username, String password, String masv, String firstname, String lastname, String status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.masv = masv;
        this.firstname = firstname;
        this.lastname = lastname;
        this.status = status;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMasv() {
        return masv;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getStatus() {
        return status;
    }

    // Để tạo full name từ firstname và lastname
    public String getFullname() {
        return firstname + " " + lastname;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

