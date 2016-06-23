package by.bsu.cinemarating.logic;

import by.bsu.cinemarating.dao.ReviewDAO;
import by.bsu.cinemarating.dao.UserDAO;
import by.bsu.cinemarating.database.ConnectionPool;
import by.bsu.cinemarating.database.WrapperConnection;
import by.bsu.cinemarating.entity.Movie;
import by.bsu.cinemarating.entity.Review;
import by.bsu.cinemarating.entity.User;
import by.bsu.cinemarating.exception.DAOException;
import by.bsu.cinemarating.exception.LogicException;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Optional;

/**
 * Created by User on 06.06.2016.
 * A service layer class implementing all the logic concerning reviews.
 */
public class ReviewLogic {
    /**
     * Retrieves a review from a specified user to a specified movie.
     *
     * @param movieId movie id
     * @param userId  user id
     * @return review from a user with a specified id to a movie with a specified id wrapped in Optional if exists, Optional.empty() otherwise
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static Optional<String> takeReview(int movieId, int userId) throws LogicException {
        Optional<String> review = Optional.empty();
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            ReviewDAO reviewDAO = new ReviewDAO(connection);
            review = reviewDAO.takeText(movieId, userId);
        } catch (SQLException | DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return review;
    }

    /**
     * Deletes the review from a specified user to a specified movie.
     *
     * @param movieId movie id
     * @param userId  user id
     * @return true if the review was deleted, false otherwise
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static boolean deleteReview(int movieId, int userId) throws LogicException {
        boolean result = false;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            ReviewDAO reviewDAO = new ReviewDAO(connection);
            result = reviewDAO.delete(movieId, userId);
        } catch (SQLException | DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return result;
    }

    public static boolean replaceReview(int movieId, int userId, String text) throws LogicException {
        boolean result = false;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            ReviewDAO reviewDAO = new ReviewDAO(connection);
            result = reviewDAO.replace(movieId, userId, text);
        } catch (SQLException | DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return result;
    }

    public static boolean isReviewed(int movieId, int userId) throws LogicException {
        boolean reviewed = false;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            ReviewDAO reviewDAO = new ReviewDAO(connection);
            reviewed = reviewDAO.existsReview(movieId, userId);
        } catch (SQLException | DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return reviewed;
    }

    public static LinkedHashMap<Movie, Review> takeUserReviews(int userId) throws LogicException {
        LinkedHashMap<Movie, Review> reviewMap;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            ReviewDAO reviewDAO = new ReviewDAO(connection);
            UserDAO userDAO = new UserDAO(connection);
            User user = userDAO.findEntityById(userId).get();
            reviewMap = reviewDAO.takeUserReviews(user);
        } catch (SQLException | DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return reviewMap;
    }
}
