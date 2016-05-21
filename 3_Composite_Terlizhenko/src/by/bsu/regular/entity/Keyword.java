package by.bsu.regular.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 29.03.16
 * Time: 3:05
 * To change this template use File | Settings | File Templates.
 */
public enum Keyword {
    PUBLIC,
    PROTECTED,
    PRIVATE,
    STATIC,
    FINAL,
    ABSTRACT,
    SYNCHRONIZED,
    DEFAULT,
    STRICTFP,
    NATIVE,
    VOLATILE,
    TRANSIENT;

    public String value() {
        return name().toLowerCase();
    }
}
