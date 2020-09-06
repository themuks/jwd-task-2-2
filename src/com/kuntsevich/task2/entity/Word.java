package com.kuntsevich.task2.entity;

public class Word implements SentenceConstruct {
    private String word;

    public Word(String word) {
        this.word = word;
    }

    @Override
    public String constructStringRepresentation() {
        return word;
    }
}
