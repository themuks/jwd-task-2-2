package com.kuntsevich.task2.entity;

import java.util.List;

public class Sentence implements TextConstruct {
    private static final char SPACE = ' ';
    private List<? extends SentenceConstruct> sentenceConstructs;

    public Sentence(List<? extends SentenceConstruct> sentenceConstructs) {
        this.sentenceConstructs = sentenceConstructs;
    }

    public List<? extends SentenceConstruct> getSentenceConstructs() {
        return sentenceConstructs;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (SentenceConstruct construct : sentenceConstructs) {
            if (construct instanceof Word) {
                stringBuilder.append(SPACE);
            }
            stringBuilder.append(construct.toString());
        }
        if (stringBuilder.length() != 0) {
            stringBuilder.delete(0, 1);
        }
        return stringBuilder.toString();
    }
}