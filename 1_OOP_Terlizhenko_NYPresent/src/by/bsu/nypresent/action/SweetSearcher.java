package by.bsu.nypresent.action;

import by.bsu.nypresent.collection.Present;
import by.bsu.nypresent.entity.Sweet;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 26.02.16
 * Time: 16:05
 * To change this template use File | Settings | File Templates.
 */
public class SweetSearcher {
    public static Sweet searchBySugar(Present present, double lower, double upper) {
        List<Sweet> sweets = present.getSweets();
        for (Sweet sweet : sweets) {
            double sugar = sweet.getSugar();
            if (sugar >= lower && sugar <= upper) {
                return sweet;
            }
        }
        return null;
    }
}
