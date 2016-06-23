package by.bsu.cinemarating.logic;

import by.bsu.cinemarating.dao.UserDAO;
import by.bsu.cinemarating.database.ConnectionPool;
import by.bsu.cinemarating.database.WrapperConnection;
import by.bsu.cinemarating.entity.User;
import by.bsu.cinemarating.exception.DAOException;
import by.bsu.cinemarating.exception.LogicException;
import by.bsu.cinemarating.logic.encrypt.MD5;
import by.bsu.cinemarating.validation.ValidationResult;
import by.bsu.cinemarating.validation.Validator;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 18.04.16
 * Time: 13:10
 * To change this template use File | Settings | File Templates.
 */
public class RegisterLogic {
    public static ValidationResult register(String login, String password, String repeatPassword, String email, User user) throws LogicException {
        ValidationResult result;
        if (!Validator.validateLogin(login) || !Validator.validatePassword(password)) {
            result = ValidationResult.LOGIN_PASS_INCORRECT;
        } else if (!Validator.validateEmail(email)) {
            result = ValidationResult.EMAIL_INCORRECT;
        } else if (!password.equals(repeatPassword)) {
            result = ValidationResult.PASS_NOT_MATCH;
        } else {
            Optional<WrapperConnection> optConnection = Optional.empty();
            try {
                optConnection = ConnectionPool.getInstance().takeConnection();
                WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
                UserDAO userDAO = new UserDAO(connection);
                if (userDAO.isLoginFree(login)) {
                    if (userDAO.isEmailFree(email)) {
                        String md5password = MD5.encrypt(password);
                        user.setLogin(login);
                        user.setPassword(md5password);
                        user.setEmail(email);
                        user.setRegDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
                        boolean registered = userDAO.create(user);
                        result = registered ? ValidationResult.ALL_RIGHT : ValidationResult.UNKNOWN_ERROR;
                    } else {
                        result = ValidationResult.EMAIL_NOT_UNIQUE;
                    }
                } else {
                    result = ValidationResult.LOGIN_NOT_UNIQUE;
                }
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
}
