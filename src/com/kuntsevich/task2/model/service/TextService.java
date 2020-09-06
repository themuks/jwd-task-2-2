package com.kuntsevich.task2.model.service;

import com.kuntsevich.task2.entity.Text;
import com.kuntsevich.task2.exception.ServiceException;

public interface TextService {
    String findAll() throws ServiceException;
    Text convertStringToText(String str);
}