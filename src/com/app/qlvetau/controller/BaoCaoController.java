package com.app.qlvetau.controller;

import com.app.qlvetau.dao.HoaDonDAO;
import com.app.qlvetau.model.business.HoaDonCalculator;
import com.app.qlvetau.model.entity.HoaDon;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Controller quản lý báo cáo và bảng kê
 */
public class BaoCaoController {
    
    /**
     * Lập bảng kê thanh toán
     */
    public String lapBangKe() {
        try {
            List<HoaDon> danhSach = HoaDonDAO.docTatCa();
            if (danhSach.isEmpty()) {
                return "Chưa có hóa đơn nào!";
            }
            return HoaDonCalculator.formatBangKe(danhSach);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return "Lỗi lập bảng kê!";
        }
    }
    
    /**
     * Lấy danh sách hóa đơn
     */
    public List<HoaDon> layDanhSachHoaDon() {
        try {
            return HoaDonDAO.docTatCa();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }
}
