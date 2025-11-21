package com.app.qlvetau.controller;

import com.app.qlvetau.dao.NguoiMuaDAO;
import com.app.qlvetau.model.entity.NguoiMua;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Controller quản lý nghiệp vụ Người Mua
 */
public class NguoiMuaController {
    
    /**
     * Thêm người mua mới
     */
    public boolean themNguoiMua(String hoTen, String diaChi, String loai) {
        try {
            // Validate
            if (hoTen == null || hoTen.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Họ tên không được để trống!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (diaChi == null || diaChi.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Địa chỉ không được để trống!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (loai == null || loai.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Loại không được để trống!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            NguoiMua nguoiMua = new NguoiMua(0, hoTen.trim(), diaChi.trim(), loai.trim());
            NguoiMuaDAO.them(nguoiMua);
            
            JOptionPane.showMessageDialog(null, "Thêm người mua thành công!", 
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);
            return true;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Lấy danh sách tất cả người mua
     */
    public List<NguoiMua> layDanhSach() {
        try {
            return NguoiMuaDAO.docTatCa();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }
    
    /**
     * Tìm người mua theo mã
     */
    public NguoiMua timTheoMa(int ma) {
        try {
            return NguoiMuaDAO.timTheoMa(ma);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    /**
     * Lấy tổng số người mua
     */
    public int demSoLuong() {
        try {
            return NguoiMuaDAO.demSoLuong();
        } catch (Exception e) {
            return 0;
        }
    }
}
