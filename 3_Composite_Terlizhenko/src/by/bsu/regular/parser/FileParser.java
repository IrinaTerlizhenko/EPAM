package by.bsu.regular.parser;

import by.bsu.regular.entity.Type;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 20.03.16
 * Time: 7:02
 * To change this template use File | Settings | File Templates.
 */
public class FileParser extends AbstractParser {
    public FileParser(Type type) {
        super(type);
    }

    @Override
    public void parse(String text) {
        Pattern pattern = Pattern.compile(PACKAGE_IMPORT_REGEXP);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String group = matcher.group();
            element.add(successorOperator.chain(group));
        }

        Pattern classPattern = Pattern.compile(CLASS_REGEXP);
        Matcher classMatcher = classPattern.matcher(text);
        while (classMatcher.find()) {
            String group = classMatcher.group();
            element.add(successorClass.chain(group));
        }
    }
}
