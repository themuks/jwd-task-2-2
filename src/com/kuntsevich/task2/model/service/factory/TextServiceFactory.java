package com.kuntsevich.task2.model.service.factory;

import com.kuntsevich.task2.model.service.TextService;
import com.kuntsevich.task2.model.service.impl.TextServiceImpl;

public class TextServiceFactory {
    private static volatile TextServiceFactory instance;

    private TextService textService = new TextServiceImpl();

    private TextServiceFactory() {
    }

    public static TextServiceFactory getInstance() {
        if (instance == null) {
            synchronized (TextServiceFactory.class) {
                if (instance == null) {
                    instance = new TextServiceFactory();
                }
            }
        }
        return instance;
    }

    public TextService getTextService() {
        return textService;
    }
}
