package com.app.qlvetau.view;

import com.app.qlvetau.controller.NguoiMuaController;
import com.app.qlvetau.model.entity.NguoiMua;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Form quản lý Người Mua - UI đơn giản
 */
public class FormNguoiMua extends JFrame {
    private NguoiMuaController controller;
    private JTextField txtHoTen, txtDiaChi;
    private JComboBox<String> cboLoai;
    private JTable table;
    private DefaultTableModel tableModel;
    
    public FormNguoiMua() {
        controller = new NguoiMuaController();
        initComponents();
        loadData();
    }
    
    private void initComponents() {
        setTitle("Quản Lý Người Mua Vé");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));
        
        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Thông Tin Người Mua"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        inputPanel.add(new JLabel("Họ tên:"));
        txtHoTen = new JTextField();
        inputPanel.add(txtHoTen);
        
        inputPanel.add(new JLabel("Địa chỉ:"));
        txtDiaChi = new JTextField();
        inputPanel.add(txtDiaChi);
        
        inputPanel.add(new JLabel("Loại:"));
        cboLoai = new JComboBox<>(new String[]{"Mua lẻ", "Mua tập thể", "Mua qua mạng"});
        inputPanel.add(cboLoai);
        
        // Panel buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        JButton btnThem = new JButton("Thêm");
        JButton btnXoa = new JButton("Xóa Rỗng");
        JButton btnTaiLai = new JButton("Tải Lại");
        
        btnThem.addActionListener(e -> themNguoiMua());
        btnXoa.addActionListener(e -> xoaRong());
        btnTaiLai.addActionListener(e -> loadData());
        
        btnPanel.add(btnThem);
        btnPanel.add(btnXoa);
        btnPanel.add(btnTaiLai);
        
        inputPanel.add(new JLabel());
        inputPanel.add(btnPanel);
        
        // Table
        String[] columns = {"Mã", "Họ Tên", "Địa Chỉ", "Loại"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(25);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh Sách Người Mua"));
        
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void themNguoiMua() {
        if (controller.themNguoiMua(txtHoTen.getText(), txtDiaChi.getText(), 
                (String) cboLoai.getSelectedItem())) {
            xoaRong();
            loadData();
        }
    }
    
    private void xoaRong() {
        txtHoTen.setText("");
        txtDiaChi.setText("");
        cboLoai.setSelectedIndex(0);
        txtHoTen.requestFocus();
    }
    
    private void loadData() {
        tableModel.setRowCount(0);
        List<NguoiMua> list = controller.layDanhSach();
        for (NguoiMua nm : list) {
            tableModel.addRow(new Object[]{
                String.format("%05d", nm.getMaNguoiMua()),
                nm.getHoTen(),
                nm.getDiaChi(),
                nm.getLoai()
            });
        }
    }
}
