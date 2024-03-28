package com.example.nhom11_duan1.DTO;

public class User {
    private String Email;
    private String Pass;
    private String ten;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        this.Pass = pass;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public User() {
    }

    public User(String email, String pass, String ten) {
        this.Email = email;
        this.Pass = pass;
        this.ten = ten;
    }
}
