package by.bsu.regular.launcher;

import by.bsu.regular.action.*;
import by.bsu.regular.entity.Composite;
import by.bsu.regular.parser.MainParser;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 12.03.16
 * Time: 3:20
 * To change this template use File | Settings | File Templates.
 */
public class Launcher {
    private static final String IN_FILE = "resource/TestFile.java";
    private static final String OUT_FILE = "resource/TestFileOut.java";
    private static final String MODIFIED_OUT_FILE = "resource/TestFileOutModified.txt";
    private static final String OPERATORS_SORTED_FILE = "resource/TestFileOperatorsSorted.txt";
    private static final String LEXEMES_SWAPPED_FILE = "resource/TestFileLexemesSwapped.txt";

    private static int eraseLength = 6;
    private static char eraseStart = 't';

    public static void main(String[] args) {
        MainParser parser = new MainParser();
        parser.parseText(IN_FILE);
        CompositeWriter.writeFile(OUT_FILE, parser.getElement());

        Composite composite = parser.getElement();
        ArrayList<String> operators = ComponentExtracter.operators(composite);

        OperatorSorter.sort(operators);
        CompositeWriter.writeOperators(OPERATORS_SORTED_FILE, operators);

        LexemeSwapper.swapLexemes(operators);
        CompositeWriter.writeOperators(LEXEMES_SWAPPED_FILE, operators);

        LexemeEraser.eraseLexemes(composite, eraseLength, eraseStart);
        CompositeWriter.writeFile(MODIFIED_OUT_FILE, parser.getElement());
    }
}
