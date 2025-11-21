package com.app.qlvetau.view;

import javax.swing.*;
import java.awt.*;

/**
 * Frame chính của ứng dụng - UI đơn giản
 */
public class MainFrame extends JFrame {
    
    public MainFrame() {
        initComponents();
    }
    
    private void initComponents() {
        setTitle("QUẢN LÝ BÁN VÉ TÀU HỎA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        // Panel tiêu đề
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(52, 152, 219));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        
        JLabel lblTitle = new JLabel("HỆ THỐNG QUẢN LÝ BÁN VÉ TÀU HỎA");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);
        
        // Panel các nút chức năng
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));
        
        JButton btnNguoiMua = createButton("1. Quản Lý Người Mua", new Color(46, 204, 113));
        JButton btnVe = createButton("2. Quản Lý Vé Tàu", new Color(52, 152, 219));
        JButton btnHoaDon = createButton("3. Lập Hóa Đơn", new Color(155, 89, 182));
        JButton btnSapXep = createButton("4. Sắp Xếp Hóa Đơn", new Color(230, 126, 34));
        JButton btnBaoCao = createButton("5. Bảng Kê Thanh Toán", new Color(231, 76, 60));
        
        btnNguoiMua.addActionListener(e -> openFormNguoiMua());
        btnVe.addActionListener(e -> openFormVe());
        btnHoaDon.addActionListener(e -> openFormHoaDon());
        btnSapXep.addActionListener(e -> openFormSapXep());
        btnBaoCao.addActionListener(e -> openFormBaoCao());
        
        buttonPanel.add(btnNguoiMua);
        buttonPanel.add(btnVe);
        buttonPanel.add(btnHoaDon);
        buttonPanel.add(btnSapXep);
        buttonPanel.add(btnBaoCao);
        
        // Panel footer
        JPanel footerPanel = new JPanel();
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JButton btnThoat = new JButton("Thoát");
        btnThoat.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, 
                    "Bạn có chắc chắn muốn thoát?", 
                    "Xác nhận", 
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        footerPanel.add(btnThoat);
        
        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }
    
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
    
    private void openFormNguoiMua() {
        FormNguoiMua form = new FormNguoiMua();
        form.setVisible(true);
    }
    
    private void openFormVe() {
        FormVe form = new FormVe();
        form.setVisible(true);
    }
    
    private void openFormHoaDon() {
        FormHoaDon form = new FormHoaDon();
        form.setVisible(true);
    }
    
    private void openFormSapXep() {
        FormSapXep form = new FormSapXep();
        form.setVisible(true);
    }
    
    private void openFormBaoCao() {
        FormBaoCao form = new FormBaoCao();
        form.setVisible(true);
    }
}
