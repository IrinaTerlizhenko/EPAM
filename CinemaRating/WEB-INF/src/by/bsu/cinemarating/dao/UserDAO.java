package by.bsu.cinemarating.dao;

import by.bsu.cinemarating.database.WrapperConnection;
import by.bsu.cinemarating.entity.User;
import by.bsu.cinemarating.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 12.04.16
 * Time: 5:09
 * To change this template use File | Settings | File Templates.
 */
public class UserDAO extends AbstractDAO<User> {
    private static Logger log = LogManager.getLogger(UserDAO.class);

    private static final String SELECT_ALL = "SELECT user_id,login,email,reg_date,role_id,name,surname,status,photo,num_rated FROM users";
    private static final String SELECT_BY_ID = "SELECT login,email,reg_date,role_id,name,surname,status,photo,num_rated FROM users WHERE user_id=?";
    private static final String SELECT_USER_BY_LOGIN = "SELECT user_id,email,reg_date,role_id,name,surname,status,photo,num_rated FROM users WHERE login=?";
    private static final String SELECT_PASSWORD_BY_LOGIN = "SELECT password FROM users WHERE login=?";
    private static final String SELECT_ID_BY_LOGIN = "SELECT user_id FROM users WHERE login=?";
    private static final String SELECT_RATING = "SELECT rating FROM ratings WHERE mid=? AND uid=?";
    private static final String SELECT_BY_LOGIN = "SELECT user_id FROM users WHERE login=?";
    private static final String SELECT_BY_EMAIL = "SELECT user_id FROM users WHERE email=?";
    private static final String SELECT_LAST_ID = "SELECT MAX(user_id) AS id FROM users";

    private static final String INSERT_USER = "INSERT INTO users(login,password,email,reg_date,role_id) VALUES(?,?,?,?,1)";

    private static final String DELETE_BY_ID = "DELETE FROM users WHERE user_id=?";

    private static final String UPDATE_USER = "UPDATE users SET login=?,email=?,reg_date=?,role_id=?,name=?,surname=?,status=?,photo=?,num_rated=? WHERE user_id=?";
    private static final String UPDATE_USER_WITH_PASSWORD =
            "UPDATE users SET login=?,email=?,reg_date=?,role_id=?,name=?,surname=?,status=?,photo=?,num_rated=?,password=? WHERE user_id=?";
    private static final String UPDATE_STATUS = "UPDATE users SET status=? WHERE user_id=?";
    private static final String UPDATE_NUM_RATED = "UPDATE users SET num_rated=? WHERE user_id=?";

    public UserDAO(WrapperConnection connection) {
        super(connection);
    }

