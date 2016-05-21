package by.bsu.nypresent.action;

import by.bsu.nypresent.collection.Present;
import by.bsu.nypresent.entity.Sweet;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 26.02.16
 * Time: 15:56
 * To change this template use File | Settings | File Templates.
 */
public class SweetSorter {
    public static void sortBySugar(Present present) {
        List<Sweet> sweets = present.getSweets();
        Collections.sort(sweets, new Comparator<Sweet>() {
            @Override
            public int compare(Sweet o1, Sweet o2) {
                if (o1.getSugar() < o2.getSugar()) {
                    return -1;
                } else if (o1.getSugar() > o2.getSugar()) {
                    return 1;
                }
                return 0;
            }
        });
        present.setSweets(sweets);
    }
}
