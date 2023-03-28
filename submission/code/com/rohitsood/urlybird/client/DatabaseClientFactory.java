package com.rohitsood.urlybird.client;

import com.rohitsood.urlybird.mode.Mode;


/**
 * Factory for creating clients. The <tt>DatabaseClientFactory</tt> creates client based on the mode requested. Modes
 * are defined in <tt>DatabaseClient</tt> interface.
 *
 * @author Rohit Sood
 * @version 1.1
 */
public class DatabaseClientFactory {
    /** Default error message for unsupported client mode. */
    private static final String UNSUPPORTED_CLIENT_MESSAGE = "Unsupported Client Mode";

    /**
     * No-args default constructor creates a new DatabaseClientFactory object.
     */
    public DatabaseClientFactory() {
    }

    /**
     * Creates a DatabaseClient object based on mode.  Checks for mode, if a mode is not matched, then a
     * <tt>IllegalArgumentException</tt> is thrown. If <tt>Mode.ONLINE</tt> is requested the
     * <tt>DatabaseNetworkClient</tt> instance is returned. If <tt>Mode.OFFLINE</tt> or <tt>Mode.SERVER</tt> is
     * selected, then <tt>DatabaseDirectClient</tt> instance is returned.
     *
     * @param mode The mode for which a database client is requested.
     *
     * @return <tt>DatabaseClient</tt> based on the mode.
     */
    public DatabaseClient getDatabaseClient(int mode) {
        DatabaseClient client = null;

        switch (mode) {
        case Mode.ONLINE:
            client = new DatabaseNetworkClient();

            break;

        case Mode.OFFLINE:
        case Mode.SERVER:
            client = new DatabaseDirectClient();

            break;

        default:
            throw new IllegalArgumentException(UNSUPPORTED_CLIENT_MESSAGE);
        }

        return client;
    }
}
