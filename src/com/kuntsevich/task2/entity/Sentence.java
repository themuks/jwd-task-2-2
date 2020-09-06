package com.kuntsevich.task2.entity;

import java.util.List;

public class Sentence implements TextConstruct {
    private static final char SPACE = ' ';
    private List<? extends SentenceConstruct> sentenceConstructs;

    public Sentence(List<? extends SentenceConstruct> sentenceConstructs) {
        this.sentenceConstructs = sentenceConstructs;
    }

    @Override
    public String constructStringRepresentation() {
        StringBuilder stringBuilder = new StringBuilder();
        for (SentenceConstruct construct : sentenceConstructs) {
            if (construct instanceof Word) {
                stringBuilder.append(SPACE);
            }
            stringBuilder.append(construct.constructStringRepresentation());
        }
        if (stringBuilder.length() != 0) {
            stringBuilder.delete(0, 1);
        }
        return stringBuilder.toString();
    }
}
