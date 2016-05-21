package by.bsu.xml.builder;

import by.bsu.xml.entity.Candy;
import by.bsu.xml.entity.CandyType;
import by.bsu.xml.entity.PackagingType;
import by.bsu.xml.entity.PresentCandy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 03.04.16
 * Time: 9:53
 * To change this template use File | Settings | File Templates.
 */
public class CandyDOMBuilder extends AbstractCandyBuilder {
    //private Set<Candy> candies;
    private DocumentBuilder docBuilder;
    public CandyDOMBuilder() {
        this.candies = new HashSet<>();
// создание DOM-анализатора
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Ошибка конфигурации парсера: " + e);
        }
    }
    public Set<Candy> getCandies() {
        return candies;
    }
    public void buildSetCandies(String fileName) {
        Document doc = null;
        try {
// parsing XML-документа и создание древовидной структуры
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
// получение списка дочерних элементов <candy>, <present-candy>
            NodeList candiesList = root.getElementsByTagName("candy");
            for (int i = 0; i < candiesList.getLength(); i++) {
                Element candyElement = (Element) candiesList.item(i);
                Candy candy = new Candy();
                buildCandy(candy, candyElement);
                candies.add(candy);
            }
            candiesList = root.getElementsByTagName("present_candy");
            for (int i = 0; i < candiesList.getLength(); i++) {
                Element presentCandyElement = (Element) candiesList.item(i);
                PresentCandy presentCandy = new PresentCandy();
                buildPresentCandy(presentCandy, presentCandyElement);
                candies.add(presentCandy);
            }
        } catch (IOException e) {
            System.err.println("File error or I/O error: " + e);
        } catch (SAXException e) {
            System.err.println("Parsing failure: " + e);
        }
    }
    private void buildCandy(Candy candy, Element candyElement) {
        candy.setId(candyElement.getAttribute("id"));
        candy.setType(CandyType.valueOf(candyElement.getAttribute("type").toUpperCase())); // TODO default attribubes
        candy.setName(getElementTextContent(candyElement, "name"));
        candy.setEnergy(Integer.parseInt(getElementTextContent(candyElement, "energy")));
        Candy.Ingredient ingredient = candy.getIngredient();
        Element ingredientElement = (Element) candyElement.getElementsByTagName("ingredient").item(0);
        ingredient.setWater(Integer.parseInt(getElementTextContent(ingredientElement, "water")));
        ingredient.setSugar(Integer.parseInt(getElementTextContent(ingredientElement, "sugar")));
        ingredient.setFructose(Integer.parseInt(getElementTextContent(ingredientElement, "fructose")));
        ingredient.setVanillin(Integer.parseInt(getElementTextContent(ingredientElement, "vanillin")));
        Candy.Value value = candy.getValue();
        Element valueElement = (Element) candyElement.getElementsByTagName("value").item(0);
        value.setProteins(Integer.parseInt(getElementTextContent(valueElement, "proteins")));
        value.setFats(Integer.parseInt(getElementTextContent(valueElement, "fats")));
        value.setCarbohydrates(Integer.parseInt(getElementTextContent(valueElement, "carbohydrates")));
        candy.setProduction(getElementTextContent(candyElement, "production"));
    }
    private void buildPresentCandy(PresentCandy presentCandy, Element presentCandyElement) {
        buildCandy(presentCandy, presentCandyElement);
        PresentCandy.Packaging packaging = presentCandy.getPackaging();
        Element packagingElement = (Element) presentCandyElement.getElementsByTagName("packaging").item(0);
        packaging.setType(PackagingType.valueOf(packagingElement.getAttribute("type").toUpperCase())); // TODO default attributes
        if (packagingElement.getElementsByTagName("color").getLength() > 0) {
            packaging.setColor(getElementTextContent(packagingElement, "color"));
        } else {
            packaging.setPicture(getElementTextContent(packagingElement, "picture"));
        }

        presentCandy.setHoliday(getElementTextContent(presentCandyElement, "holiday"));
    }
    // получение текстового содержимого тега
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
