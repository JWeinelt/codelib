package de.codeblocksmc.codelib.databsae;

import lombok.Getter;

/**
 * Abstract MySQL-specific implementation of {@link SQLTemplate}.
 *
 * @author JustCody
 * @author CrAfTs_ArMy
 * @version 2.0
 */
public abstract non-sealed class MySQLTemplate extends SQLTemplate {

    /**
     * Additional JDBC connection flags appended to the connection URL.
     */
    @Getter
    private final String additionalFlags;

    /**
     * Creates a new MySQLTemplate without any additional connection flags.
     *
     * @param host     The database host
     * @param port     The database port
     * @param database The database name
     * @param user     The database user
     * @param password The database password
     */
    public MySQLTemplate(String host, int port, String database, String user, String password) {
        super(host, port, database, user, password);
        this.additionalFlags = "";
    }

    /**
     * Creates a new MySQLTemplate with additional JDBC connection flags.
     * <p>
     * Each flag should be provided in the format {@code key=value}.
     * Multiple flags will be joined using {@code &}.
     * </p>
     *
     * @param host            The database host
     * @param port            The database port
     * @param database        The database name
     * @param user            The database user
     * @param password        The database password
     * @param additionalFlags Optional JDBC connection flags
     */
    public MySQLTemplate(String host, int port, String database, String user, String password, String... additionalFlags) {
        super(host, port, database, user, password);
        this.additionalFlags = String.join("&", additionalFlags);
    }

    /**
     * Returns the fully qualified MySQL JDBC driver class name.
     *
     * @return The MySQL JDBC driver class
     */
    @Override
    protected String getDriverClass() {
        return "com.mysql.cj.jdbc.Driver";
    }

    /**
     * Builds the MySQL JDBC connection URL.
     * <p>
     * The URL follows the format:
     * {@code jdbc:mysql://host:port/database}
     * and optionally appends query parameters if additional flags are present.
     * </p>
     *
     * @return The JDBC connection URL template
     */
    @Override
    protected String getConnectionUrl() {
        String url = "jdbc:mysql://%host%:%port%/%database%";
        return url + (hasAdditionalFlags() ? "?" + additionalFlags : "");
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
