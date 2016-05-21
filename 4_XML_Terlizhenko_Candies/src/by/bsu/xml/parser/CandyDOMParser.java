package by.bsu.xml.parser;

import by.bsu.xml.builder.CandyDOMBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 03.04.16
 * Time: 9:53
 * To change this template use File | Settings | File Templates.
 */
public class CandyDOMParser {
    public static void main(String[] args) {
        CandyDOMBuilder domBuilder = new CandyDOMBuilder();
        domBuilder.buildSetCandies("resource/candies.xml");
        System.out.println(domBuilder.getCandies());
    }
}
