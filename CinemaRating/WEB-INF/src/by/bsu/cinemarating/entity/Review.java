package by.bsu.cinemarating.entity;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 24.04.16
 * Time: 9:20
 * To change this template use File | Settings | File Templates.
 */
public class Review {
    private User user;
    private int mid;
    private String text;
    private Timestamp time;

    public Review(User user, int mid, String text, Timestamp time) {
        this.user = user;
        this.mid = mid;
        this.text = text;
        this.time = time;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
