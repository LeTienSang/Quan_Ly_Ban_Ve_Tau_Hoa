package com.app.qlvetau.controller;

import com.app.qlvetau.dao.HoaDonDAO;
import com.app.qlvetau.model.entity.HoaDon;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Controller quản lý sắp xếp Hóa Đơn
 */
public class SortController {
    
    /**
     * Sắp xếp theo họ tên người mua
     */
    public List<HoaDon> sapXepTheoHoTen() {
        try {
            List<HoaDon> list = HoaDonDAO.sapXepTheoHoTen();
            HoaDonDAO.luuTatCa(list); // Lưu lại sau khi sắp xếp
            return list;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi sắp xếp: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }
    
    /**
     * Sắp xếp theo số lượng vé (giảm dần)
     */
    public List<HoaDon> sapXepTheoSoLuong() {
        try {
            List<HoaDon> list = HoaDonDAO.sapXepTheoSoLuong();
            HoaDonDAO.luuTatCa(list); // Lưu lại sau khi sắp xếp
            return list;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi sắp xếp: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }
}
