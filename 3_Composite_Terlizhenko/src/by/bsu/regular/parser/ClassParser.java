package by.bsu.regular.parser;

import by.bsu.regular.entity.Lexeme;
import by.bsu.regular.entity.Type;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 20.03.16
 * Time: 7:00
 * To change this template use File | Settings | File Templates.
 */
public class ClassParser extends AbstractParser {
    public ClassParser(Type type) {
        super(type);
    }

    @Override
    public void parse(String text) {
        String signature = text.substring(0, text.indexOf('{') + 1);
        Pattern lexemePattern = Pattern.compile(LEXEME_REGEXP);
        Matcher lexemeMatcher = lexemePattern.matcher(signature);
        while (lexemeMatcher.find()) {
            String group = lexemeMatcher.group();
            element.add(new Lexeme(group));
        }

        text = text.substring(signature.length());
        text = parse(text, CLASS_REGEXP, new ClassParser(Type.CLASS));
        text = parse(text, METHOD_REGEXP, new MethodParser(Type.METHOD));
        text = parse(text, BLOCK_REGEXP, successorBlock);
        parse(text, FIELD_REGEXP, successorField);

        element.add(new Lexeme("}", element.getDepth()));
    }
}
