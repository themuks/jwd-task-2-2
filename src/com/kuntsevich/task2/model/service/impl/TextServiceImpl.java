package com.kuntsevich.task2.model.service.impl;

import com.kuntsevich.task2.exception.DaoException;
import com.kuntsevich.task2.exception.ServiceException;
import com.kuntsevich.task2.model.dao.StringDao;
import com.kuntsevich.task2.model.dao.factory.StringDaoFactory;
import com.kuntsevich.task2.entity.Text;
import com.kuntsevich.task2.model.service.TextService;
import com.kuntsevich.task2.parser.TextParser;

public class TextServiceImpl implements TextService {
    @Override
    public String findAll() throws ServiceException {
        StringDaoFactory stringDaoFactory = StringDaoFactory.getInstance();
        StringDao stringDao = stringDaoFactory.getStringDao();
        try {
            return stringDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Can't find string", e);
        }
    }

    @Override
    public Text convertStringToText(String str) {
        TextParser textParser = new TextParser();
        return textParser.parseText(str);
    }
}
