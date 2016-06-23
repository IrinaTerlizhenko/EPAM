package by.bsu.cinemarating.logic;

import by.bsu.cinemarating.dao.BanDAO;
import by.bsu.cinemarating.database.ConnectionPool;
import by.bsu.cinemarating.database.WrapperConnection;
import by.bsu.cinemarating.entity.Ban;
import by.bsu.cinemarating.entity.BanType;
import by.bsu.cinemarating.exception.DAOException;
import by.bsu.cinemarating.exception.LogicException;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

/**
 * Created by Irina on 30.05.2016.
 * A service layer class implementing all the logic concerning bans.
 */
public class BanLogic {
    /**
     * Bans the user with a specific id.
     *
     * @param userId id of user to ban
     * @param type   type of the ban
     * @param reason reason of the ban
     * @return true if ban was added, false otherwise
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static boolean addBan(int userId, BanType type, String reason) throws LogicException {
        boolean result = false;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            BanDAO banDAO = new BanDAO(connection);
            Timestamp expiration = new Timestamp(new java.util.Date().getTime()); // todo EXPIRATION, not current
            Ban ban = new Ban(0, userId, type, expiration, reason);
            result = banDAO.create(ban);
        } catch (SQLException | DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return result;
    }

    /**
     * Checks if the user with a specific id is banned.
     *
     * @param userId id of the user
     * @return true if the user is banned, false otherwise
     * @throws LogicException if any exceptions occurred on the DAO or SQL layer
     */
    public static boolean isUserBanned(int userId) throws LogicException {
        boolean result = false;
        Optional<WrapperConnection> optConnection = Optional.empty();
        try {
            optConnection = ConnectionPool.getInstance().takeConnection();
            WrapperConnection connection = optConnection.orElseThrow(SQLException::new);
            BanDAO banDAO = new BanDAO(connection);
            result = banDAO.isBanned(userId);
        } catch (SQLException | DAOException e) {
            throw new LogicException(e);
        } finally {
            optConnection.ifPresent(ConnectionPool.getInstance()::returnConnection);
        }
        return result;
    }
}
