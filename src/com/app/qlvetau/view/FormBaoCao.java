package com.app.qlvetau.view;

import com.app.qlvetau.controller.BaoCaoController;
import javax.swing.*;
import java.awt.*;

/**
 * Form báo cáo và bảng kê thanh toán - UI đơn giản
 */
public class FormBaoCao extends JFrame {
    private BaoCaoController controller;
    private JTextArea txtBaoCao;
    
    public FormBaoCao() {
        controller = new BaoCaoController();
        initComponents();
        loadBaoCao();
    }
    
    private void initComponents() {
        setTitle("Bảng Kê Thanh Toán");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));
        
        // Panel điều khiển
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton btnTaiLai = new JButton("Tải Lại");
        btnTaiLai.addActionListener(e -> loadBaoCao());
        
        JButton btnIn = new JButton("In Báo Cáo");
        btnIn.addActionListener(e -> inBaoCao());
        
        controlPanel.add(btnTaiLai);
        controlPanel.add(btnIn);
        
        // Text area hiển thị báo cáo
        txtBaoCao = new JTextArea();
        txtBaoCao.setEditable(false);
        txtBaoCao.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtBaoCao.setMargin(new Insets(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(txtBaoCao);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Bảng Kê Thanh Toán"));
        
        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void loadBaoCao() {
        String bangKe = controller.lapBangKe();
        txtBaoCao.setText(bangKe);
    }
    
    private void inBaoCao() {
        try {
            boolean complete = txtBaoCao.print();
            if (complete) {
                JOptionPane.showMessageDialog(this, 
                        "In báo cáo thành công!", 
                        "Thành công", 
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                    "Lỗi in báo cáo: " + e.getMessage(), 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
