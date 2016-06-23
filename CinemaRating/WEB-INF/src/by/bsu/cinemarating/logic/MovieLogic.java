package by.bsu.cinemarating.logic;

import by.bsu.cinemarating.dao.MovieDAO;
import by.bsu.cinemarating.dao.ReviewDAO;
import by.bsu.cinemarating.database.ConnectionPool;
import by.bsu.cinemarating.database.WrapperConnection;
import by.bsu.cinemarating.entity.Movie;
import by.bsu.cinemarating.entity.Review;
import by.bsu.cinemarating.exception.DAOException;
import by.bsu.cinemarating.exception.LogicException;
import by.bsu.cinemarating.loader.PictureLoader;
import by.bsu.cinemarating.validation.ValidationResult;
import by.bsu.cinemarating.validation.Validator;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 22.04.16
 * Time: 5:05
 * A service layer class implementing all the logic concerning movies.
 */
public class MovieLogic {
    /**
     * Folder where all movie pictures are saved.
     */
    private static final String PICTURE_FOLDER = "img" + File.separator + "movie";
    private static final String DEFAULT_PICTURE = "default.png";

    /**
     * Retrieves all the movies in the system.
     *
     * @return List of all movies added to the system.
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static List<Movie> takeAllMovies() throws LogicException {
        List<Movie> list;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            MovieDAO movieDAO = new MovieDAO(connection);
            list = movieDAO.findAll();
        } catch (SQLException | DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return list;
    }

    /**
     * Retrives a list of movies with maximum ratings. The size of the list is bounded up to size parameter.
     *
     * @param size maximum size of the return list
     * @return List of movies with maximum ratings
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static List<Movie> takeTopMovies(int size) throws LogicException {
        List<Movie> list;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            MovieDAO movieDAO = new MovieDAO(connection);
            list = movieDAO.findTop(size);
        } catch (SQLException | DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return list;
    }

    /**
     * Retrives a list of latest added movies. The size of the list is bounded up to size parameter.
     *
     * @param size maximum size of the return list
     * @return List of latest added movies
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static List<Movie> takeLatestAddedMovies(int size) throws LogicException {
        List<Movie> list;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            MovieDAO movieDAO = new MovieDAO(connection);
            list = movieDAO.findLatestAdded(size);
        } catch (SQLException | DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return list;
    }

    /**
     * Retrieves a movie with a specified id.
     *
     * @param movieId id of the movie
     * @return Movie with a specified id
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static Movie takeMovie(int movieId) throws LogicException {
        Movie movie;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            MovieDAO movieDAO = new MovieDAO(connection);
            Optional<Movie> optMovie = movieDAO.findEntityById(movieId);
            movie = optMovie.orElseThrow(LogicException::new);
        } catch (SQLException | DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return movie;
    }

    /**
     * Retrives a list of all movie reviews. If a review with specified movieId and userId exists, it comes first.
     *
     * @param movieId id of the movie
     * @param userId  id of the user
     * @return List of reviews on a movie with a specified id
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static List<Review> takeMovieReviews(int movieId, int userId) throws LogicException {
        List<Review> reviews;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            ReviewDAO reviewDAO = new ReviewDAO(connection);
            reviews = reviewDAO.findReviews(movieId, userId);
        } catch (SQLException | DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return reviews;
    }

    /**
     * Adds a movie to the system.
     *
     * @param name        movie name
     * @param description movie description
     * @param year        year of movie premiere
     * @param country     country(-ies) of movie production
     * @param filePart    movie picture
     * @param path        path to the server root folder
     * @return ValidationResult.ALL_RIGHT if the movie was added, ValidationResult with a specific cause if not valid,
     * ValidationResult.UNKNOWN_ERROR otherwise
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static ValidationResult addMovie(String name, String description, int year, String country, Part filePart,
                                            String path) throws LogicException {
        ValidationResult result = ValidationResult.UNKNOWN_ERROR;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            if (!Validator.validateMovieName(name)) {
                result = ValidationResult.NAME_INCORRECT;
            } else if (!Validator.validateMovieDescription(description)) {
                result = ValidationResult.DESCRIPTION_INCORRECT;
            } else if (!Validator.validateYear(year)) {
                result = ValidationResult.YEAR_INCORRECT;
            } else if (!Validator.validateCountry(country)) {
                result = ValidationResult.COUNTRY_INCORRECT;
            } else {
                optConnection = ConnectionPool.getInstance().takeConnection();
                WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
                MovieDAO movieDAO = new MovieDAO(connection);
                Movie movie = new Movie(0, name, description, year, country, 0.0, PICTURE_FOLDER + File.separator + DEFAULT_PICTURE);
                movieDAO.create(movie);
                String filename = PictureLoader.loadPicture(filePart, movie.getId(), path, PICTURE_FOLDER, DEFAULT_PICTURE);
                if (filename != null) {
                    movie.setRef(filename);
                    movieDAO.update(movie); // todo update ref
                }
                result = ValidationResult.ALL_RIGHT;
            }
        } catch (IOException | SQLException | DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return result;
    }

    /**
     * Edits an existing movie.
     *
     * @param movieId     movie id
     * @param name        movie name
     * @param description movie description
     * @param year        year of movie premiere
     * @param country     country(-ies) of movie production
     * @param filePart    movie picture
     * @param path        path to the server root folder
     * @return edited movie
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static Movie editMovie(int movieId, String name, String description, int year, String country, Part filePart, String path) throws LogicException {
        Movie movie;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            String filename = PictureLoader.loadPicture(filePart, movieId, path, PICTURE_FOLDER, DEFAULT_PICTURE);
            movie = new Movie(movieId, name, description, year, country, 0.0, (filename != null) ? filename : PICTURE_FOLDER + File.separator + DEFAULT_PICTURE);
            MovieDAO movieDAO = new MovieDAO(connection);
            Movie oldMovie = movieDAO.update(movie);
            movie.setRating(oldMovie.getRating());
        } catch (IOException | SQLException | DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return movie;
    }

    /**
     * Deletes a movie from the system.
     *
     * @param movieId movie id
     * @return true if the movie was deleted, false otherwise
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static boolean deleteMovie(int movieId) throws LogicException {
        boolean result = false;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            MovieDAO movieDAO = new MovieDAO(connection);
            result = movieDAO.delete(movieId);
        } catch (SQLException | DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return result;
    }
}
