package by.bsu.regular.parser;

import by.bsu.regular.entity.Composite;
import by.bsu.regular.entity.Keyword;
import by.bsu.regular.entity.Type;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 12.03.16
 * Time: 3:42
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractParser {
    protected static final ClassParser successorClass = new ClassParser(Type.CLASS);
    protected static final BlockParser successorBlock = new BlockParser(Type.BLOCK);
    protected static final FieldParser successorField = new FieldParser(Type.FIELD);
    protected static final OperatorParser successorOperator = new OperatorParser(Type.OPERATOR);

    private static final String IDENTIFICATOR = "[A-z_]\\w*\\s*";
    private static final String PARAMETRIZATION = "(?:<\\s*(?:" + IDENTIFICATOR + ",\\s*)*" + IDENTIFICATOR + ">)?";
    private static final String TYPE = IDENTIFICATOR + PARAMETRIZATION;
    private static final String PARAMETER = TYPE + "\\s+" + IDENTIFICATOR;
    private static final String ANNOTATION = "@" + IDENTIFICATOR + "(?:\\(.*\\))?\\s*";

    public static final String PACKAGE_IMPORT_REGEXP = "(?:package|import)\\s+(?:" + IDENTIFICATOR + ".)*" + IDENTIFICATOR + "\\s*;";
    public static final String CLASS_REGEXP =
            "(\\p{Blank}*)(?:" + ANNOTATION + "\\s*)*(?:(?:" +
                    String.join("|", Keyword.PUBLIC.value(),
                            Keyword.ABSTRACT.value(),
                            Keyword.FINAL.value(),
                            Keyword.STATIC.value(),
                            Keyword.STRICTFP.value()) +
                    ")\\s+){0,3}" +
                    "(?:class|@?interface|enum)\\s+" + TYPE +
                    "\\s*(?:extends\\s+(?:\\s*" + TYPE + "\\s*,)*(?:\\s*" + TYPE + "\\s*))?" +
                    "(?:implements\\s+(?:\\s*" + TYPE + "\\s*,)*(?:\\s*" + TYPE + "\\s*))?\\{" +
                    "(?:.|\\s)*?" +
                    "\\n\\1\\}";
    public static final String METHOD_REGEXP =
            "(\\p{Blank}*)(?:" + ANNOTATION + "\\s*)*(?:(?:" +
                    String.join("|", Keyword.PUBLIC.value(),
                            Keyword.PROTECTED.value(),
                            Keyword.PRIVATE.value(),
                            Keyword.ABSTRACT.value(),
                            Keyword.STATIC.value(),
                            Keyword.FINAL.value(),
                            Keyword.SYNCHRONIZED.value(),
                            Keyword.NATIVE.value(),
                            Keyword.STRICTFP.value(),
                            Keyword.DEFAULT.value()) +
                    ")\\s+){0,5}" +
                    "(?:" + PARAMETER + "|" + IDENTIFICATOR + ")" + // return type & name or name if constructor
                    "\\(\\s*" + // parameters begin
                    "(?:" + // may be 0 or not 0 parameters
                    "(?:" + // may be more than 1 parameter
                    PARAMETER + ",\\s*)*" + PARAMETER + ")?\\)" + //parameters end
                    "\\s*(?:(?:\\{" +
                    "(?:.|\\s)*?" +
                    "\\n\\1\\})|;)";
    public static final String BLOCK_REGEXP = "(\\p{Blank}*)(?:" + Keyword.STATIC.value() + "\\s+)?\\{(?:.|\\s)*?\\n\\1\\}";
    public static final String FIELD_REGEXP =
            "(?:(?:" +
                    String.join("|", Keyword.PUBLIC.value(),
                            Keyword.PROTECTED.value(),
                            Keyword.PRIVATE.value(),
                            Keyword.STATIC.value(),
                            Keyword.FINAL.value(),
                            Keyword.VOLATILE.value(),
                            Keyword.TRANSIENT.value()) +
                    ")\\s+){0,4}" + PARAMETER + "(?:=\\s*(?:\".*\"|[^;]*)\\s*)?|" + IDENTIFICATOR + "[,;]";
    public static final String OPERATOR_REGEXP = "[^;\\{\\}]*;";
    public static final String LEXEME_REGEXP = "\\S+";

    private static final String BLANK = "    ";

    protected Composite element;
    private Type type;

    public AbstractParser(Type type) {
        this.type = type;
    }

    abstract public void parse(String text);

    public String parse(String text, String regex, AbstractParser successor) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String group = matcher.group();
            if (CLASS_REGEXP.equals(regex) || METHOD_REGEXP.equals(regex)) {
                String blank = matcher.group(1);
                String blankNeeded = "";
                for (int i = 0; i <= element.getDepth(); i++) {
                    blankNeeded += BLANK;
                }
                if (blankNeeded.equals(blank)) {
                    text = text.replace(group, "");
                    Composite c = successor.chain(group, element.getDepth() + 1);
                    element.add(c);
                }
            } else {
                text = text.replace(group, "");
                Composite c = successor.chain(group, element.getDepth() + 1);
                element.add(c);
            }
        }
        return text;
    }

    public Composite chain(String text, int depth) {
        element = new Composite(type, depth);
        parse(text);
        return element;
    }

    public Composite chain(String text) {
        return chain(text, 0);
    }
}
