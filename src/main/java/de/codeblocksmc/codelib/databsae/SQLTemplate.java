package de.codeblocksmc.codelib.databsae;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Abstract base class for SQL database templates.
 *
 * @author CrAfTs_ArMy
 * @version 1.0
 */
public sealed abstract class SQLTemplate permits MSSQLTemplate, MySQLTemplate {

    /**
     * The database host address.
     */
    private final String host;

    /**
     * The port on which the database server is running.
     */
    private final int port;

    /**
     * The name of the database schema to connect to.
     */
    private final String database;

    /**
     * The username used for database authentication.
     */
    private final String user;

    /**
     * The password used for database authentication.
     */
    private final String password;

    /**
     * The active JDBC connection.
     */
    @Getter
    private Connection connection;

    /**
     * Creates a new {@link SQLTemplate} with the given connection parameters.
     *
     * @param host     The database host
     * @param port     The database port
     * @param database The database name
     * @param user     The database user
     * @param password The database password
     */
    public SQLTemplate(String host, int port, String database, String user, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    /**
     * Hook method that is called after a successful database connection.
     */
    protected abstract void afterSuccessfulConnection();

    /**
     * Returns the JDBC connection URL template.
     * <p>
     * The returned string may contain the placeholders:
     * <ul>
     *     <li>{@code %host%}</li>
     *     <li>{@code %port%}</li>
     *     <li>{@code %database%}</li>
     * </ul>
     * which will be replaced before establishing the connection.
     * </p>
     *
     * @return The JDBC connection URL template
     */
    protected abstract String getConnectionUrl();

    /**
     * Returns the fully qualified class name of the JDBC driver.
     * <p>
     * If {@code null} is returned, no explicit driver loading will be attempted.
     * </p>
     *
     * @return The JDBC driver class name or {@code null}
     */
    protected abstract String getDriverClass();

    /**
     * Establishes a connection to the database.
     * <p>
     * This method loads the JDBC driver (if provided), builds the connection URL,
     * and opens a connection using {@link DriverManager}.
     * </p>
     *
     * @return The established {@link Connection}
     * @throws RuntimeException If the driver cannot be loaded or the connection fails
     */
    public Connection connect() {
        try {
            String driverClass = getDriverClass();
            if (driverClass != null) {
                Class.forName(driverClass);
            }

            String connectionUrl = getConnectionUrl()
                    .replace("%host%", host)
                    .replace("%port%", String.valueOf(port))
                    .replace("%database%", database);

            connection = DriverManager.getConnection(connectionUrl, user, password);
            afterSuccessfulConnection();
            return connection;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load driver: " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect: " + e.getMessage(), e);
        }
    }

    /**
     * Closes the current database connection.
     *
     * @throws RuntimeException if closing the connection fails
     */
    public void disconnect() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to disconnect: " + e.getMessage(), e);
        }
    }

    /**
     * Checks whether the database connection is active.
     * <p>
     * If the connection is {@code null} or already closed,
     * a new connection will be established automatically.
     * </p>
     *
     * @throws RuntimeException if checking the connection fails
     */
    public void checkConnection() {
        try {
            Connection connection = getConnection();
            if (connection == null || connection.isClosed()) {
                this.connect();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to check the connection: " + e.getMessage(), e);
        }
    }

}
