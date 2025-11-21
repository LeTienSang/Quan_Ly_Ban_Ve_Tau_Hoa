package com.app.qlvetau.model.entity;

import com.app.qlvetau.model.interfaces.IAutoId;
import com.app.qlvetau.model.interfaces.IFileEntity;

/**
 * Lớp đại diện cho Người Mua Vé
 */
public class NguoiMua implements IAutoId, IFileEntity {
    private static final long serialVersionUID = 1L;
    
    private int maNguoiMua;      // Mã tự động tăng, 5 chữ số
    private String hoTen;
    private String diaChi;
    private String loai;         // mua lẻ, mua tập thể, mua qua mạng
    
    public NguoiMua() {
    }
    
    public NguoiMua(int maNguoiMua, String hoTen, String diaChi, String loai) {
        this.maNguoiMua = maNguoiMua;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.loai = loai;
    }
    
    @Override
    public int getId() {
        return maNguoiMua;
    }
    
    @Override
    public void setId(int id) {
        this.maNguoiMua = id;
    }
    
    public int getMaNguoiMua() {
        return maNguoiMua;
    }
    
    public void setMaNguoiMua(int maNguoiMua) {
        this.maNguoiMua = maNguoiMua;
    }
    
    public String getHoTen() {
        return hoTen;
    }
    
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    
    public String getDiaChi() {
        return diaChi;
    }
    
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    public String getLoai() {
        return loai;
    }
    
    public void setLoai(String loai) {
        this.loai = loai;
    }
    
    @Override
    public String toFileString() {
        return String.format("Mã: %05d | Họ tên: %-30s | Địa chỉ: %-40s | Loại: %s", 
                            maNguoiMua, hoTen, diaChi, loai);
    }
    
    @Override
    public String toString() {
        return toFileString();
    }
    
    /**
     * Validate dữ liệu người mua
     */
    public boolean isValid() {
        return hoTen != null && !hoTen.trim().isEmpty() 
            && diaChi != null && !diaChi.trim().isEmpty()
            && loai != null && !loai.trim().isEmpty();
    }
}
