package com.kuntsevich.task2.parser;

import com.kuntsevich.task2.entity.PunctuationMark;
import com.kuntsevich.task2.entity.Sentence;
import com.kuntsevich.task2.entity.SentenceConstruct;
import com.kuntsevich.task2.entity.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser {
    private static final int FIRST_CHARACTER = 0;
    private static final String WORD_AND_PUNCTUATION_REGEX = "([а-яА-Яa-zA-Z0-9]+)|(\\p{Punct}+)";

    public Sentence parseSentence(String str) {
        Pattern pattern = Pattern.compile(WORD_AND_PUNCTUATION_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        List<SentenceConstruct> sentenceConstructs = new ArrayList<>();
        while (matcher.find()) {
            String sentenceConstruct = matcher.group(1);
            if (sentenceConstruct != null) {
                sentenceConstructs.add(new Word(sentenceConstruct));
            }
            sentenceConstruct = matcher.group(2);
            if (sentenceConstruct != null) {
                sentenceConstructs.add(new PunctuationMark(sentenceConstruct.charAt(FIRST_CHARACTER)));
            }
        }
        return new Sentence(sentenceConstructs);
    }
}
