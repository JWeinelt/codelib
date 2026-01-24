package de.codeblocksmc.codelib.database;

import lombok.Getter;

/**
 * Abstract Microsoft SQL Server specific implementation of {@link SQLTemplate}.
 *
 * @author JustCody
 * @author CrAfTs_ArMy
 * @version 2.0
 */
public abstract non-sealed class MSSQLTemplate extends SQLTemplate {

    /**
     * Additional JDBC connection flags appended to the connection URL.
     */
    @Getter
    private final String additionalFlags;

    /**
     * Creates a new MSSQLTemplate without any additional connection flags.
     *
     * @param host     The database host
     * @param port     The database port
     * @param database The database name
     * @param user     The database user
     * @param password The database password
     */
    public MSSQLTemplate(String host, int port, String database, String user, String password) {
        super(host, port, database, user, password);
        this.additionalFlags = "";
    }

    /**
     * Creates a new MSSQLTemplate with additional JDBC connection flags.
     * <p>
     * Each flag should be provided in the format {@code key=value}.
     * Multiple flags will be joined using {@code ;}.
     * </p>
     *
     * @param host            The database host
     * @param port            The database port
     * @param database        The database name
     * @param user            The database user
     * @param password        The database password
     * @param additionalFlags Optional JDBC connection flags
     */
    public MSSQLTemplate(String host, int port, String database, String user, String password, String... additionalFlags) {
        super(host, port, database, user, password);
        this.additionalFlags = String.join(";", additionalFlags);
    }

    /**
     * Returns the fully qualified Microsoft SQL Server JDBC driver class name.
     *
     * @return The SQL Server JDBC driver class
     */
    @Override
    protected String getDriverClass() {
        return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    }

    /**
     * Builds the Microsoft SQL Server JDBC connection URL.
     * <p>
     * The URL follows the format:
     * {@code jdbc:sqlserver://host:port;databaseName=database}
     * and optionally appends additional connection properties.
     * </p>
     *
     * @return The JDBC connection URL template
     */
    @Override
    protected String getConnectionUrl() {
        String url = "jdbc:sqlserver://%host%:%port%;databaseName=%database%";
        return url + (hasAdditionalFlags() ? ";" + additionalFlags : "");
    }

    /**
     * Checks whether any additional JDBC connection flags are defined.
     *
     * @return {@code true} if additional flags are present, otherwise {@code false}
     */
    public boolean hasAdditionalFlags() {
        return additionalFlags != null && !additionalFlags.isBlank();
    }

}
