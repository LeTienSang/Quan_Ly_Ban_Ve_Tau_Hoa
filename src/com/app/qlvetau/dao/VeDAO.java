package com.app.qlvetau.dao;

import com.app.qlvetau.model.entity.Ve;
import java.io.IOException;
import java.util.List;

/**
 * DAO quản lý Vé Tàu - VE.DAT
 */
public class VeDAO {
    private static final String FILE_NAME = "VE.DAT";
    private static int nextId = 10000; // Bắt đầu từ 10000 (5 chữ số)
    
    /**
     * Khởi tạo và load nextId từ dữ liệu có sẵn
     */
    static {
        try {
            List<Ve> list = docTatCa();
            if (!list.isEmpty()) {
                nextId = list.stream()
                        .mapToInt(Ve::getMaVe)
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
     * Thêm vé mới
     */
    public static void them(Ve ve) throws IOException, ClassNotFoundException {
        if (!ve.isValid()) {
            throw new IllegalArgumentException("Dữ liệu vé không hợp lệ!");
        }
        
        List<Ve> list = docTatCa();
        ve.setMaVe(taoMaTuDong());
        list.add(ve);
        FileUtil.ghiFile(FILE_NAME, list);
    }
    
    /**
     * Đọc tất cả vé
     */
    public static List<Ve> docTatCa() throws IOException, ClassNotFoundException {
        return FileUtil.docFile(FILE_NAME);
    }
    
    /**
     * Tìm vé theo mã
     */
    public static Ve timTheoMa(int maVe) throws IOException, ClassNotFoundException {
        List<Ve> list = docTatCa();
        return list.stream()
                .filter(v -> v.getMaVe() == maVe)
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Cập nhật vé
     */
    public static void capNhat(Ve ve) throws IOException, ClassNotFoundException {
        List<Ve> list = docTatCa();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMaVe() == ve.getMaVe()) {
                list.set(i, ve);
                break;
            }
        }
        FileUtil.ghiFile(FILE_NAME, list);
    }
    
    /**
     * Xóa vé
     */
    public static void xoa(int maVe) throws IOException, ClassNotFoundException {
        List<Ve> list = docTatCa();
        list.removeIf(v -> v.getMaVe() == maVe);
        FileUtil.ghiFile(FILE_NAME, list);
    }
    
    /**
     * Lấy tổng số vé
     */
    public static int demSoLuong() throws IOException, ClassNotFoundException {
        return docTatCa().size();
    }
}
