package com.app.qlvetau.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Tiện ích xử lý file chung
 */
public class FileUtil {
    
    /**
     * Đọc danh sách đối tượng từ file
     */
    public static <T> List<T> docFile(String fileName) throws IOException, ClassNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) ois.readObject();
        }
    }
    
    /**
     * Ghi danh sách đối tượng vào file
     */
    public static <T> void ghiFile(String fileName, List<T> data) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(data);
        }
    }
    
    /**
     * Kiểm tra file có tồn tại không
     */
    public static boolean kiemTraFile(String fileName) {
        return new File(fileName).exists();
    }
    
    /**
     * Tạo file mới nếu chưa tồn tại
     */
    public static void taoFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
            ghiFile(fileName, new ArrayList<>());
        }
    }
}
