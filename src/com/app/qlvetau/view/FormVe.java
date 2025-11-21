package com.app.qlvetau.view;

import com.app.qlvetau.controller.VeController;
import com.app.qlvetau.model.entity.Ve;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Form quản lý Vé Tàu - UI đơn giản
 */
public class FormVe extends JFrame {
    private VeController controller;
    private JTextField txtLoaiGhe, txtDonGia;
    private JTable table;
    private DefaultTableModel tableModel;
    
    public FormVe() {
        controller = new VeController();
        initComponents();
        loadData();
    }
    
    private void initComponents() {
        setTitle("Quản Lý Vé Tàu");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));
        
        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Thông Tin Vé"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        inputPanel.add(new JLabel("Loại ghế:"));
        txtLoaiGhe = new JTextField();
        inputPanel.add(txtLoaiGhe);
        
        inputPanel.add(new JLabel("Đơn giá (VNĐ):"));
        txtDonGia = new JTextField();
        inputPanel.add(txtDonGia);
        
        // Panel buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        JButton btnThem = new JButton("Thêm");
        JButton btnXoa = new JButton("Xóa Rỗng");
        JButton btnTaiLai = new JButton("Tải Lại");
        
        btnThem.addActionListener(e -> themVe());
        btnXoa.addActionListener(e -> xoaRong());
        btnTaiLai.addActionListener(e -> loadData());
        
        btnPanel.add(btnThem);
        btnPanel.add(btnXoa);
        btnPanel.add(btnTaiLai);
        
        inputPanel.add(new JLabel());
        inputPanel.add(btnPanel);
        
        // Table
        String[] columns = {"Mã Vé", "Loại Ghế", "Đơn Giá (VNĐ)"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(25);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh Sách Vé"));
        
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void themVe() {
        if (controller.themVe(txtLoaiGhe.getText(), txtDonGia.getText())) {
            xoaRong();
            loadData();
        }
    }
    
    private void xoaRong() {
        txtLoaiGhe.setText("");
        txtDonGia.setText("");
        txtLoaiGhe.requestFocus();
    }
    
    private void loadData() {
        tableModel.setRowCount(0);
        List<Ve> list = controller.layDanhSach();
        for (Ve ve : list) {
            tableModel.addRow(new Object[]{
                String.format("%05d", ve.getMaVe()),
                ve.getLoaiGhe(),
                String.format("%,.0f", ve.getDonGia())
            });
        }
    }
}
