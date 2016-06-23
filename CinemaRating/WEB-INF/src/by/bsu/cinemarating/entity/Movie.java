package by.bsu.cinemarating.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 14.04.16
 * Time: 2:08
 * To change this template use File | Settings | File Templates.
 */
public class Movie extends Entity {
    private String name;
    private String description;
    private int year;
    private String country;
    private double rating;
    private String ref;

    public Movie(int id, String name, String description, int year, String country, double rating, String ref) {
        super(id);
        this.name = name;
        this.description = description;
        this.year = year;
        this.country = country;
        this.rating = rating;
        this.ref = ref;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
