package by.bsu.cinemarating.dao;

import by.bsu.cinemarating.database.WrapperConnection;
import by.bsu.cinemarating.entity.Movie;
import by.bsu.cinemarating.entity.Review;
import by.bsu.cinemarating.entity.User;
import by.bsu.cinemarating.exception.DAOException;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Created by User on 06.06.2016.
 */
public class ReviewDAO extends AbstractRatingDAO<Review> {
    private static final String SELECT_BY_ID =
            "SELECT review,time,login,email,reg_date,role_id,name,surname,status,photo,num_rated FROM reviews,users WHERE mid=? AND uid=? AND user_id=uid";
    private static final String SELECT_TEXT_BY_ID = "SELECT review FROM reviews WHERE mid=? AND uid=?";
    private static final String SELECT_REVIEWS_BY_MID = "SELECT uid,review,time FROM reviews WHERE mid=? ORDER BY time DESC";
    private static final String SELECT_USER_REVIEWS_WITH_MOVIES =
            "SELECT review,time,movie_id,name,description,year,country,movies.rating AS movie_rating,ref FROM reviews,movies WHERE uid=? AND mid=movie_id ORDER BY time DESC";

    private static final String INSERT_REVIEW = "INSERT INTO reviews(mid,uid,review) VALUES(?,?,?)";

    private static final String REPLACE_REVIEW = "REPLACE INTO reviews(mid,uid,review,time) VALUES(?,?,?,?)";

    private static final String DELETE_BY_ID = "DELETE FROM reviews WHERE mid=? AND uid=?";

    public ReviewDAO(WrapperConnection connection) {
        super(connection);
    }

    @Override
    public boolean delete(int movieId, int userId) throws DAOException {
        int rows;
        try (PreparedStatement st = connection.prepareStatement(DELETE_BY_ID)) {
            st.setInt(1, movieId);
            st.setInt(2, userId);
            rows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return rows > 0;
    }

    @Override
    public boolean create(Review entity) throws DAOException {
        /*int rows;
        try (PreparedStatement st = connection.prepareStatement(INSERT_REVIEW)) {
            st.setInt(1, entity.getMid());
            st.setInt(2, entity.getUser().getId());
            st.setString(3, entity.getText());
            rows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return rows > 0;*/
        return create(entity.getMid(), entity.getUser().getId(), entity.getText());
    }

    public boolean create(int movieId, int userId, String text) throws DAOException {
        int rows;
        try (PreparedStatement st = connection.prepareStatement(INSERT_REVIEW)) {
            st.setInt(1, movieId);
            st.setInt(2, userId);
            st.setString(3, text);
            rows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return rows > 0;
    }

    @Override
    public Optional<Review> findEntityById(int movieId, int userId) throws DAOException { //todo
        Review review = null;
        try (PreparedStatement st = connection.prepareStatement(SELECT_BY_ID)) {
            st.setInt(1, movieId);
            st.setInt(2, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String text = rs.getString(REVIEW);
                Timestamp time = rs.getTimestamp(TIME);
                String login = rs.getString(LOGIN);
                String email = rs.getString(EMAIL);
                Date reg_date = rs.getDate(REG_DATE);
                byte role_id = rs.getByte(ROLE_ID);
                String name = rs.getString(NAME);
                String surname = rs.getString(SURNAME);
                double status = rs.getDouble(STATUS);
                String photo = rs.getString(PHOTO);
                int numRated = rs.getInt(NUM_RATED);
                User user = new User(userId, login, email, reg_date, role_id, name, surname, status, photo, numRated);
                review = new Review(user, movieId, text, time);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(review);
    }

    @Override
    public boolean replace(Review entity) throws DAOException {
        /*int rows;
        try (PreparedStatement st = connection.prepareStatement(REPLACE_REVIEW)) {
            st.setInt(1, entity.getMid());
            st.setInt(2, entity.getUser().getId());
            st.setString(3, entity.getText());
            rows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return rows > 0;*/
        return replace(entity.getMid(), entity.getUser().getId(), entity.getText());
    }

    public boolean replace(int movieId, int userId, String text) throws DAOException {
        boolean result;
        Optional<Review> oldReview = findEntityById(movieId, userId);
        if (oldReview.isPresent()) {
            try (PreparedStatement st = connection.prepareStatement(REPLACE_REVIEW)) {
                st.setInt(1, movieId);
                st.setInt(2, userId);
                st.setString(3, text);
                st.setTimestamp(4, oldReview.get().getTime());
                result = st.executeUpdate() > 0;
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } else {
            /*try (PreparedStatement st = connection.prepareStatement(REPLACE_REVIEW)) {
                st.setInt(1, movieId);
                st.setInt(2, userId);
                st.setString(3, text);
                rows = st.executeUpdate();
            } catch (SQLException e) {
                throw new DAOException(e);
            }*/
            result = create(movieId, userId, text);
        }
        return result;
    }

    public Optional<String> takeText(int movieId, int userId) throws DAOException {
        String text = null;
        try (PreparedStatement st = connection.prepareStatement(SELECT_TEXT_BY_ID)) {
            st.setInt(1, movieId);
            st.setInt(2, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                text = rs.getString(REVIEW);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(text);
    }

    public List<Review> findReviews(int movieId, int userIdFirst) throws DAOException {
        List<Review> reviews = new LinkedList<>();
        try (PreparedStatement st = connection.prepareStatement(SELECT_REVIEWS_BY_MID)) {
            st.setInt(1, movieId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt(UID);
                String text = rs.getString(REVIEW);
                Timestamp ts = rs.getTimestamp(TIME);
                UserDAO udao = new UserDAO(connection);
                User user = udao.findEntityById(userId).get();
                Review review = new Review(user, movieId, text, ts);
                if (userIdFirst != userId) {
                    reviews.add(review);
                } else {
                    ((LinkedList<Review>) reviews).addFirst(review);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return reviews;
    }

    public boolean existsReview(int movieId, int userId) throws DAOException {
        Optional<Review> optReview = findEntityById(movieId, userId);
        return optReview.isPresent();
    }

    public LinkedHashMap<Movie, Review> takeUserReviews(User user) throws DAOException {
        LinkedHashMap<Movie, Review> reviewMap = new LinkedHashMap<>();
        try (PreparedStatement st = connection.prepareStatement(SELECT_USER_REVIEWS_WITH_MOVIES)) {
            st.setInt(1, user.getId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String text = rs.getString(REVIEW);
                Timestamp time = rs.getTimestamp(TIME);
                int movieId = rs.getInt(MOVIE_ID);
                String name = rs.getString(NAME);
                String description = rs.getString(DESCRIPTION);
                int year = rs.getInt(YEAR);
                String country = rs.getString(COUNTRY);
                double movieRating = rs.getDouble(MOVIE_RATING);
                String ref = rs.getString(REF);
                Movie movie = new Movie(movieId, name, description, year, country, movieRating, ref);
                Review review = new Review(user, movieId, text, time);
                reviewMap.put(movie, review);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return reviewMap;
    }
}
