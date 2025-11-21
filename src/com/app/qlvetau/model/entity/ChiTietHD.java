package com.app.qlvetau.model.entity;

import com.app.qlvetau.model.interfaces.IFileEntity;

/**
 * Lớp đại diện cho Chi Tiết Hóa Đơn (mỗi loại vé trong hóa đơn)
 */
public class ChiTietHD implements IFileEntity {
    private static final long serialVersionUID = 1L;
    
    private Ve ve;               // Thông tin vé
    private int soLuong;         // Số lượng (tối đa 20)
    
    public ChiTietHD() {
    }
    
    public ChiTietHD(Ve ve, int soLuong) {
        this.ve = ve;
        this.soLuong = soLuong;
    }
    
    public Ve getVe() {
        return ve;
    }
    
    public void setVe(Ve ve) {
        this.ve = ve;
    }
    
    public int getSoLuong() {
        return soLuong;
    }
    
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    /**
     * Tính thành tiền cho chi tiết này
     */
    public double tinhThanhTien() {
        return ve.getDonGia() * soLuong;
    }
    
    @Override
    public String toFileString() {
        return String.format("   - %s x %d = %,.0f VNĐ", 
                            ve.getLoaiGhe(), soLuong, tinhThanhTien());
    }
    
    @Override
    public String toString() {
        return toFileString();
    }
}
