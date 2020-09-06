package com.kuntsevich.task2.model.dao;

import com.kuntsevich.task2.exception.DaoException;

public interface StringDao {
    String findAll() throws DaoException;
}