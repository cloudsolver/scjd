package com.rohitsood.urlybird.launcher;

import com.rohitsood.urlybird.gui.manager.GuiManager;
import com.rohitsood.urlybird.gui.manager.GuiManagerFactory;
import com.rohitsood.urlybird.gui.server.ServerMainWindow;
import com.rohitsood.urlybird.mode.Mode;


/**
 * Launches the application in "server" mode. Reads the <tt>suncertify.properties</tt> file This automatically starts
 * the RMI registry. It binds the <tt>DatabaseNetworkServerFactory</tt> registry.
 *
 * @author Rohit Sood
 * @version 1.1
 */
class ServerLauncher implements Launcher {
    /**
     * No args default constructor creates a new <tt>ServerLauncher</tt> object.
     */
    ServerLauncher() {
    }

    /**
     * Launches the server. Reads the configuration file settings. Sets the location of the codebase to the jar
     * runtime. Sets system properties for codebase location, policy file location. Starts the registry and rebinds
     * the server factory implementation.
     */
    public void launch() {
        final GuiManagerFactory factory = new GuiManagerFactory();
        final GuiManager manager = factory.getInstance(Mode.SERVER);
        new ServerMainWindow(manager);
    }
}
