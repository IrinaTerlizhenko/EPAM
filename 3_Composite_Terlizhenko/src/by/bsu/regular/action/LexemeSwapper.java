package by.bsu.regular.action;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 12.03.16
 * Time: 11:17
 * To change this template use File | Settings | File Templates.
 */
public class LexemeSwapper {
    private static final String SPLIT_REGEXP = "\\s+";

    public static void swapLexemes(ArrayList<String> operators) {
        for (int i = 0; i < operators.size(); i++) {
            String op = operators.get(i);
            String[] lexemes = op.split(SPLIT_REGEXP);
            if (lexemes.length > 1) {
                String buffer = lexemes[0];
                lexemes[0] = lexemes[lexemes.length - 1];
                lexemes[lexemes.length - 1] = buffer;
                String opNew = String.join(" ", lexemes);
                operators.set(i, opNew);
            }
        }
    }
}
