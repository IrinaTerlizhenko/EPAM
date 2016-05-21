package by.bsu.nypresent.report;

import by.bsu.nypresent.collection.Present;
import by.bsu.nypresent.entity.Sweet;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 26.02.16
 * Time: 16:33
 * To change this template use File | Settings | File Templates.
 */
public class ConsolePrinter {
    public static void printPresent(Present present) {
        System.out.println("Present:\n" + present);
    }

    public static void printSweet(Sweet sweet) {
        System.out.println("Sweet:\n" + sweet);
    }

    public static void printWeight(double weight) {
        System.out.println("Weight: " + weight);
    }
}
