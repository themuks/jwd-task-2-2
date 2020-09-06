package com.kuntsevich.task2.parser;

import com.kuntsevich.task2.entity.Sentence;
import com.kuntsevich.task2.entity.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {
    public Text parseText(String str) {
        Pattern pattern = Pattern.compile(".+[.!?;]",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        List<Sentence> sentences = new ArrayList<>();
        SentenceParser sentenceParser = new SentenceParser();
        while (matcher.find()) {
            String sentence = matcher.group();
            sentences.add(sentenceParser.parseSentence(sentence));
        }
        return new Text(sentences);
    }
}