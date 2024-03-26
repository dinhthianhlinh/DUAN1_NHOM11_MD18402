package com.example.nhom11_duan1.DTO;


import com.google.firebase.Timestamp;

public class SanPham {
    public String tenSP;
    public int giaSP;
    public String hangSP;
    public String moTaSP;
    public Timestamp timestamp;

    public SanPham() {
    }

    public SanPham(String tenSP, String hangSP, String moTaSP) {
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.hangSP = hangSP;
        this.moTaSP = moTaSP;

    }

    public String gettenSP() {
        return tenSP;
    }

    public void settenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getgiaSP() {
        return giaSP;
    }

    public void setgiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public String gethangSP() {
        return hangSP;
    }

    public void sethangSP(String hangSP) {
        this.hangSP = hangSP;
    }

    public String getmoTaSP() {
        return moTaSP;
    }

    public void setmoTaSP(String moTaSP) {
        this.moTaSP = moTaSP;
    }

    public Timestamp gettimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
//123