    @Override
    public boolean create(User entity) throws DAOException {
        boolean created = false;
        try (PreparedStatement st = connection.prepareStatement(INSERT_USER)) {
            st.setString(1, entity.getLogin());
            st.setString(2, entity.getPassword());
            st.setString(3, entity.getEmail());
            st.setDate(4, entity.getRegDate());
            int res = st.executeUpdate();
            if (res > 0) {
                created = true;
                updateId(entity);
                log.info("User " + entity + " created");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return created;
    }

    @Override
    public List<User> findAll() throws DAOException {
        List<User> allUsers = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(USER_ID);
                String login = rs.getString(LOGIN);
                String email = rs.getString(EMAIL);
                Date reg_date = rs.getDate(REG_DATE);
                byte role_id = rs.getByte(ROLE_ID);
                String name = rs.getString(NAME);
                String surname = rs.getString(SURNAME);
                double status = rs.getDouble(STATUS);
                String photo = rs.getString(PHOTO);
                int numRated = rs.getInt(NUM_RATED);
                User user = new User(id, login, email, reg_date, role_id, name, surname, status, photo, numRated);
                allUsers.add(user);
            }
            log.info("All users retrieved");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return allUsers;
    }

    @Override
    public Optional<User> findEntityById(int id) throws DAOException {
        User user = null;
        try (PreparedStatement st = connection.prepareStatement(SELECT_BY_ID)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String login = rs.getString(LOGIN);
                String email = rs.getString(EMAIL);
                Date reg_date = rs.getDate(REG_DATE);
                byte role_id = rs.getByte(ROLE_ID);
                String name = rs.getString(NAME);
                String surname = rs.getString(SURNAME);
                double status = rs.getDouble(STATUS);
                String photo = rs.getString(PHOTO);
                int numRated = rs.getInt(NUM_RATED);
                user = new User(id, login, email, reg_date, role_id, name, surname, status, photo, numRated);
                log.info("User [id = " + id + "] found");
            } else {
                log.info("User [id = " + id + "] not found");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(user);
    }

    public Optional<User> findEntityByLogin(String login) throws DAOException {
        User user = null;
        try (PreparedStatement st = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(ID);
                String email = rs.getString(EMAIL);
                Date reg_date = rs.getDate(REG_DATE);
                byte role_id = rs.getByte(ROLE_ID);
                String name = rs.getString(NAME);
                String surname = rs.getString(SURNAME);
                double status = rs.getDouble(STATUS);
                String photo = rs.getString(PHOTO);
                int numRated = rs.getInt(NUM_RATED);
                user = new User(id, login, email, reg_date, role_id, name, surname, status, photo, numRated);
                log.info("User [login = " + login + "] found");
            } else {
                log.info("User [login = " + login + "] not found");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(user);
    }

    public Optional<String> findPasswordByLogin(String login) throws DAOException {
        String password = null;
        try (PreparedStatement st = connection.prepareStatement(SELECT_PASSWORD_BY_LOGIN)) {
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                password = rs.getString(PASSWORD);
                log.info("Password by login = " + login + " retrieved");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(password);
    }

    public Optional<Integer> findIdByLogin(String login) throws DAOException {
        Integer id = null;
        try (PreparedStatement st = connection.prepareStatement(SELECT_ID_BY_LOGIN)) {
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                id = rs.getInt(USER_ID);
                log.info("Id by login = " + login + " retrieved");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(id);
    }

    @Override
    public boolean delete(int id) throws DAOException {
        int rows;
        try (PreparedStatement st = connection.prepareStatement(DELETE_BY_ID)) {
            st.setInt(1, id);
            rows = st.executeUpdate();
            if (rows > 0) {
                log.info("User [id = " + id + "] deleted");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return rows > 0;
    }

    @Override
    public User update(User entity) throws DAOException {
        return update(entity, false);
    }

    public User updateWithPassword(User entity) throws DAOException {
        return update(entity, true);
    }

    private User update(User entity, boolean withPassword) throws DAOException {
        User user = findEntityById(entity.getId()).get();
        String sqlQuery = withPassword ? UPDATE_USER_WITH_PASSWORD : UPDATE_USER;
        try (PreparedStatement st = connection.prepareStatement(sqlQuery)) {
            st.setString(1, entity.getLogin());
            st.setString(2, entity.getEmail());
            st.setDate(3, entity.getRegDate());
            st.setInt(4, entity.getRoleID());
            st.setString(5, entity.getName());
            st.setString(6, entity.getSurname());
            st.setDouble(7, entity.getStatus());
            st.setString(8, entity.getPhoto());
            st.setInt(9, entity.getNumRated());
            if (withPassword) {
                st.setString(10, entity.getPassword());
                st.setInt(11, entity.getId());
            } else {
                st.setInt(10, entity.getId());
            }
            int updated = st.executeUpdate();
            if (updated > 0) {
                log.info("Movie " + entity + " updated");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return user;
    }

    public void updateStatus(int id, double status) throws DAOException {
        try (PreparedStatement st = connection.prepareStatement(UPDATE_STATUS)) {
            st.setDouble(1, status);
            st.setInt(2, id);
            if (st.executeUpdate() > 0) {
                log.info("User [id = " + id + "] status updated to " + status);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public boolean isLoginFree(String login) throws DAOException {
        boolean isFree;
        try (PreparedStatement st = connection.prepareStatement(SELECT_BY_LOGIN)) {
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            isFree = !rs.next();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return isFree;
    }

    public boolean isEmailFree(String email) throws DAOException {
        boolean isFree;
        try (PreparedStatement st = connection.prepareStatement(SELECT_BY_EMAIL)) {
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            isFree = !rs.next();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return isFree;
    }

    public boolean updateNumRated(int userId, int numRated) throws DAOException {
        int rows;
        try (PreparedStatement st = connection.prepareStatement(UPDATE_NUM_RATED)) {
            st.setInt(1, numRated);
            st.setInt(2, userId);
            rows = st.executeUpdate();
            if (rows > 0) {
                log.info("User [id = " + userId + " numRated updated to " + numRated);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return rows > 0;
    }

    public boolean ratedMovie(int movieId, int userId) throws DAOException {
        try (PreparedStatement st = connection.prepareStatement(SELECT_RATING)) {
            st.setInt(1, movieId);
            st.setInt(2, userId);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public int selectLastId() throws DAOException {
        return super.selectLastId(SELECT_LAST_ID);
    }
}
