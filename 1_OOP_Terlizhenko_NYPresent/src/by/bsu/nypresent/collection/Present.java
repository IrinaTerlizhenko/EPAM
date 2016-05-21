package by.bsu.nypresent.collection;

import by.bsu.nypresent.entity.Sweet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 26.02.16
 * Time: 15:45
 * To change this template use File | Settings | File Templates.
 */
public class Present {
    private List<Sweet> sweets;

    public Present() {
        sweets = new ArrayList<Sweet>();
    }

    public Present(List<Sweet> sweets) {
        this.sweets = sweets;
    }

    public void add(Sweet sweet) {
        sweets.add(sweet);
    }

    public List<Sweet> getSweets() {
        return new ArrayList<>(sweets);
    }

    public void setSweets(List<Sweet> sweets) {
        this.sweets = new ArrayList<>(sweets);
    }

    public double countWeight() {
        double weight = 0;
        for (Sweet sweet : sweets) {
            weight += sweet.getWeight();
        }
        return weight;
    }

    @Override
    public String toString() {
        StringBuilder stringPresent = new StringBuilder();
        for (Sweet sweet : sweets) {
            stringPresent.append(sweet).append("\n");
        }
        return stringPresent.toString();
    }
}
