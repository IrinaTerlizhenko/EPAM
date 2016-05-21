package by.bsu.xml.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 03.04.16
 * Time: 10:44
 * To change this template use File | Settings | File Templates.
 */
public enum CandyEnum {
    CANDIES,
    CANDY,
    PRESENT_CANDY,
    ID,
    TYPE,
    NAME,
    ENERGY,
    INGREDIENT,
    VALUE,
    PRODUCTION,
    WATER,
    SUGAR,
    FRUCTOSE,
    VANILLIN,
    PROTEINS,
    FATS,
    CARBOHYDRATES,
    PACKAGING,
    HOLIDAY,
    COLOR,
    PICTURE;

    public String value() {
        return name().toLowerCase();
    }
}
