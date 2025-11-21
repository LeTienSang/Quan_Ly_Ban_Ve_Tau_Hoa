/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quanlybanvetauhoa;

import com.app.qlvetau.view.MainFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Lớp chính khởi chạy ứng dụng Quản Lý Bán Vé Tàu Hỏa
 * @author Admin
 */
public class QuanLyBanVeTauHoa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Thiết lập Look and Feel
        try {
            // Sử dụng Nimbus Look and Feel cho giao diện đẹp hơn
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Nếu Nimbus không có, sử dụng Look and Feel mặc định
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        // Khởi chạy giao diện trên Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
