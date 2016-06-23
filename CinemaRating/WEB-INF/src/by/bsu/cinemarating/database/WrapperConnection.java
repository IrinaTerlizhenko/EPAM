package by.bsu.cinemarating.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 12.04.16
 * Time: 3:42
 * Wrapper on java.sql.Connection.
 */
public class WrapperConnection {
    private static Logger log = LogManager.getLogger(WrapperConnection.class);
    private Connection connection;

    /**
     * @param prop a list of arbitrary string tag/value pairs as connection arguments; normally at least a "user" and "password" property should be included
     * @param url  a database url of the form jdbc:subprotocol:subname
     */
    WrapperConnection(Properties prop, String url) {
        try {
            connection = DriverManager.getConnection(url, prop);
        } catch (SQLException e) {
            log.error("Connection not obtained.", e);
        }
    }

    /**
     * Releases Statement object's database and JDBC resources immediately instead of waiting for this to happen when it is automatically closed.
     *
     * @param statement Statement object related to this WrapperConnection to release resources
     */
    public void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.error("Statement is null.", e);
            }
        }
    }

    /**
     * Releases this Connection object's database and JDBC resources immediately instead of waiting for them to be automatically released.
     */
    void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Wrong connection.", e);
            }
        }
    }

    /**
     * Creates a PreparedStatement object for sending parameterized SQL statements to the database.
     *
     * @param sql an SQL statement that may contain one or more '?' IN parameter placeholders
     * @return a new default PreparedStatement object containing the pre-compiled SQL statement
     * @throws SQLException
     */
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        if (connection != null) {
            PreparedStatement statement = connection.prepareStatement(sql);
            if (statement != null) {
                return statement;
            }
        }
        throw new SQLException("Connection or statement is null.");
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

    public void commit() throws SQLException {
        connection.commit();
    }
}