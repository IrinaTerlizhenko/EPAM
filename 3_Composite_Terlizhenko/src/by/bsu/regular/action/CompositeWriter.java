package by.bsu.regular.action;

import by.bsu.regular.entity.Component;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 20.03.16
 * Time: 6:56
 * To change this template use File | Settings | File Templates.
 */
public class CompositeWriter {
    static Logger log = Logger.getLogger(CompositeWriter.class);

    public static void writeFile(String filename, Component element) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(filename))))) {
            writer.print(element.toString());
        } catch (IOException e) {
            log.error("File cannot be written", e);
        }
    }

    public static void writeOperators(String filename, ArrayList<String> operators) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(filename))))) {
            String text = String.join("", operators);
            writer.print(text);
        } catch (IOException e) {
            log.error("File cannot be written", e);
        }
    }
}
