package by.bsu.xml.builder;

import by.bsu.xml.entity.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 03.04.16
 * Time: 10:39
 * To change this template use File | Settings | File Templates.
 */
public class CandyStAXBuilder extends AbstractCandyBuilder {
    //private HashSet<Candy> candies = new HashSet<>();
    private XMLInputFactory inputFactory;

    public CandyStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public Set<Candy> getCandies() {
        return candies;
    }

    public void buildSetCandies(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
// StAX parsing
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    switch (CandyEnum.valueOf(name.toUpperCase())) {
                        case CANDY:
                            Candy candy = buildCandy(reader);
                            candies.add(candy);
                            break;
                        case PRESENT_CANDY:
                            PresentCandy presentCandy = buildPresentCandy(reader);
                            candies.add(presentCandy);
                            break;
                    }
                }
            }
        } catch (XMLStreamException ex) {
            System.err.println("StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.err.println("File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Impossible close file " + fileName + " : " + e);
            }
        }
    }

    private Candy buildCandy(XMLStreamReader reader) throws XMLStreamException {
        Candy candy = new Candy();
        candy.setId(reader.getAttributeValue(null, CandyEnum.ID.value()));
        candy.setType(CandyType.valueOf(reader.getAttributeValue(null, CandyEnum.TYPE.value()).toUpperCase())); // TODO проверить на null
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (CandyEnum.valueOf(name.toUpperCase())) {
                        case NAME:
                            candy.setName(getXMLText(reader));
                            break;
                        case ENERGY:
                            candy.setEnergy(Integer.parseInt(getXMLText(reader)));
                            break;
                        case INGREDIENT:
                            setXMLIngredient(reader, candy.getIngredient());
                            break;
                        case VALUE:
                            setXMLValue(reader, candy.getValue());
                            break;
                        case PRODUCTION:
                            candy.setProduction(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (CandyEnum.valueOf(name.toUpperCase()) == CandyEnum.CANDY) {
                        return candy;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Candy");
    }

    private PresentCandy buildPresentCandy(XMLStreamReader reader) throws XMLStreamException {
        PresentCandy candy = new PresentCandy();
        candy.setId(reader.getAttributeValue(null, CandyEnum.ID.value()));
        candy.setType(CandyType.valueOf(reader.getAttributeValue(null, CandyEnum.TYPE.value()).toUpperCase())); // TODO проверить на null
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (CandyEnum.valueOf(name.toUpperCase())) {
                        case NAME:
                            candy.setName(getXMLText(reader));
                            break;
                        case ENERGY:
                            candy.setEnergy(Integer.parseInt(getXMLText(reader)));
                            break;
                        case INGREDIENT:
                            setXMLIngredient(reader, candy.getIngredient());
                            break;
                        case VALUE:
                            setXMLValue(reader, candy.getValue());
                            break;
                        case PRODUCTION:
                            candy.setProduction(getXMLText(reader));
                            break;
                        case PACKAGING:
                            setXMLPackaging(reader, candy.getPackaging());
                            break;
                        case HOLIDAY:
                            candy.setHoliday(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (CandyEnum.valueOf(name.toUpperCase()) == CandyEnum.PRESENT_CANDY) {
                        return candy;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Present_candy");
    }

    private void setXMLIngredient(XMLStreamReader reader, Candy.Ingredient ingredient) throws XMLStreamException {
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (CandyEnum.valueOf(name.toUpperCase())) {
                        case WATER:
                            ingredient.setWater(Integer.parseInt(getXMLText(reader)));
                            break;
                        case SUGAR:
                            ingredient.setSugar(Integer.parseInt(getXMLText(reader)));
                            break;
                        case FRUCTOSE:
                            ingredient.setFructose(Integer.parseInt(getXMLText(reader)));
                            break;
                        case VANILLIN:
                            ingredient.setVanillin(Integer.parseInt(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (CandyEnum.valueOf(name.toUpperCase()) == CandyEnum.INGREDIENT){
                        return;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Ingredient");
    }

    private void setXMLValue(XMLStreamReader reader, Candy.Value value) throws XMLStreamException {
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (CandyEnum.valueOf(name.toUpperCase())) {
                        case PROTEINS:
                            value.setProteins(Integer.parseInt(getXMLText(reader)));
                            break;
                        case FATS:
                            value.setFats(Integer.parseInt(getXMLText(reader)));
                            break;
                        case CARBOHYDRATES:
                            value.setCarbohydrates(Integer.parseInt(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (CandyEnum.valueOf(name.toUpperCase()) == CandyEnum.VALUE){
                        return;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Value");
    }

    private void setXMLPackaging(XMLStreamReader reader, PresentCandy.Packaging packaging) throws XMLStreamException {
        packaging.setType(PackagingType.valueOf(reader.getAttributeValue(null, CandyEnum.TYPE.value()).toUpperCase())); // TODO проверить на null
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (CandyEnum.valueOf(name.toUpperCase())) {
                        case COLOR:
                            packaging.setColor(getXMLText(reader));
                            break;
                        case PICTURE:
                            packaging.setPicture(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (CandyEnum.valueOf(name.toUpperCase()) == CandyEnum.PACKAGING){
                        return;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Packaging");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
