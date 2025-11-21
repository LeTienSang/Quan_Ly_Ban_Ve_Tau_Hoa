package com.app.qlvetau.model.interfaces;

import java.io.Serializable;

/**
 * Interface cho các đối tượng có thể lưu vào file
 */
public interface IFileEntity extends Serializable {
    String toFileString();
}
