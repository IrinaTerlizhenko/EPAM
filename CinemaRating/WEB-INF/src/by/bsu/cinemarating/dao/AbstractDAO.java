package by.bsu.cinemarating.dao;

import by.bsu.cinemarating.database.WrapperConnection;
import by.bsu.cinemarating.entity.Entity;
import by.bsu.cinemarating.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 12.04.16
 * Time: 3:51
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractDAO<T extends Entity> implements IDAO<T> {
    protected WrapperConnection connection;
    protected static final String LAST_ID = "SELECT LAST_INSERT_ID() AS id";
    protected static final String ID = "id";

    public AbstractDAO(WrapperConnection connection) {
        this.connection = connection;
    }

    public abstract List<T> findAll() throws DAOException;

    public abstract Optional<T> findEntityById(int id) throws DAOException;

    public abstract boolean delete(int id) throws DAOException;

    public abstract T update(T entity) throws DAOException;

    public void close(Statement st) {
        connection.closeStatement(st);
    }

    protected void updateId(Entity entity) throws SQLException {
        try (PreparedStatement stId = connection.prepareStatement(LAST_ID)) {
            ResultSet rs = stId.executeQuery();
            if (rs.next()) {
                entity.setId(rs.getInt(ID));
            }
        }
    }
}
