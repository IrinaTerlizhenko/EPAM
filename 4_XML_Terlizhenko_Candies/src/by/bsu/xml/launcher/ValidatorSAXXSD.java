package by.bsu.xml.launcher;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 03.04.16
 * Time: 7:39
 * To change this template use File | Settings | File Templates.
 */
public class ValidatorSAXXSD {
    public static void main(String[ ] args) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String filename = "resource/candies.xml";
        String schemaName = "resource/candies.xsd";
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
// создание схемы
            Schema schema = factory.newSchema(schemaLocation);
// создание валидатора на основе схемы
            Validator validator = schema.newValidator();
// проверка документа
            Source source = new StreamSource(filename);
            validator.validate(source);
            System.out.println(filename + " is valid.");
        } catch (SAXException e) {
            System.err.print("validation "+ filename + " is not valid because "
                    + e.getMessage());
        } catch (IOException e) {
            System.err.print(filename + " is not valid because "
                    + e.getMessage());
        }
    }
}
