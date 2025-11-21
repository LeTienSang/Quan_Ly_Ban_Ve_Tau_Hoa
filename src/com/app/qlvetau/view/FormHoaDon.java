package com.app.qlvetau.view;

import com.app.qlvetau.controller.HoaDonController;
import com.app.qlvetau.model.entity.ChiTietHD;
import com.app.qlvetau.model.entity.HoaDon;
import com.app.qlvetau.model.entity.NguoiMua;
import com.app.qlvetau.model.entity.Ve;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Form lập Hóa Đơn - UI đơn giản
 */
public class FormHoaDon extends JFrame {
    private HoaDonController controller;
    private JComboBox<String> cboNguoiMua, cboVe;
    private JTextField txtSoLuong;
    private JTable tableChiTiet, tableHoaDon;
    private DefaultTableModel modelChiTiet, modelHoaDon;
    private List<ChiTietHD> danhSachChiTiet;
    private List<NguoiMua> danhSachNguoiMua;
    private List<Ve> danhSachVe;
    
    public FormHoaDon() {
        controller = new HoaDonController();
        danhSachChiTiet = new ArrayList<>();
        initComponents();
        loadComboData();
        loadHoaDon();
    }
    
    private void initComponents() {
        setTitle("Lập Hóa Đơn Mua Vé");
        setSize(950, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));
        
        // Panel nhập
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // Panel thông tin
        JPanel infoPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Thông Tin Hóa Đơn"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        infoPanel.add(new JLabel("Người mua:"));
        cboNguoiMua = new JComboBox<>();
        infoPanel.add(cboNguoiMua);
        
        infoPanel.add(new JLabel("Loại vé:"));
        cboVe = new JComboBox<>();
        infoPanel.add(cboVe);
        
        infoPanel.add(new JLabel("Số lượng (1-20):"));
        txtSoLuong = new JTextField();
        infoPanel.add(txtSoLuong);
        
        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton btnThemCT = new JButton("Thêm Chi Tiết");
        JButton btnXoaCT = new JButton("Xóa Chi Tiết");
        JButton btnLuu = new JButton("Lưu Hóa Đơn");
        JButton btnHuy = new JButton("Hủy Bỏ");
        
        btnThemCT.addActionListener(e -> themChiTiet());
        btnXoaCT.addActionListener(e -> xoaChiTiet());
        btnLuu.addActionListener(e -> luuHoaDon());
        btnHuy.addActionListener(e -> huyBo());
        
        btnPanel.add(btnThemCT);
        btnPanel.add(btnXoaCT);
        btnPanel.add(btnLuu);
        btnPanel.add(btnHuy);
        
        infoPanel.add(new JLabel());
        infoPanel.add(btnPanel);
        
        // Table chi tiết
        String[] colsChiTiet = {"Loại Vé", "Đơn Giá", "Số Lượng", "Thành Tiền"};
        modelChiTiet = new DefaultTableModel(colsChiTiet, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableChiTiet = new JTable(modelChiTiet);
        tableChiTiet.setRowHeight(25);
        JScrollPane scrollChiTiet = new JScrollPane(tableChiTiet);
        scrollChiTiet.setBorder(BorderFactory.createTitledBorder("Chi Tiết Hóa Đơn (Tối đa 4 loại)"));
        scrollChiTiet.setPreferredSize(new Dimension(0, 120));
        
        topPanel.add(infoPanel, BorderLayout.NORTH);
        topPanel.add(scrollChiTiet, BorderLayout.CENTER);
        
        // Table danh sách hóa đơn
        String[] colsHD = {"Mã KH", "Tên KH", "Loại", "Số Loại Vé", "Tổng SL", "Tổng Tiền"};
        modelHoaDon = new DefaultTableModel(colsHD, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableHoaDon = new JTable(modelHoaDon);
        tableHoaDon.setRowHeight(25);
        JScrollPane scrollHD = new JScrollPane(tableHoaDon);
        scrollHD.setBorder(BorderFactory.createTitledBorder("Danh Sách Hóa Đơn"));
        
        add(topPanel, BorderLayout.NORTH);
        add(scrollHD, BorderLayout.CENTER);
    }
    
    private void loadComboData() {
        danhSachNguoiMua = controller.layDanhSachNguoiMua();
        danhSachVe = controller.layDanhSachVe();
        
        cboNguoiMua.removeAllItems();
        for (NguoiMua nm : danhSachNguoiMua) {
            cboNguoiMua.addItem(String.format("%05d - %s", nm.getMaNguoiMua(), nm.getHoTen()));
        }
        
        cboVe.removeAllItems();
        for (Ve ve : danhSachVe) {
            cboVe.addItem(String.format("%05d - %s - %,.0f VNĐ", 
                    ve.getMaVe(), ve.getLoaiGhe(), ve.getDonGia()));
        }
    }
    
    private void themChiTiet() {
        try {
            if (danhSachChiTiet.size() >= 4) {
                JOptionPane.showMessageDialog(this, "Không được quá 4 loại vé!", 
                        "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int veIndex = cboVe.getSelectedIndex();
            if (veIndex < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn vé!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int soLuong = Integer.parseInt(txtSoLuong.getText());
            if (soLuong <= 0 || soLuong > 20) {
                JOptionPane.showMessageDialog(this, "Số lượng phải từ 1 đến 20!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Ve ve = danhSachVe.get(veIndex);
            ChiTietHD chiTiet = new ChiTietHD(ve, soLuong);
            danhSachChiTiet.add(chiTiet);
            
            modelChiTiet.addRow(new Object[]{
                ve.getLoaiGhe(),
                String.format("%,.0f", ve.getDonGia()),
                soLuong,
                String.format("%,.0f", chiTiet.tinhThanhTien())
            });
            
            txtSoLuong.setText("");
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void xoaChiTiet() {
        int row = tableChiTiet.getSelectedRow();
        if (row >= 0) {
            danhSachChiTiet.remove(row);
            modelChiTiet.removeRow(row);
        } else {
            JOptionPane.showMessageDialog(this, "Chọn chi tiết cần xóa!", 
                    "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void luuHoaDon() {
        int nmIndex = cboNguoiMua.getSelectedIndex();
        if (nmIndex < 0) {
            JOptionPane.showMessageDialog(this, "Chọn người mua!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (danhSachChiTiet.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thêm chi tiết hóa đơn!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        NguoiMua nm = danhSachNguoiMua.get(nmIndex);
        
        if (controller.taoHoaDon(nm.getMaNguoiMua(), new ArrayList<>(danhSachChiTiet))) {
            huyBo();
            loadHoaDon();
        }
    }
    
    private void huyBo() {
        danhSachChiTiet.clear();
        modelChiTiet.setRowCount(0);
        txtSoLuong.setText("");
        if (cboNguoiMua.getItemCount() > 0) cboNguoiMua.setSelectedIndex(0);
        if (cboVe.getItemCount() > 0) cboVe.setSelectedIndex(0);
    }
    
    private void loadHoaDon() {
        modelHoaDon.setRowCount(0);
        List<HoaDon> list = controller.layDanhSach();
        for (HoaDon hd : list) {
            modelHoaDon.addRow(new Object[]{
                String.format("%05d", hd.getNguoiMua().getMaNguoiMua()),
                hd.getNguoiMua().getHoTen(),
                hd.getNguoiMua().getLoai(),
                hd.getDanhSachChiTiet().size(),
                hd.tinhTongSoLuong(),
                String.format("%,.0f", hd.tinhTongTien())
            });
        }
    }
}
