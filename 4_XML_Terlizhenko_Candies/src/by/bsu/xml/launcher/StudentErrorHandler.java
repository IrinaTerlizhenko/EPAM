package by.bsu.xml.launcher;

import org.apache.log4j.Logger;
import org.apache.log4j.FileAppender;
import org.apache.log4j.SimpleLayout;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 02.04.16
 * Time: 13:51
 * To change this template use File | Settings | File Templates.
 */
public class StudentErrorHandler extends DefaultHandler {
    // создание регистратора ошибок для пакета by.bsu.valid
    private Logger logger = Logger.getLogger(StudentErrorHandler.class);
    public StudentErrorHandler(String log) throws IOException {
// установка файла и формата вывода ошибок
        logger.addAppender(new FileAppender(new SimpleLayout(), log));
    }
    public void warning(SAXParseException e) {
        logger.warn(getLineAddress(e) + "-" + e.getMessage());
    }
    public void error(SAXParseException e) {
        logger.error(getLineAddress(e) + " - " + e.getMessage());
    }
    public void fatalError(SAXParseException e) {
        logger.fatal(getLineAddress(e) + " - " + e.getMessage());
    }
    private String getLineAddress(SAXParseException e) {
// определение строки и столбца ошибки
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}
