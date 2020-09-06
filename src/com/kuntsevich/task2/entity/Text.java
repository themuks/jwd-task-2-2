package com.kuntsevich.task2.entity;

import java.util.List;

public class Text implements TextConstruct {
    private static final char SPACE = ' ';
    private List<? extends TextConstruct> textConstructs;

    public Text(List<? extends TextConstruct> textConstructs) {
        this.textConstructs = textConstructs;
    }

    @Override
    public String constructStringRepresentation() {
        StringBuilder stringBuilder = new StringBuilder();
        for (TextConstruct construct : textConstructs) {
            stringBuilder.append(construct.constructStringRepresentation()).append(SPACE);
        }
        return stringBuilder.toString();
    }
}
