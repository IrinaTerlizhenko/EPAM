package by.bsu.xml.parser;

import by.bsu.xml.builder.CandyStAXBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 03.04.16
 * Time: 10:38
 * To change this template use File | Settings | File Templates.
 */
public class CandyStAXParser {
    public static void main(String[] args) {
        CandyStAXBuilder staxBuilder = new CandyStAXBuilder();
        staxBuilder.buildSetCandies("resource/candies.xml");
        System.out.println(staxBuilder.getCandies());
    }
}
