package com.kuntsevich.task2.parser;

import com.kuntsevich.task2.entity.Text;
import com.kuntsevich.task2.entity.TextConstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {
    private static final char SEPARATOR = '-';
    private static final int SEPARATOR_LENGTH = 3;
    private static final String SENTENCE_AND_CODE_BLOCK_REGEX = SEPARATOR + "{" + SEPARATOR_LENGTH + "}";

    public Text parseText(String str) {
        Pattern pattern = Pattern.compile(SENTENCE_AND_CODE_BLOCK_REGEX,
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(str);
        List<TextConstruct> sentences = new ArrayList<>();
        SentenceParser sentenceParser = new SentenceParser();
        CodeBlockParser codeBlockParser = new CodeBlockParser();
        int previousPosition = 0;
        boolean flag = true;
        while (matcher.find()) {
            String strToParse = str.substring(previousPosition, matcher.start());
            previousPosition = matcher.start() + SEPARATOR_LENGTH;
            if (flag) {
                sentences.add(sentenceParser.parseSentence(strToParse));
            } else {
                sentences.add(codeBlockParser.parseCodeBlock(strToParse));
            }
            flag = !flag;
        }
        return new Text(sentences);
    }
}