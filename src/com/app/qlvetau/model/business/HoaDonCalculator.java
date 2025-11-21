package com.app.qlvetau.model.business;

import com.app.qlvetau.model.entity.HoaDon;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Lớp tính toán cho Hóa Đơn
 */
public class HoaDonCalculator {
    
    /**
     * Tính tổng doanh thu từ danh sách hóa đơn
     */
    public static double tinhTongDoanhThu(List<HoaDon> danhSachHD) {
        return danhSachHD.stream()
                .mapToDouble(HoaDon::tinhTongTien)
                .sum();
    }
    
    /**
     * Tính tổng số vé đã bán
     */
    public static int tinhTongSoVe(List<HoaDon> danhSachHD) {
        return danhSachHD.stream()
                .mapToInt(HoaDon::tinhTongSoLuong)
                .sum();
    }
    
    /**
     * Tạo bảng kê thanh toán cho từng người mua
     */
    public static Map<Integer, Double> taoBangKeThanhToan(List<HoaDon> danhSachHD) {
        Map<Integer, Double> bangKe = new HashMap<>();
        
        for (HoaDon hd : danhSachHD) {
            int maNguoiMua = hd.getNguoiMua().getMaNguoiMua();
            double tongTien = hd.tinhTongTien();
            
            bangKe.merge(maNguoiMua, tongTien, Double::sum);
        }
        
        return bangKe;
    }
    
    /**
     * Định dạng bảng kê thanh toán thành chuỗi
     */
    public static String formatBangKe(List<HoaDon> danhSachHD) {
        StringBuilder sb = new StringBuilder();
        sb.append("=".repeat(100)).append("\n");
        sb.append(String.format("%-15s %-35s %-25s %-20s\n", 
                "MÃ KHÁCH HÀNG", "HỌ TÊN", "LOẠI", "TỔNG TIỀN"));
        sb.append("=".repeat(100)).append("\n");
        
        Map<Integer, HoaDon> uniqueCustomers = new HashMap<>();
        for (HoaDon hd : danhSachHD) {
            uniqueCustomers.putIfAbsent(hd.getNguoiMua().getMaNguoiMua(), hd);
        }
        
        Map<Integer, Double> bangKe = taoBangKeThanhToan(danhSachHD);
        
        for (Map.Entry<Integer, HoaDon> entry : uniqueCustomers.entrySet()) {
            HoaDon hd = entry.getValue();
            double tongTien = bangKe.get(entry.getKey());
            
            sb.append(String.format("%-15s %-35s %-25s %,20.0f VNĐ\n",
                    String.format("%05d", hd.getNguoiMua().getMaNguoiMua()),
                    hd.getNguoiMua().getHoTen(),
                    hd.getNguoiMua().getLoai(),
                    tongTien));
        }
        
        sb.append("=".repeat(100)).append("\n");
        sb.append(String.format("TỔNG DOANH THU: %,20.0f VNĐ\n", tinhTongDoanhThu(danhSachHD)));
        sb.append(String.format("TỔNG SỐ VÉ BÁN: %,20d vé\n", tinhTongSoVe(danhSachHD)));
        sb.append("=".repeat(100)).append("\n");
        
        return sb.toString();
    }
}
