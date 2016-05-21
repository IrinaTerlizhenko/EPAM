package by.bsu.xml.parser;

import by.bsu.xml.builder.AbstractCandyBuilder;
import by.bsu.xml.factory.CandyBuilderFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 04.04.16
 * Time: 8:48
 * To change this template use File | Settings | File Templates.
 */
public class CandyParser {
    public static void main(String[] args) {
        CandyBuilderFactory sFactory = new CandyBuilderFactory();
        AbstractCandyBuilder builder = sFactory.createCandyBuilder("stax");
        builder.buildSetCandies("resource/candies.xml");
        System.out.println(builder.getCandies());
    }
}
