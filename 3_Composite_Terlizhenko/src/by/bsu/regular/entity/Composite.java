package by.bsu.regular.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 12.03.16
 * Time: 6:30
 * To change this template use File | Settings | File Templates.
 */
public class Composite implements Component {
    static Logger log = LogManager.getLogger();

    private ArrayList<Component> components = new ArrayList<>();
    private int depth;
    private Type type;

    public Composite(Type type) {
        this.type = type;
    }

    public Composite(Type type, int depth) {
        this.type = type;
        this.depth = depth;
    }

    @Override
    public void add(Component c) {
        components.add(c);
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("\t");
        }
        switch (type) {
            case OPERATOR:
            case FIELD:
                appendComponents(sb);
                sb.append("\n");
                break;
            case METHOD:
                appendComponents(sb);
                if (sb.indexOf("{") == -1) {
                    sb.append("\n");
                }
                break;
            case BLOCK:
            case CLASS:
            case FILE:
                appendComponents(sb);
                break;
            default:
                log.error("Unknown type");
                break;
        }
        return sb.toString();
    }

    private void appendComponents(StringBuilder sb) {
        for (Component c : components) {
            sb.append(c);
        }
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public Type getType() {
        return type;
    }

    @Override
    public void remove(Component c) {
        components.remove(c);
    }
}
