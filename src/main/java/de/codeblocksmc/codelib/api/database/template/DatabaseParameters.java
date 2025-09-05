package de.codeblocksmc.codelib.api.database.template;

import java.util.HashMap;

/**
 * Class to build database connection parameters for different database types.
 */
public class DatabaseParameters {
    private final HashMap<String, String> parameters;
    private final Mode mode;

    protected DatabaseParameters(HashMap<String, String> parameters, Mode mode) {
        this.parameters = parameters;
        this.mode = mode;
    }

    /**
     * Constructs the parameter string based on the database mode.
     *
     * @return The formatted parameter string.
     * <p>
     * Example for MySQL: "?key1=value1&key2=value2"
     * Example for MSSQL: "" (currently not implemented)
     */
    public String getParameters() {
        if (mode.equals(Mode.MYSQL)) {
            StringBuilder sb = new StringBuilder("?");
            parameters.forEach((key, value) -> {
                if (!sb.isEmpty()) {
                    sb.append("&");
                }
                sb.append(key).append("=").append(value);
            });
            return sb.toString();
        } else {
            //TODO: add Syntax for MSSQL Server
            return "";
        }
    }


    /**
     * Builder class for constructing {@link DatabaseParameters} instances.
     */
    public static class Builder {
        private final Mode mode;
        private final HashMap<String, String> parameters = new HashMap<>();

        public Builder(Mode mode) {
            this.mode = mode;
        }

        /**
         * Adds a custom parameter key-value pair.
         *
         * @param key The parameter key.
         * @param value The parameter value.
         * @return The Builder instance for chaining.
         */
        public Builder param(String key, String value) {
            parameters.put(key, value);
            return this;
        }

        /** Enables or disables automatic reconnection.
         *
         * @param autoReconnect true to enable, false to disable.
         * @return The Builder instance for chaining.
         */
        public Builder autoReconnect(boolean autoReconnect) {
            parameters.put("autoReconnect", String.valueOf(autoReconnect));
            return this;
        }

        /**
         * Sets the useSSL parameter.
         * @param useSSL true to use SSL, false otherwise.
         * @return The Builder instance for chaining.
         */
        public Builder useSSL(boolean useSSL) {
            parameters.put("useSSL", String.valueOf(useSSL));
            return this;
        }

        /**
         * Sets the time zone shift parameter.
         *
         * @param timeZoneShift true to use JDBC compliant timezone shift, false otherwise.
         * @return The Builder instance for chaining.
         */
        public Builder timeZoneShift(boolean timeZoneShift) {
            parameters.put("useJDBCCompliantTimezoneShift", String.valueOf(timeZoneShift));
            return this;
        }

        /**
         * Sets the time zone UTC parameter.
         *
         * @param timeZoneUTC true to use UTC timezone, false otherwise.
         * @return The Builder instance for chaining.
         */
        public Builder timeZoneUTC(boolean timeZoneUTC) {
            parameters.put("useLegacyDatetimeCode", String.valueOf(!timeZoneUTC));
            return this;
        }

        /**
         * Sets the server time zone parameter.
         *
         * @param serverTimeZone The server time zone (e.g., "UTC", "America/New_York").
         * @return The Builder instance for chaining.
         */
        public Builder serverTimeZone(String serverTimeZone) {
            parameters.put("serverTimezone", serverTimeZone);
            return this;
        }

        /**
         * Builds the DatabaseParameters instance.
         *
         * @return A new DatabaseParameters instance with the configured parameters.
         */
        public DatabaseParameters build() {
            return new DatabaseParameters(parameters, this.mode);
        }
    }

    public enum Mode {
        MYSQL,
        MSSQL;
    }
}
