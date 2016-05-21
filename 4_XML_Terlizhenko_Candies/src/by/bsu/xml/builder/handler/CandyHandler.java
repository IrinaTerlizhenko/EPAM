package by.bsu.xml.builder.handler;

import by.bsu.xml.entity.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 03.04.16
 * Time: 9:47
 * To change this template use File | Settings | File Templates.
 */
public class CandyHandler extends DefaultHandler {
    private Set<Candy> candies = new HashSet<>();
    private Candy candy;
    private CandyEnum tag = CandyEnum.CANDIES;

    public Set<Candy> getCandies() {
        return candies;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        tag = CandyEnum.valueOf(localName.toUpperCase());
        switch (tag) {
            case CANDY:
            case PRESENT_CANDY:
                candy = (CandyEnum.CANDY.equals(tag) ? new Candy() : new PresentCandy());
                for (int i = 0; i < attrs.getLength(); i++) {
                    switch (CandyEnum.valueOf(attrs.getLocalName(i).toUpperCase())) {
                        case ID:
                            candy.setId(attrs.getValue(i));
                            break;
                        case TYPE:
                            candy.setType(CandyType.valueOf(attrs.getValue(i).toUpperCase()));
                            break;
                    }
                }
                break;
            case PACKAGING:
                PresentCandy presentCandy = (PresentCandy) candy;
                presentCandy.getPackaging().setType(PackagingType.valueOf(attrs.getValue(0).toUpperCase())); //todo null
                break;
        }
    }
    @Override
    public void characters(char[ ] ch, int start, int length) {
        String info = new String(ch, start, length).trim();
        if (!info.isEmpty()) { //todo
            switch (tag) {
                case NAME:
                    candy.setName(info);
                    break;
                case ENERGY:
                    candy.setEnergy(Integer.parseInt(info));
                    break;
                case WATER:
                    candy.getIngredient().setWater(Integer.parseInt(info));
                    break;
                case SUGAR:
                    candy.getIngredient().setSugar(Integer.parseInt(info));
                    break;
                case FRUCTOSE:
                    candy.getIngredient().setFructose(Integer.parseInt(info));
                    break;
                case VANILLIN:
                    candy.getIngredient().setVanillin(Integer.parseInt(info));
                    break;
                case PROTEINS:
                    candy.getValue().setProteins(Integer.parseInt(info));
                    break;
                case FATS:
                    candy.getValue().setFats(Integer.parseInt(info));
                    break;
                case CARBOHYDRATES:
                    candy.getValue().setCarbohydrates(Integer.parseInt(info));
                    break;
                case PRODUCTION:
                    candy.setProduction(info);
                    break;
                case PACKAGING:
                    break;
                case HOLIDAY:
                    ((PresentCandy) candy).setHoliday(info);
            }
        }
    }
    @Override
    public void endElement(String uri, String localName, String qName) {
        if (CandyEnum.CANDY.value().equals(localName)) {
            candies.add(candy);
        }
    }
}
