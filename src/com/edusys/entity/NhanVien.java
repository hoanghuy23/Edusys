/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.entity;

/**
 *
 * @author ASUS
 */
public class NhanVien {
    String maNV;
    String hoTen;
    String matKhau;
    boolean vaiTro;

//    public NhanVien(String maNV, String hoTen, String matKhau, boolean vaiTro) {
//        this.maNV = maNV;
//        this.hoTen = hoTen;
//        this.matKhau = matKhau;
//        this.vaiTro = vaiTro;
//    }

//    public NhanVien() {
//            
//    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public boolean getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }
    
}
