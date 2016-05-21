package by.bsu.regular.parser;

import by.bsu.regular.action.CompositeReader;
import by.bsu.regular.entity.Composite;
import by.bsu.regular.entity.Type;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 20.03.16
 * Time: 7:06
 * To change this template use File | Settings | File Templates.
 */
public class MainParser {
    private Composite element;

    public void parseText(String fileName) {
        FileParser fileParser = new FileParser(Type.FILE);
        element = fileParser.chain(CompositeReader.readFile(fileName));
    }

    public Composite getElement() {
        return element;
    }
}
