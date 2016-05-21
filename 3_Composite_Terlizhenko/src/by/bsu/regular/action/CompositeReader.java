package by.bsu.regular.action;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 20.03.16
 * Time: 6:55
 * To change this template use File | Settings | File Templates.
 */
public class CompositeReader {
    static Logger log = Logger.getLogger(CompositeReader.class);

    public static String readFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (Scanner fileInput = new Scanner(new File(fileName))) {
            while (fileInput.hasNextLine()) {
                builder.append(fileInput.nextLine() + "\n");
            }
        } catch (IOException e) {
            log.fatal("File cannot be read", e);
            throw new RuntimeException(e);
        }
        return builder.toString();
    }
}