package by.bsu.regular.action;

import by.bsu.regular.entity.Component;
import by.bsu.regular.entity.Composite;
import by.bsu.regular.entity.Lexeme;
import by.bsu.regular.entity.Type;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 29.03.16
 * Time: 3:07
 * To change this template use File | Settings | File Templates.
 */
public class ComponentExtracter {
    public static ArrayList<String> operators(Composite element) {
        ArrayList<String> list = new ArrayList<>();
        for (Component c : element.getComponents()) {
            if (c instanceof Composite) {
                Composite composite = (Composite) c;
                if (Type.OPERATOR.equals(composite.getType())) {
                    list.add(c.toString());
                } else {
                    list.addAll(operators(composite));
                }
            }
        }
        return list;
    }

    public static ArrayList<String> lexemes(Composite element) {
        ArrayList<String> list = new ArrayList<>();
        for (Component c : element.getComponents()) {
            if (c instanceof Lexeme) {
                list.add(c.toString());
            } else {
                list.addAll(lexemes((Composite) c));
            }
        }
        return list;
    }
}
