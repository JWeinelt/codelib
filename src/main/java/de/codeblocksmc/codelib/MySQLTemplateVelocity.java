package de.codeblocksmc.codelib;

import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Template class for creating MySQL connections
 */
@Deprecated(forRemoval = true)
public class MySQLTemplateVelocity {

    public Connection conn;
    public final Logger log;

    private final String host;
    private final int port;
    private final String database;
    private final String user;
    private final String password;

    /**
     *
     * @param log {@link Logger} of the Plugin
     * @param host Hostname
     * @param port Port of the server. Default: 3306
     * @param database Database name
     * @param user Username
     * @param password Password
     */
    public MySQLTemplateVelocity(Logger log, String host, int port, String database, String user, String password) {
        this.log = log;
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    /**
     * Connects to the MySQL server and creates a {@link Connection} object
     */
    public void connect() {
        final String DB_NAME = "jdbc:mysql://"+host+":"+port+"/"+database+"?useJDBCCompliantTimezoneShift=true&useLeg" +
                "acyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Gets the driver class

            conn = DriverManager.getConnection(DB_NAME, user, password); //Gets a connection to the database using the details you provided.

        }
        catch(Exception ex) {
            log.warn(ex.getMessage());
        }
    }

    /**
     * Checks if the connection is ok, otherwise it will try to {@link MySQLTemplate#connect()} to the server
     */
    public void checkConnection() {
        try {
            if (conn == null || conn.isClosed()) connect();
        } catch (SQLException e) {
            log.warn(e.getMessage());
        }
    }
}
