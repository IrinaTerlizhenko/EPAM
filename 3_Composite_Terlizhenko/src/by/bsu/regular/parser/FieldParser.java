package by.bsu.regular.parser;

import by.bsu.regular.entity.Lexeme;
import by.bsu.regular.entity.Type;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 20.03.16
 * Time: 7:01
 * To change this template use File | Settings | File Templates.
 */
public class FieldParser extends AbstractParser {
    public FieldParser(Type type) {
        super(type);
    }

    @Override
    public void parse(String text) {
        Pattern pattern = Pattern.compile(LEXEME_REGEXP);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String group = matcher.group();
            element.add(new Lexeme(group));
        }
    }
}
