package by.bsu.nypresent.main;

import by.bsu.nypresent.action.SweetReader;
import by.bsu.nypresent.action.SweetSearcher;
import by.bsu.nypresent.action.SweetSorter;
import by.bsu.nypresent.collection.Present;
import by.bsu.nypresent.report.ConsolePrinter;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 22.02.16
 * Time: 12:30
 * To change this template use File | Settings | File Templates.
 */
public class Runner {
    static {
        new DOMConfigurator().doConfigure("log4j.xml", LogManager.getLoggerRepository());
    }

    private static final String filename = "resource/sweets.txt";
    private static final double MIN_SUGAR = 4.9;
    private static final double MAX_SUGAR = 6.8;

    public static void main(String[] args) {
        Present present = new Present();
        SweetReader.readPresent(present, filename);
        SweetSorter.sortBySugar(present);
        ConsolePrinter.printPresent(present);
        ConsolePrinter.printWeight(present.countWeight());
        ConsolePrinter.printSweet(SweetSearcher.searchBySugar(present, MIN_SUGAR, MAX_SUGAR));
    }
}
