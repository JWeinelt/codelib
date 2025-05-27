package de.codeblocksmc.codelib.api.databsae;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MSSQLTemplate extends DatabaseObject {
    @Getter
    public Connection conn;
    private final Logger log;
    private final String DB_NAME;
    private final String DB_USER;
    private final String DB_PASSWORD;
    private final int DB_PORT;
    private final String DB_HOST;

    public MSSQLTemplate(Logger log, String DB_NAME, String DB_USER, String DB_PASSWORD, int DB_PORT, String DB_HOST) {
        this.log = log;
        this.DB_NAME = DB_NAME;
        this.DB_USER = DB_USER;
        this.DB_PASSWORD = DB_PASSWORD;
        this.DB_PORT = DB_PORT;
        this.DB_HOST = DB_HOST;
    }

    public Connection connect() {
        String CONNECTION_STRING = "jdbc:sqlserver://HOST;databaseName=DATABASE;encrypt=false;trustServerCertificate=true;"
                .replace("HOST", DB_HOST).replace("DATABASE", DB_NAME);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(CONNECTION_STRING, DB_USER, DB_PASSWORD);
            log.info("MSSQL Connection established.");
            conn.createStatement().execute("USE " + DB_NAME);
            return conn;
        }
        catch(Exception ex) {
            log.warning(ex.getMessage());
        }
        return null;
    }

    public void checkConnection() {
        try {
            if (conn == null || conn.isClosed()) connect();
        } catch (SQLException ignored) {}
    }
    public void disconnect() {
        try {
            conn.close();
            log.info("SQL Connection destroyed.");
        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
    }
}
