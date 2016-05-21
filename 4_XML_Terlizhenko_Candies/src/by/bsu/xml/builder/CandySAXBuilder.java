package by.bsu.xml.builder;

import by.bsu.xml.builder.handler.CandyHandler;
import by.bsu.xml.entity.Candy;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 04.04.16
 * Time: 5:37
 * To change this template use File | Settings | File Templates.
 */
public class CandySAXBuilder extends AbstractCandyBuilder{
    //private Set<Candy> candies;
    private CandyHandler sh;
    private XMLReader reader;
    public CandySAXBuilder() {
// создание SAX-анализатора
        sh = new CandyHandler();
        try {
// создание объекта-обработчика
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(sh);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        }
    }
    public Set<Candy> getCandies() {
        return candies;
    }
    public void buildSetCandies(String fileName) {
        try {
// разбор XML-документа
            reader.parse(fileName);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        } catch (IOException e) {
            System.err.print("ошибка I/О потока: " + e);
        }
        candies = sh.getCandies();
    }
}
