package com.kuntsevich.task2.entity;

public class PunctuationMark implements SentenceConstruct {
    private char mark;

    public PunctuationMark(char mark) {
        this.mark = mark;
    }

    @Override
    public String constructStringRepresentation() {
        return String.valueOf(mark);
    }
}
