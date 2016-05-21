package by.bsu.regular.parser;

import by.bsu.regular.entity.Lexeme;
import by.bsu.regular.entity.Type;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 20.03.16
 * Time: 7:04
 * To change this template use File | Settings | File Templates.
 */
public class MethodParser extends AbstractParser {
    public MethodParser(Type type) {
        super(type);
    }

    @Override
    public void parse(String text) {
        boolean isNotAbstract = text.contains("{");
        String signature = (isNotAbstract ? text.substring(0, text.indexOf('{') + 1) : text);

        Pattern lexemePattern = Pattern.compile(LEXEME_REGEXP);
        Matcher lexemeMatcher = lexemePattern.matcher(signature);
        while (lexemeMatcher.find()) {
            String group = lexemeMatcher.group();
            element.add(new Lexeme(group));
        }

        if (isNotAbstract) {
            text = parse(text, CLASS_REGEXP, new ClassParser(Type.CLASS));
            parse(text, OPERATOR_REGEXP, successorOperator);

            element.add(new Lexeme("}", element.getDepth()));
        }
    }
}
