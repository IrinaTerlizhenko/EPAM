package by.bsu.cinemarating.dao;

import by.bsu.cinemarating.database.WrapperConnection;
import by.bsu.cinemarating.entity.User;
import by.bsu.cinemarating.exception.DAOException;

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
    private static final String SELECT_ALL = "SELECT user_id,login,email,reg_date,role_id,name,surname,status,photo,num_rated FROM users";
    private static final String SELECT_BY_ID = "SELECT login,email,reg_date,role_id,name,surname,status,photo,num_rated FROM users WHERE user_id=?";
    private static final String SELECT_PASSWORD_BY_LOGIN = "SELECT password FROM users WHERE login=?";
    private static final String SELECT_ID_BY_LOGIN = "SELECT user_id FROM users WHERE login=?";
    private static final String SELECT_STATUS_BY_ID = "SELECT status FROM users WHERE user_id=?";
    private static final String SELECT_RATINGS = "SELECT rating FROM ratings WHERE uid=?";
    private static final String SELECT_RATING = "SELECT rating FROM ratings WHERE mid=? AND uid=?";
    private static final String SELECT_BY_LOGIN = "SELECT user_id FROM users WHERE login=?";
    private static final String SELECT_BY_EMAIL = "SELECT user_id FROM users WHERE email=?";

    private static final String INSERT_USER = "INSERT INTO users(login,password,email,reg_date,role_id,status) VALUES(?,?,?,?,1,0)";

    private static final String DELETE_BY_ID = "DELETE FROM users WHERE user_id=?";

    private static final String UPDATE_USER = "UPDATE users SET login=?,email=?,reg_date=?,role_id=?,name=?,surname=?,status=?,photo=?,num_rated=? WHERE user_id=?";
    private static final String UPDATE_STATUS = "UPDATE users SET status=? WHERE user_id=?";
    private static final String UPDATE_NUM_RATED = "UPDATE users SET num_rated=? WHERE user_id=?";

    private static final String COUNT_RATED = "SELECT COUNT(mid) AS count FROM ratings WHERE uid=?";

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
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return rows > 0;
    }

    @Override
    public User update(User entity) throws DAOException {
        User user = findEntityById(entity.getId()).get();
        try (PreparedStatement st = connection.prepareStatement(UPDATE_USER)) {
            st.setString(1, entity.getLogin());
            st.setString(2, entity.getEmail());
            st.setDate(3, entity.getRegDate());
            st.setInt(4, entity.getRoleID());
            st.setString(5, entity.getName());
            st.setString(6, entity.getSurname());
            st.setDouble(7, entity.getStatus());
            st.setString(8, entity.getPhoto());
            st.setInt(9, entity.getNumRated());
            st.setInt(10, entity.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return user;
    }

    public void updateStatus(int id, double status) throws DAOException {
        try (PreparedStatement st = connection.prepareStatement(UPDATE_STATUS)) {
            st.setDouble(1, status);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    /*public ArrayList<Integer> selectRatings(int userId) throws DAOException {
        ArrayList<Integer> ratings = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(SELECT_RATINGS)) {
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            int rating;
            while (rs.next()) {
                rating = rs.getInt("rating");
                ratings.add(rating);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return ratings;
    }*/

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

    /*public double takeStatus(int userId) throws DAOException {
        double status = -1.0;
        try (PreparedStatement st = connection.prepareStatement(SELECT_STATUS_BY_ID)) {
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                status = rs.getDouble(STATUS);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return status;
    }*/

    public boolean updateNumRated(int userId, int numRated) throws DAOException {
        int rows;
        try (PreparedStatement st = connection.prepareStatement(UPDATE_NUM_RATED)) {
            st.setInt(1, numRated);
            st.setInt(2, userId);
            rows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return rows > 0;
    }

    /*public int countRated(int userId) throws DAOException {
        int count = -1;
        try (PreparedStatement st = connection.prepareStatement(COUNT_RATED)) {
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count"); // todo
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return count;
    }*/

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
}
