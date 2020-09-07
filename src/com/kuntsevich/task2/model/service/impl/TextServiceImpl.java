package com.kuntsevich.task2.model.service.impl;

import com.kuntsevich.task2.entity.*;
import com.kuntsevich.task2.exception.DaoException;
import com.kuntsevich.task2.exception.ServiceException;
import com.kuntsevich.task2.model.dao.StringDao;
import com.kuntsevich.task2.model.dao.factory.StringDaoFactory;
import com.kuntsevich.task2.model.service.TextService;
import com.kuntsevich.task2.parser.TextParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TextServiceImpl implements TextService {
    private static final String CONSONANT_REGEX = "^[бвгджзйлмнрпфктшсхцчщБВГДЖЗЙЛМНРПФКТШСХЦЧЩ][а-яА-Яa-zA-Z]*";

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
    public Text removeWordsStartingWithConsonant(Text text) {
        List<? extends TextConstruct> textConstructs = text.getTextConstructs();
        List<SentenceConstruct> wordsToRemove = new ArrayList<>();
        for (TextConstruct textConstruct : textConstructs) {
            if (textConstruct instanceof Sentence) {
                List<? extends SentenceConstruct> sentenceConstructs = ((Sentence) textConstruct).getSentenceConstructs();
                for (SentenceConstruct sentenceConstruct : sentenceConstructs) {
                    if (sentenceConstruct instanceof Word) {
                        String str = ((Word) sentenceConstruct).getWord();
                        if (Pattern.matches(CONSONANT_REGEX, str)) {
                            wordsToRemove.add(sentenceConstruct);
                        }
                    }
                }
                ((Sentence) textConstruct).getSentenceConstructs().removeAll(wordsToRemove);
            }
        }
        return text;
    }

    public Text replaceWordsWithExactLength(Text text, String newWord, int length) {
        List<? extends TextConstruct> textConstructs = text.getTextConstructs();
        for (TextConstruct textConstruct : textConstructs) {
            if (textConstruct instanceof Sentence) {
                List<? extends SentenceConstruct> sentenceConstructs = ((Sentence) textConstruct).getSentenceConstructs();
                for (SentenceConstruct sentenceConstruct : sentenceConstructs) {
                    if (sentenceConstruct instanceof Word) {
                        String str = ((Word) sentenceConstruct).getWord();
                        if (str.length() == length) {
                            ((Word) sentenceConstruct).setWord(newWord);
                        }
                    }
                }
            }
        }
        return text;
    }

    @Override
    public List<Word> wordCharCountSort(Text text, char c) {
        List<Word> words = extractWords(text);
        List<Word> wordsToRemove = new ArrayList<>();
        for (Word word : words) {
            if (word.getWord().indexOf(c) == -1) {
                wordsToRemove.add(word);
            }
        }
        words.removeAll(wordsToRemove);
        words.sort(((o1, o2) -> {
            String str1 = o1.getWord();
            String str2 = o2.getWord();
            int count1 = 0;
            for (int i = 0; i < str1.length(); i++) {
                if (str1.charAt(i) == c) {
                    count1++;
                }
            }
            int count2 = 0;
            for (int i = 0; i < str2.length(); i++) {
                if (str2.charAt(i) == c) {
                    count2++;
                }
            }
            int result = 0;
            if (count1 > count2) {
                result = 1;
            }
            if (count1 < count2) {
                result = -1;
            }
            if (count1 == count2) {
                result = str1.compareTo(str2);
            }
            return result;
        }));
        return words;
    }

    @Override
    public String findMaxLengthPalindrome(Text text) {
        List<Word> words = extractWords(text);
        String resultString = "";
        boolean flag;
        for (Word word : words) {
            String str = word.getWord();
            flag = true;
            for (int i = 0; i < str.length() / 2; i++) {
                if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                    flag = false;
                }
            }
            if (flag && resultString.length() < str.length()) {
                resultString = str;
            }
        }
        return resultString;
    }

    @Override
    public List<TextConstruct> sentenceInOrderOfWordCount(Text text) {
        List<TextConstruct> textConstructs = extractSentences(text);
        textConstructs.sort(((o1, o2) -> {
            Sentence s1 = (Sentence) o1;
            Sentence s2 = (Sentence) o2;
            int size1 = s1.getSentenceConstructs().size();
            int size2 = s2.getSentenceConstructs().size();
            int result = 0;
            if (size1 < size2) {
                result = -1;
            }
            if (size1 > size2) {
                result = 1;
            }
            return result;
        }));
        return textConstructs;
    }

    @Override
    public Text convertStringToText(String str) {
        TextParser textParser = new TextParser();
        return textParser.parseText(str);
    }

    private List<TextConstruct> extractSentences(Text text) {
        List<? extends TextConstruct> textConstructs = text.getTextConstructs();
        List<TextConstruct> sentences = new ArrayList<>();
        for (TextConstruct construct : textConstructs) {
            if (construct instanceof Sentence) {
                sentences.add(construct);
            }
        }
        return sentences;
    }

    private List<Word> extractWords(Text text) {
        List<? extends TextConstruct> textConstructs = text.getTextConstructs();
        List<Word> words = new ArrayList<>();
        for (TextConstruct construct : textConstructs) {
            if (construct instanceof Sentence) {
                for (SentenceConstruct sentenceConstruct : ((Sentence) construct).getSentenceConstructs()) {
                    if (sentenceConstruct instanceof Word) {
                        words.add((Word) sentenceConstruct);
                    }
                }
            }
        }
        return words;
    }
}