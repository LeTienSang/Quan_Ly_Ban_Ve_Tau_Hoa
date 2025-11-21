package com.app.qlvetau.controller;

import com.app.qlvetau.dao.VeDAO;
import com.app.qlvetau.model.entity.Ve;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Controller quản lý nghiệp vụ Vé Tàu
 */
public class VeController {
    
    /**
     * Thêm vé mới
     */
    public boolean themVe(String loaiGhe, String donGiaStr) {
        try {
            // Validate
            if (loaiGhe == null || loaiGhe.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Loại ghế không được để trống!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            double donGia;
            try {
                donGia = Double.parseDouble(donGiaStr);
                if (donGia <= 0) {
                    JOptionPane.showMessageDialog(null, "Đơn giá phải lớn hơn 0!", 
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Đơn giá không hợp lệ!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            Ve ve = new Ve(0, loaiGhe.trim(), donGia);
            VeDAO.them(ve);
            
            JOptionPane.showMessageDialog(null, "Thêm vé thành công!", 
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);
            return true;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Lấy danh sách tất cả vé
     */
    public List<Ve> layDanhSach() {
        try {
            return VeDAO.docTatCa();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }
    
    /**
     * Tìm vé theo mã
     */
    public Ve timTheoMa(int ma) {
        try {
            return VeDAO.timTheoMa(ma);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    /**
     * Lấy tổng số vé
     */
    public int demSoLuong() {
        try {
            return VeDAO.demSoLuong();
        } catch (Exception e) {
            return 0;
        }
    }
}
