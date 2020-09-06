package com.kuntsevich.task2.model.dao.constant;

import java.util.ResourceBundle;

public class FileDaoConstant {
    public static final String DB_FILE_PATH;
    static {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        DB_FILE_PATH = resource.getString("path").concat(resource.getString("name"));
    }
}