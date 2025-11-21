package com.app.qlvetau.view;

import com.app.qlvetau.controller.SortController;
import com.app.qlvetau.model.entity.HoaDon;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Form sắp xếp Hóa Đơn - UI đơn giản
 */
public class FormSapXep extends JFrame {
    private SortController controller;
    private JRadioButton rdoHoTen, rdoSoLuong;
    private JTable table;
    private DefaultTableModel tableModel;
    
    public FormSapXep() {
        controller = new SortController();
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Sắp Xếp Hóa Đơn");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));
        
        // Panel điều khiển
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Lựa Chọn Sắp Xếp"));
        
        rdoHoTen = new JRadioButton("Theo Họ Tên Người Mua");
        rdoSoLuong = new JRadioButton("Theo Số Lượng Vé (Giảm Dần)");
        
        ButtonGroup group = new ButtonGroup();
        group.add(rdoHoTen);
        group.add(rdoSoLuong);
        rdoHoTen.setSelected(true);
        
        JButton btnSapXep = new JButton("Sắp Xếp & Lưu");
        btnSapXep.addActionListener(e -> sapXep());
        
        controlPanel.add(rdoHoTen);
        controlPanel.add(rdoSoLuong);
        controlPanel.add(btnSapXep);
        
        // Table
        String[] columns = {"Mã KH", "Họ Tên", "Địa Chỉ", "Loại", "Số Loại Vé", "Tổng SL", "Tổng Tiền"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(25);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh Sách Hóa Đơn Đã Sắp Xếp"));
        
        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void sapXep() {
        List<HoaDon> list;
        
        if (rdoHoTen.isSelected()) {
            list = controller.sapXepTheoHoTen();
            JOptionPane.showMessageDialog(this, 
                    "Đã sắp xếp theo Họ Tên và lưu vào file!", 
                    "Thành công", 
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            list = controller.sapXepTheoSoLuong();
            JOptionPane.showMessageDialog(this, 
                    "Đã sắp xếp theo Số Lượng Vé (Giảm Dần) và lưu vào file!", 
                    "Thành công", 
                    JOptionPane.INFORMATION_MESSAGE);
        }
        
        loadData(list);
    }
    
    private void loadData(List<HoaDon> list) {
        tableModel.setRowCount(0);
        for (HoaDon hd : list) {
            tableModel.addRow(new Object[]{
                String.format("%05d", hd.getNguoiMua().getMaNguoiMua()),
                hd.getNguoiMua().getHoTen(),
                hd.getNguoiMua().getDiaChi(),
                hd.getNguoiMua().getLoai(),
                hd.getDanhSachChiTiet().size(),
                hd.tinhTongSoLuong(),
                String.format("%,.0f", hd.tinhTongTien())
            });
        }
    }
}
