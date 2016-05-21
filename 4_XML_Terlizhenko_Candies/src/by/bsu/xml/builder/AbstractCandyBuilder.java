package by.bsu.xml.builder;

import by.bsu.xml.entity.Candy;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 04.04.16
 * Time: 8:32
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractCandyBuilder {
    // protected так как к нему часто обращаются из подкласса
    protected Set<Candy> candies;
    public AbstractCandyBuilder() {
        candies = new HashSet<>();
    }
    public AbstractCandyBuilder(Set<Candy> candies) {
        this.candies = candies;
    }
    public Set<Candy> getCandies() {
        return candies;
    }
    abstract public void buildSetCandies(String fileName);
}
