package by.bsu.xml.parser;

import by.bsu.xml.builder.CandySAXBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 03.04.16
 * Time: 9:49
 * To change this template use File | Settings | File Templates.
 */
public class CandySAXParser {
    public static void main(String[ ] args) {
        CandySAXBuilder saxBuilder = new CandySAXBuilder();
        saxBuilder.buildSetCandies("resource/candies.xml");
        System.out.println(saxBuilder.getCandies());
    }
}
