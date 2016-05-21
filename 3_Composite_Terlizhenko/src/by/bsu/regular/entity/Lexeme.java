package by.bsu.regular.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 12.03.16
 * Time: 3:29
 * To change this template use File | Settings | File Templates.
 */
public class Lexeme implements Component {
    private static final String BRACKETS = "{}";

    private String content;
    private int depth;

    public Lexeme(String content) {
        this.content = content;
    }

    public Lexeme(String content, int depth) {
        this.content = content;
        this.depth = depth;
    }

    @Override
    public void add(Component c) {
    }

    @Override
    public void remove(Component c) {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if ("}".equals(content)) {
            for (int i = 0; i < depth; i++) {
                sb.append("\t");
            }
        }
        sb.append(content).append(hasBracket() ? "\n" : " ");
        return sb.toString();
    }

    private boolean hasBracket() {
        char lastSymbol = content.charAt(content.length() - 1);
        return BRACKETS.indexOf(lastSymbol) != -1;
    }
}
