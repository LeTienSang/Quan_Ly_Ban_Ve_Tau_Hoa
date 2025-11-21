package com.app.qlvetau.model.entity;

import com.app.qlvetau.model.interfaces.IAutoId;
import com.app.qlvetau.model.interfaces.IFileEntity;

/**
 * Lớp đại diện cho Vé Tàu
 */
public class Ve implements IAutoId, IFileEntity {
    private static final long serialVersionUID = 1L;
    
    private int maVe;            // Mã tự động tăng, 5 chữ số
    private String loaiGhe;      // VD: Ngồi cứng, ngồi mềm, nằm điều hòa, etc
    private double donGia;
    
    public Ve() {
    }
    
    public Ve(int maVe, String loaiGhe, double donGia) {
        this.maVe = maVe;
        this.loaiGhe = loaiGhe;
        this.donGia = donGia;
    }
    
    @Override
    public int getId() {
        return maVe;
    }
    
    @Override
    public void setId(int id) {
        this.maVe = id;
    }
    
    public int getMaVe() {
        return maVe;
    }
    
    public void setMaVe(int maVe) {
        this.maVe = maVe;
    }
    
    public String getLoaiGhe() {
        return loaiGhe;
    }
    
    public void setLoaiGhe(String loaiGhe) {
        this.loaiGhe = loaiGhe;
    }
    
    public double getDonGia() {
        return donGia;
    }
    
    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
    
    @Override
    public String toFileString() {
        return String.format("Mã vé: %05d | Loại ghế: %-30s | Đơn giá: %,.0f VNĐ", 
                            maVe, loaiGhe, donGia);
    }
    
    @Override
    public String toString() {
        return toFileString();
    }
    
    /**
     * Validate dữ liệu vé
     */
    public boolean isValid() {
        return loaiGhe != null && !loaiGhe.trim().isEmpty() && donGia > 0;
    }
}
