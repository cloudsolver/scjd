package com.rohitsood.urlybird.server;

import com.rohitsood.urlybird.config.ConfigurationProperties;
import com.rohitsood.urlybird.gui.manager.GuiManager;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


/**
 * Starts and stops the network server. Defines and uses the system properties needed to successfully start the network
 * server. Defines the codebase, user directory, the name of the jar file from which the application should run.
 * Starts the RMI Registry and binds the remote objects to the registry. Unbinds the remote objects from the registry.
 *
 * @author Rohit Sood
 * @version 1.3
 */
public final class DatabaseNetworkServerStarter {
    /** The singleton instance */
    private static DatabaseNetworkServerStarter starter;

    /** Flag that indicates start */
    private boolean started = false;

    /** Configuration as specified in the <tt>suncertify.properties</tt> file. */
    ConfigurationProperties config = new ConfigurationProperties();

    /** The manager to delegate to. */
    private GuiManager manager;

    /**
     * Private constructor prevents instantiation of this object. Makes a call to initialize the object.
     */
    private DatabaseNetworkServerStarter(GuiManager manager) {
        this.manager = manager;
    }

    /**
     * Starts the server.  Starts the registry and binds appropriate objects.
     *
     * @throws Exception If any failures occur starting registry or binding objects.
     */
    public void start() throws Exception {
        console("\nStarting Server.\n");

        boolean registryStarted = startRegistry();
        boolean rebindSuccess = rebind();

        if (rebindSuccess && registryStarted) {
            console("\nServer ready for e-business\n");
        } else {
            console("\nServer could not be started due to errors.\n");
        }
    }

    /**
     * Stops the server. Unbinds remote objects and stops the registry.
     *
     * @throws Exception If any failures occur starting registry.
     */
    public void stop() throws Exception {
        console("\nStopping Server.\n");
        unBind();
        console("\nServer Stopped.\n");
    }

    /**
     * Gets the boolean which represents the started state.
     *
     * @return If <tt>true</tt> then the server is started, <tt>false</tt> the server is stopped.
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * Unbinds the remote objects.
     *
     * @throws Exception If remote objects could not be unbound.
     */
    private void unBind() throws Exception {
        console("\nUnbinding remote object(s)...");

        boolean clean = false;
        Naming.unbind(config.getLookupName());
        clean = DatabaseNetworkServerFactoryImpl.shutDown();

        if (clean) {
            console(" unbind complete.\n");
        } else {
            console(" problems encountered unbinding and removing server instances.\n");
        }
    }

    /**
     * Returns the instance of the <tt>DatabaseNetworkServerStarter</tt>.
     *
     * @return DatabaseNetworkServerStarter
     */
    public static synchronized DatabaseNetworkServerStarter getInstance(GuiManager manager) {
        if (null == starter) {
            starter = new DatabaseNetworkServerStarter(manager);
        }

        return starter;
    }

    /**
     * Attempts to locate a current registry and export it. Then attempts to create a new registry and export that.
     */
    private boolean startRegistry() {
        console("\nStarting Registry\n");

        boolean success = true;
        final String port = config.getPort();

        try {
            final int portNumber = Integer.parseInt(port);

            LocateRegistry.createRegistry(portNumber);

            console("Created Registry on port: " + portNumber);
        } catch (RemoteException e) {
            success = true; //because the registry already exists
            console("\nReusing current registry.\n");
        } catch (NumberFormatException e) {
            success = false;
            console("\n**Incorrect port number:" + port + " The server will not start.**");
        }

        if (success) {
            console("\nRegistry Ready.\n");
        } else {
            throw new RuntimeException("\n**There was a problem starting the registry.**\n");
        }

        return success;
    }

    /**
     * Binds the factory object to the registry. If the binding already exists it rebinds it.
     */
    private boolean rebind() {
        console("\nBinding Remote Object(s)...");

        boolean success = true;

        try {
            final DatabaseNetworkServerFactory obj = new DatabaseNetworkServerFactoryImpl();
            console("\nbinding DatabaseNetworkServerFactory (impl stub) to " + config.getLookupName());

            Naming.rebind(config.getLookupName(), obj);

            console("\nDatabaseNetworkServerFactory bound in registry.\n");
        } catch (SecurityException se) {
            success = false;
            console("\n Not enough security access to connect & bind remote objects.\n " + se.getMessage());
            throw new RuntimeException("Could not bind objects due to a security problem.");
        } catch (RemoteException re) {
            success = false;
            console("\nCould not bind remote objects to registry: " + re.getMessage());
            throw new RuntimeException("Could not bind remote objects to the registry.");
        } catch (Exception e) {
            success = false;
            console(e.getClass().toString());
            throw new RuntimeException("\nCould not bind remote objects  error: " + e.getMessage());
        }

        return success;
    }

    /**
     * Prints debug statements on the console for development and bug fixes.
     *
     * @param msg The message to print to the console.
     */
    private void console(String msg) {
        manager.appendToConsole(msg);
    }
}
