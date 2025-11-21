package com.app.qlvetau.controller;

import com.app.qlvetau.dao.HoaDonDAO;
import com.app.qlvetau.dao.NguoiMuaDAO;
import com.app.qlvetau.dao.VeDAO;
import com.app.qlvetau.model.entity.ChiTietHD;
import com.app.qlvetau.model.entity.HoaDon;
import com.app.qlvetau.model.entity.NguoiMua;
import com.app.qlvetau.model.entity.Ve;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Controller quản lý nghiệp vụ Hóa Đơn
 */
public class HoaDonController {
    
    /**
     * Tạo hóa đơn mới
     */
    public boolean taoHoaDon(int maNguoiMua, List<ChiTietHD> danhSachChiTiet) {
        try {
            // Validate
            if (danhSachChiTiet == null || danhSachChiTiet.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Phải có ít nhất 1 chi tiết hóa đơn!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (danhSachChiTiet.size() > 4) {
                JOptionPane.showMessageDialog(null, "Không được quá 4 loại vé!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // Kiểm tra người mua tồn tại
            NguoiMua nguoiMua = NguoiMuaDAO.timTheoMa(maNguoiMua);
            if (nguoiMua == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy người mua!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // Tạo hóa đơn
            HoaDon hoaDon = new HoaDon(nguoiMua);
            for (ChiTietHD ct : danhSachChiTiet) {
                if (ct.getSoLuong() <= 0 || ct.getSoLuong() > 20) {
                    JOptionPane.showMessageDialog(null, "Số lượng vé phải từ 1 đến 20!", 
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                hoaDon.themChiTiet(ct);
            }
            
            HoaDonDAO.them(hoaDon);
            
            JOptionPane.showMessageDialog(null, "Tạo hóa đơn thành công!", 
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);
            return true;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Lấy danh sách tất cả hóa đơn
     */
    public List<HoaDon> layDanhSach() {
        try {
            return HoaDonDAO.docTatCa();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }
    
    /**
     * Lấy danh sách người mua
     */
    public List<NguoiMua> layDanhSachNguoiMua() {
        try {
            return NguoiMuaDAO.docTatCa();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }
    
    /**
     * Lấy danh sách vé
     */
    public List<Ve> layDanhSachVe() {
        try {
            return VeDAO.docTatCa();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc dữ liệu: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }
    
    /**
     * Lấy tổng số hóa đơn
     */
    public int demSoLuong() {
        try {
            return HoaDonDAO.demSoLuong();
        } catch (Exception e) {
            return 0;
        }
    }
}
