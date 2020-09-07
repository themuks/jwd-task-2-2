package com.kuntsevich.task2.parser;

import com.kuntsevich.task2.entity.CodeBlock;
import com.kuntsevich.task2.entity.CodeLine;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeBlockParser {
    private static final String CODE_LINE_REGEX = ".+";

    public CodeBlock parseCodeBlock(String str) {
        Pattern pattern = Pattern.compile(CODE_LINE_REGEX);
        Matcher matcher = pattern.matcher(str);
        List<CodeLine> codeLines = new ArrayList<>();
        while (matcher.find()) {
            String codeLineString = matcher.group();
            CodeLine codeLine = new CodeLine(codeLineString);
            codeLines.add(codeLine);
        }
        return new CodeBlock(codeLines);
    }
}
