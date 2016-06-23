package by.bsu.cinemarating.entity;

/**
 * Created by User on 01.06.2016.
 */
public class Rating {
    private int movieId;
    private int userId;
    private byte rating;

    public Rating(int movieId, int userId, byte rating) {
        this.movieId = movieId;
        this.userId = userId;
        this.rating = rating;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }
}
