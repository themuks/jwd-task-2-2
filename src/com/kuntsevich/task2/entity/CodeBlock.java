package com.kuntsevich.task2.entity;

import java.util.ArrayList;
import java.util.List;

public class CodeBlock implements TextConstruct {
    private List<CodeLine> codeLines;

    public CodeBlock(List<CodeLine> codeLines) {
        this.codeLines = codeLines;
    }

    public List<CodeLine> getCodeLines() {
        return codeLines;
    }

    public void setCodeLines(List<CodeLine> codeLines) {
        this.codeLines = codeLines;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (CodeLine codeLine : codeLines) {
            stringBuilder.append(codeLine).append("\n");
        }
        return stringBuilder.toString();
    }
}