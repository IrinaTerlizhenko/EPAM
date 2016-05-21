package by.bsu.xml.factory;

import by.bsu.xml.builder.AbstractCandyBuilder;
import by.bsu.xml.builder.CandyDOMBuilder;
import by.bsu.xml.builder.CandySAXBuilder;
import by.bsu.xml.builder.CandyStAXBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 04.04.16
 * Time: 8:41
 * To change this template use File | Settings | File Templates.
 */
public class CandyBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }
    public AbstractCandyBuilder createCandyBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new CandyDOMBuilder();
            case STAX:
                return new CandyStAXBuilder();
            case SAX:
                return new CandySAXBuilder();
            default:
                throw new EnumConstantNotPresentException (type.getDeclaringClass(), type.name());   //todo
        }
    }
}
