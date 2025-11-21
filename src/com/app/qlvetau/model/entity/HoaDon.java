package com.app.qlvetau.model.entity;

import com.app.qlvetau.model.interfaces.ICanCalculate;
import com.app.qlvetau.model.interfaces.IFileEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp đại diện cho Hóa Đơn
 */
public class HoaDon implements IFileEntity, ICanCalculate {
    private static final long serialVersionUID = 1L;
    
    private NguoiMua nguoiMua;
    private List<ChiTietHD> danhSachChiTiet;   // Tối đa 4 loại vé
    
    public HoaDon() {
        danhSachChiTiet = new ArrayList<>();
    }
    
    public HoaDon(NguoiMua nguoiMua) {
        this.nguoiMua = nguoiMua;
        this.danhSachChiTiet = new ArrayList<>();
    }
    
    public NguoiMua getNguoiMua() {
        return nguoiMua;
    }
    
    public void setNguoiMua(NguoiMua nguoiMua) {
        this.nguoiMua = nguoiMua;
    }
    
    public List<ChiTietHD> getDanhSachChiTiet() {
        return danhSachChiTiet;
    }
    
    public void setDanhSachChiTiet(List<ChiTietHD> danhSachChiTiet) {
        this.danhSachChiTiet = danhSachChiTiet;
    }
    
    /**
     * Thêm chi tiết vào hóa đơn
     */
    public boolean themChiTiet(ChiTietHD chiTiet) {
        if (danhSachChiTiet.size() >= 4) {
            return false; // Đã đủ 4 loại
        }
        danhSachChiTiet.add(chiTiet);
        return true;
    }
    
    /**
     * Tính tổng số lượng vé trong hóa đơn
     */
    public int tinhTongSoLuong() {
        return danhSachChiTiet.stream()
                .mapToInt(ChiTietHD::getSoLuong)
                .sum();
    }
    
    /**
     * Tính tổng tiền hóa đơn
     */
    @Override
    public double tinhTongTien() {
        return danhSachChiTiet.stream()
                .mapToDouble(ChiTietHD::tinhThanhTien)
                .sum();
    }
    
    @Override
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=".repeat(80)).append("\n");
        sb.append(String.format("HÓA ĐỜN MUA VÉ - Mã khách hàng: %05d\n", nguoiMua.getMaNguoiMua()));
        sb.append(String.format("Khách hàng: %s\n", nguoiMua.getHoTen()));
        sb.append(String.format("Địa chỉ: %s\n", nguoiMua.getDiaChi()));
        sb.append(String.format("Loại: %s\n", nguoiMua.getLoai()));
        sb.append("-".repeat(80)).append("\n");
        sb.append("Chi tiết:\n");
        for (ChiTietHD ct : danhSachChiTiet) {
            sb.append(ct.toFileString()).append("\n");
        }
        sb.append("-".repeat(80)).append("\n");
        sb.append(String.format("Tổng số lượng vé: %d\n", tinhTongSoLuong()));
        sb.append(String.format("TỔNG TIỀN: %,.0f VNĐ\n", tinhTongTien()));
        sb.append("=".repeat(80)).append("\n");
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return toFileString();
    }
}
