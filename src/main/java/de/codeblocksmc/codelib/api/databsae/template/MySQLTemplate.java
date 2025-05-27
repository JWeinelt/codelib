package de.codeblocksmc.codelib.api.databsae.template;

import de.codeblocksmc.codelib.api.databsae.DatabaseObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Template class for creating MySQL connections.
 * This class provides methods for connecting to a MySQL database and checking the connection status.
 * It works in conjunction with {@link Logger} for logging any issues or errors during the connection process.
 * This is commonly used in Bukkit and Paper plugins for logging database-related activities.
 *
 * @author JustCody
 * @version 1.0
 */
public abstract class MySQLTemplate extends DatabaseObject {

    // The connection object to the MySQL database
    public Connection conn;

    // Logger for logging messages related to the connection
    public final Logger log;

    // MySQL connection details
    public final String host;
    public final int port;
    public final String database;
    public final String user;
    public final String password;

    /**
     * Constructor for initializing the MySQL connection template.
     *
     * @param log {@link Logger} of the plugin, typically used to log connection issues.
     * @param host Hostname of the MySQL server (e.g., "localhost").
     * @param port Port of the MySQL server. Default is 3306.
     * @param database Name of the MySQL database to connect to.
     * @param user Username to authenticate with the MySQL server.
     * @param password Password for the given username.
     */
    public MySQLTemplate(Logger log, String host, int port, String database, String user, String password) {
        this.log = log;
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    /**
     * Establishes a connection to the MySQL server and creates a {@link Connection} object.
     * This method loads the MySQL JDBC driver, attempts to connect to the database,
     * and logs any exceptions if the connection fails.
     */
    public void connect() {
        final String DB_NAME = "jdbc:mysql://"+host+":"+port+"/"+database+"?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true";

        try {
            // Load MySQL JDBC driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection using the provided connection details
            conn = DriverManager.getConnection(DB_NAME, user, password);

        } catch (Exception ex) {
            // Log any exception that occurs during the connection process
            log.warning("MySQL connection failed: " + ex.getMessage());
        }
    }

    /**
     * Checks the current MySQL connection to verify if it is still open and valid.
     * If the connection is closed or null, this method will attempt to reconnect using {@link MySQLTemplate#connect()}.
     */
    public void checkConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                // Attempt to reconnect if the connection is null or closed
                connect();
            }
        } catch (SQLException e) {
            // Log any exceptions encountered while checking the connection status
            log.warning("MySQL connection check failed: " + e.getMessage());
        }
    }

    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
    }

    public abstract void afterSuccessfulConnection();
}
