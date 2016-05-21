package by.bsu.regular.action;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 14.03.16
 * Time: 4:35
 * To change this template use File | Settings | File Templates.
 */
public class OperatorSorter {
    private static final String SPLIT_REGEXP = "\\s+";

    public static void sort(ArrayList<String> operators) {
        operators.sort((o1, o2) -> o1.split(SPLIT_REGEXP).length - o2.split(SPLIT_REGEXP).length);
    }
}
