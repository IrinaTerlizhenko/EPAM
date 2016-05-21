package by.bsu.regular.action;

import by.bsu.regular.entity.Component;
import by.bsu.regular.entity.Composite;
import by.bsu.regular.entity.Lexeme;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 26.03.16
 * Time: 9:03
 * To change this template use File | Settings | File Templates.
 */
public class LexemeEraser {
    public static void eraseLexemes(Composite element, int length, char startSymbol) {
        int index = 0;
        ArrayList<Component> list = element.getComponents();
        while (index < list.size()) {
            Component c = list.get(index);
            if (c instanceof Lexeme) {
                String lexeme = c.toString().trim();
                if (lexeme.length() == length && lexeme.charAt(0) == startSymbol) {
                    element.remove(c);
                } else {
                    index++;
                }
            } else {
                eraseLexemes((Composite) c, length, startSymbol);
                index++;
            }
        }
    }
}
