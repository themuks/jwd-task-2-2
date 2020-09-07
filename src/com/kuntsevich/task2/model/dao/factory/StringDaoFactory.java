package com.kuntsevich.task2.model.dao.factory;

import com.kuntsevich.task2.model.dao.StringDao;
import com.kuntsevich.task2.model.dao.impl.StringDaoImpl;

public class StringDaoFactory {
    private static volatile StringDaoFactory instance;
    private StringDao stringDao = new StringDaoImpl();

    private StringDaoFactory() {
    }

    public static StringDaoFactory getInstance() {
        if (instance == null) {
            synchronized (StringDaoFactory.class) {
                if (instance == null) {
                    instance = new StringDaoFactory();
                }
            }
        }
        return instance;
    }

    public StringDao getStringDao() {
        return stringDao;
    }
}