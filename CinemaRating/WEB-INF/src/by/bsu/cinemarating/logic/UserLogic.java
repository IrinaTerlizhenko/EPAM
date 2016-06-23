package by.bsu.cinemarating.logic;

import by.bsu.cinemarating.dao.UserDAO;
import by.bsu.cinemarating.database.ConnectionPool;
import by.bsu.cinemarating.database.WrapperConnection;
import by.bsu.cinemarating.entity.User;
import by.bsu.cinemarating.exception.DAOException;
import by.bsu.cinemarating.exception.LogicException;
import by.bsu.cinemarating.loader.PictureLoader;
import by.bsu.cinemarating.logic.encrypt.MD5;
import by.bsu.cinemarating.validation.ValidationResult;
import by.bsu.cinemarating.validation.Validator;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 23.04.16
 * Time: 12:28
 * A service layer class implementing all the logic concerning users.
 */
public class UserLogic {
    /**
     * Folder where all profile photos are saved.
     */
    private static final String PICTURE_FOLDER = "img" + File.separator + "photo";
    private static final String DEFAULT_PICTURE = "default.png";

    /**
     * Checks user's login and password.
     *
     * @param enterLogin entered login
     * @param enterPass  entered password
     * @return user if login and password are correct, null otherwise
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static User checkLogin(String enterLogin, String enterPass) throws LogicException {
        User user = null;
        if (!Validator.validateLogin(enterLogin) || !Validator.validatePassword(enterPass)) {
            return null;
        }
        String md5password = MD5.encrypt(enterPass);
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            UserDAO userDAO = new UserDAO(connection);
            Optional<String> optPassword = userDAO.findPasswordByLogin(enterLogin);
            if (optPassword.isPresent()) {
                String pass = optPassword.get();
                if (pass.equals(md5password)) {
                    int id = userDAO.findIdByLogin(enterLogin).get();
                    user = userDAO.findEntityById(id).get(); // todo 1 method
                }
            }
        } catch (SQLException e) {
            throw new LogicException("DB connection error: ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return user;
    }

    /**
     * Finds the user with a specified id.
     *
     * @param userId user id
     * @return user, wrapped in Optional if exists, Optional.empty() otherwise
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static Optional<User> findUser(int userId) throws LogicException {
        Optional<User> user = null;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            UserDAO userDAO = new UserDAO(connection);
            user = userDAO.findEntityById(userId);
        } catch (SQLException e) {
            throw new LogicException("DB connection error: ", e);
        } catch (DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return user;
    }

    /**
     * Edits the user.
     *
     * @param user user to edit
     * @return result of user validation and editing
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static ValidationResult editUser(User user) throws LogicException {
        ValidationResult result = ValidationResult.UNKNOWN_ERROR;
        if (!Validator.validateName(user.getName())) {
            result = ValidationResult.NAME_INCORRECT;
        } else if (!Validator.validateSurname(user.getSurname())) {
            result = ValidationResult.SURNAME_INCORRECT;
        } else if (!Validator.validateEmail(user.getEmail())) {
            result = ValidationResult.EMAIL_INCORRECT;
        } else {
            Optional<WrapperConnection> optConnection = Optional.empty();
            try {
                optConnection = ConnectionPool.getInstance().takeConnection();
                WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
                UserDAO userDAO = new UserDAO(connection);
                userDAO.update(user);
                result = ValidationResult.ALL_RIGHT;
            } catch (SQLException e) {
                throw new LogicException("DB connection error: ", e);
            } catch (DAOException e) {
                throw new LogicException(e);
            } finally {
                optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
            }
        }
        return result;
    }

    /**
     * Deletes the user.
     *
     * @param userId user id
     * @return true if the user was deleted, false otherwise
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static boolean deleteUser(int userId) throws LogicException {
        boolean result = false;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            UserDAO userDAO = new UserDAO(connection);
            result = userDAO.delete(userId);
        } catch (SQLException | DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return result;
    }

    /**
     * Updates user's photo.
     *
     * @param user     user to update photo
     * @param filePart picture to set
     * @throws LogicException if any exceptions occurred with input/output
     */
    public static void updatePhoto(User user, Part filePart, String path) throws LogicException {
        try {
            String filename = PictureLoader.loadPicture(filePart, user.getId(), path, PICTURE_FOLDER, DEFAULT_PICTURE);
            user.setPhoto(filename);
        } catch (IOException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Retrieves all users registered in the system.
     *
     * @return list of all users registered in the system
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static List<User> takeAllUsers() throws LogicException {
        List<User> userList = new ArrayList<>();
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            UserDAO userDAO = new UserDAO(connection);
            userList = userDAO.findAll();
        } catch (SQLException | DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return userList;
    }
}
