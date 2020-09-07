package com.kuntsevich.task2.model.service;

import com.kuntsevich.task2.entity.Sentence;
import com.kuntsevich.task2.entity.Text;
import com.kuntsevich.task2.entity.TextConstruct;
import com.kuntsevich.task2.entity.Word;
import com.kuntsevich.task2.exception.ServiceException;

import java.util.List;

public interface TextService {
    String findAll() throws ServiceException;

    Text removeWordsStartingWithConsonant(Text text);

    Text replaceWordsWithExactLength(Text text, String newWord, int length);

    List<Word> wordCharCountSort(Text text, char c);

    String findMaxLengthPalindrome(Text text);

    List<TextConstruct> sentenceInOrderOfWordCount(Text text);

    Text convertStringToText(String str);
}