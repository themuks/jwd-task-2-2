package com.kuntsevich.task2.model.dao.impl;

import com.kuntsevich.task2.exception.DaoException;
import com.kuntsevich.task2.model.dao.StringDao;
import com.kuntsevich.task2.model.dao.constant.FileDaoConstant;

import java.io.*;

public class StringDaoImpl implements StringDao {
    @Override
    public String findAll() throws DaoException {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(FileDaoConstant.DB_FILE_PATH);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            throw new DaoException("Can't find database file", e);
        } catch (IOException e) {
            throw new DaoException("Error reading from database", e);
        }
        return stringBuilder.toString();
    }
}
