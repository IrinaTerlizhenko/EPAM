package by.bsu.cinemarating.dao;

import by.bsu.cinemarating.exception.DAOException;

/**
 * Created by User on 23.06.2016.
 */
public interface IDAO<T> {
    boolean create(T entity) throws DAOException;

    String USER_ID = "user_id";
    String LOGIN = "login";
    String EMAIL = "email";
    String REG_DATE = "reg_date";
    String ROLE_ID = "role_id";
    String NAME = "name";
    String SURNAME = "surname";
    String STATUS = "status";
    String PHOTO = "photo";
    String NUM_RATED = "num_rated";
    String PASSWORD = "password";

    String BAN_ID = "ban_id";
    String TYPE = "type";
    String EXPIRATION = "expiration";
    String REASON = "reason";

    String MOVIE_ID = "movie_id";
    String DESCRIPTION = "description";
    String YEAR = "year";
    String COUNTRY = "country";
    String RATING = "rating";
    String REF = "ref";
    String UID = "uid";
    String REVIEW = "review";
    String TIME = "time";

    String MOVIE_RATING = "movie_rating";
}
