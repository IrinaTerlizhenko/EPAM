package by.bsu.cinemarating.dao;

import by.bsu.cinemarating.database.WrapperConnection;
import by.bsu.cinemarating.entity.Ban;
import by.bsu.cinemarating.entity.BanType;
import by.bsu.cinemarating.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 14.04.16
 * Time: 5:39
 * To change this template use File | Settings | File Templates.
 */
public class BanDAO extends AbstractDAO<Ban> {
    private static final String SELECT_ALL = "SELECT ban_id,user_id,type,expiration,reason FROM bans";
    private static final String SELECT_BY_ID = "SELECT user_id,type,expiration,reason FROM bans WHERE ban_id=?";
    private static final String SELECT_BY_USER_ID = "SELECT ban_id,type,expiration,reason FROM bans WHERE user_id=?";

    private static final String INSERT_BAN = "INSERT INTO bans(user_id,type,expiration,reason) VALUES(?,?,?,?)";

    private static final String UPDATE_BAN = "UPDATE bans SET user_id=?,type=?,expiration=?,reason=? WHERE ban_id=?";

    private static final String DELETE_BY_ID = "DELETE FROM bans WHERE ban_id=?";

    public BanDAO(WrapperConnection connection) {
        super(connection);
    }

    @Override
    public List<Ban> findAll() throws DAOException {
        List<Ban> allBans = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(SELECT_ALL);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt(BAN_ID);
                int userId = rs.getInt(USER_ID);
                String type = rs.getString(TYPE);
                Timestamp expiration = rs.getTimestamp(EXPIRATION);
                String reason = rs.getString(REASON);
                Ban ban = new Ban(id, userId, BanType.valueOf(type.toUpperCase()), expiration, reason);
                allBans.add(ban);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return allBans;
    }

    @Override
    public Optional<Ban> findEntityById(int id) throws DAOException {
        Ban ban = null;
        try (PreparedStatement st = connection.prepareStatement(SELECT_BY_ID)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt(USER_ID);
                String type = rs.getString(TYPE);
                Timestamp expiration = rs.getTimestamp(EXPIRATION);
                String reason = rs.getString(REASON);
                ban = new Ban(id, userId, BanType.valueOf(type.toUpperCase()), expiration, reason);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(ban);
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
    public boolean create(Ban entity) throws DAOException {
        boolean created = false;
        try (PreparedStatement st = connection.prepareStatement(INSERT_BAN)) {
            st.setInt(1, entity.getUserId());
            st.setString(2, entity.getType().name());
            st.setTimestamp(3, entity.getExpiration());
            st.setString(4, entity.getReason());
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
    public Ban update(Ban entity) throws DAOException {
        Ban ban = findEntityById(entity.getId()).get();
        try (PreparedStatement st = connection.prepareStatement(UPDATE_BAN)) {
            st.setInt(1, entity.getUserId());
            st.setString(2, entity.getType().name());
            st.setTimestamp(3, entity.getExpiration());
            st.setString(4, entity.getReason());
            st.setInt(5, entity.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return ban;
    }

    public boolean isBanned(int userId) throws DAOException {
        boolean banned;
        try (PreparedStatement st = connection.prepareStatement(SELECT_BY_USER_ID)) {
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            banned = rs.next();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return banned;
    }
}
