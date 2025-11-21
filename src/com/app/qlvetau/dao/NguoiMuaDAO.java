package com.app.qlvetau.dao;

import com.app.qlvetau.model.entity.NguoiMua;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO quản lý Người Mua - NGUOIMUA.DAT
 */
public class NguoiMuaDAO {
    private static final String FILE_NAME = "NGUOIMUA.DAT";
    private static int nextId = 10000; // Bắt đầu từ 10000 (5 chữ số)
    
    /**
     * Khởi tạo và load nextId từ dữ liệu có sẵn
     */
    static {
        try {
            List<NguoiMua> list = docTatCa();
            if (!list.isEmpty()) {
                nextId = list.stream()
                        .mapToInt(NguoiMua::getMaNguoiMua)
                        .max()
                        .orElse(10000) + 1;
            }
        } catch (Exception e) {
            nextId = 10000;
        }
    }
    
    /**
     * Tạo mã tự động
     */
    private static synchronized int taoMaTuDong() {
        return nextId++;
    }
    
    /**
     * Thêm người mua mới
     */
    public static void them(NguoiMua nguoiMua) throws IOException, ClassNotFoundException {
        if (!nguoiMua.isValid()) {
            throw new IllegalArgumentException("Dữ liệu người mua không hợp lệ!");
        }
        
        List<NguoiMua> list = docTatCa();
        nguoiMua.setMaNguoiMua(taoMaTuDong());
        list.add(nguoiMua);
        FileUtil.ghiFile(FILE_NAME, list);
    }
    
    /**
     * Đọc tất cả người mua
     */
    public static List<NguoiMua> docTatCa() throws IOException, ClassNotFoundException {
        return FileUtil.docFile(FILE_NAME);
    }
    
    /**
     * Tìm người mua theo mã
     */
    public static NguoiMua timTheoMa(int maNguoiMua) throws IOException, ClassNotFoundException {
        List<NguoiMua> list = docTatCa();
        return list.stream()
                .filter(nm -> nm.getMaNguoiMua() == maNguoiMua)
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Cập nhật người mua
     */
    public static void capNhat(NguoiMua nguoiMua) throws IOException, ClassNotFoundException {
        List<NguoiMua> list = docTatCa();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMaNguoiMua() == nguoiMua.getMaNguoiMua()) {
                list.set(i, nguoiMua);
                break;
            }
        }
        FileUtil.ghiFile(FILE_NAME, list);
    }
    
    /**
     * Xóa người mua
     */
    public static void xoa(int maNguoiMua) throws IOException, ClassNotFoundException {
        List<NguoiMua> list = docTatCa();
        list.removeIf(nm -> nm.getMaNguoiMua() == maNguoiMua);
        FileUtil.ghiFile(FILE_NAME, list);
    }
    
    /**
     * Lấy tổng số người mua
     */
    public static int demSoLuong() throws IOException, ClassNotFoundException {
        return docTatCa().size();
    }
}
