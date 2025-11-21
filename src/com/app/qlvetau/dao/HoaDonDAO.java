package com.app.qlvetau.dao;

import com.app.qlvetau.model.entity.HoaDon;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * DAO quản lý Hóa Đơn - HOADON.DAT
 */
public class HoaDonDAO {
    private static final String FILE_NAME = "HOADON.DAT";
    
    /**
     * Thêm hóa đơn mới
     */
    public static void them(HoaDon hoaDon) throws IOException, ClassNotFoundException {
        List<HoaDon> list = docTatCa();
        list.add(hoaDon);
        FileUtil.ghiFile(FILE_NAME, list);
    }
    
    /**
     * Đọc tất cả hóa đơn
     */
    public static List<HoaDon> docTatCa() throws IOException, ClassNotFoundException {
        return FileUtil.docFile(FILE_NAME);
    }
    
    /**
     * Lưu lại toàn bộ danh sách hóa đơn (sau khi sắp xếp)
     */
    public static void luuTatCa(List<HoaDon> list) throws IOException {
        FileUtil.ghiFile(FILE_NAME, list);
    }
    
    /**
     * Sắp xếp theo họ tên người mua
     */
    public static List<HoaDon> sapXepTheoHoTen() throws IOException, ClassNotFoundException {
        List<HoaDon> list = docTatCa();
        list.sort(Comparator.comparing(hd -> hd.getNguoiMua().getHoTen()));
        return list;
    }
    
    /**
     * Sắp xếp theo số lượng vé (giảm dần)
     */
    public static List<HoaDon> sapXepTheoSoLuong() throws IOException, ClassNotFoundException {
        List<HoaDon> list = docTatCa();
        list.sort(Comparator.comparingInt(HoaDon::tinhTongSoLuong).reversed());
        return list;
    }
    
    /**
     * Tìm hóa đơn theo mã người mua
     */
    public static List<HoaDon> timTheoMaNguoiMua(int maNguoiMua) throws IOException, ClassNotFoundException {
        List<HoaDon> list = docTatCa();
        return list.stream()
                .filter(hd -> hd.getNguoiMua().getMaNguoiMua() == maNguoiMua)
                .toList();
    }
    
    /**
     * Xóa tất cả hóa đơn
     */
    public static void xoaTatCa() throws IOException {
        FileUtil.ghiFile(FILE_NAME, List.of());
    }
    
    /**
     * Lấy tổng số hóa đơn
     */
    public static int demSoLuong() throws IOException, ClassNotFoundException {
        return docTatCa().size();
    }
}
