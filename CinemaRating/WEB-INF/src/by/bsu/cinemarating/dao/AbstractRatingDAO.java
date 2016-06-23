package by.bsu.cinemarating.dao;

import by.bsu.cinemarating.database.WrapperConnection;
import by.bsu.cinemarating.exception.DAOException;

import java.util.Optional;

/**
 * Created by User on 06.06.2016.
 */
public abstract class AbstractRatingDAO<T> implements IDAO<T> {
    protected WrapperConnection connection;

    public AbstractRatingDAO(WrapperConnection connection) {
        this.connection = connection;
    }

    public abstract boolean delete(int movieId, int userId) throws DAOException;

    public abstract Optional<T> findEntityById(int movieId, int userId) throws DAOException;

    public abstract boolean replace(T entity) throws DAOException;
}
