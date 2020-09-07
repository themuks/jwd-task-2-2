package com.kuntsevich.task2.entity;

public class CodeLine implements TextConstruct {
    private String codeLine;

    public CodeLine(String codeLine) {
        this.codeLine = codeLine;
    }

    public String getCodeLine() {
        return codeLine;
    }

    public void setCodeLine(String codeLine) {
        this.codeLine = codeLine;
    }

    @Override
    public String toString() {
        return codeLine;
    }
}