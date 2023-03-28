package com.rohitsood.urlybird.launcher;

import com.rohitsood.urlybird.gui.ClientMainWindow;
import com.rohitsood.urlybird.gui.manager.GuiManager;
import com.rohitsood.urlybird.gui.manager.GuiManagerFactory;
import com.rohitsood.urlybird.mode.Mode;


/**
 * This will launch the application client in the "client" mode. The client works online. Starts the
 * <tt>GuiManager</tt> with a remote client that connects to the remote server. Launches the main window of the
 * application.
 *
 * @author Rohit Sood
 * @version 1.2
 */
class ClientLauncher implements Launcher {
    /**
     * Default constructor. Instantiates this launcher so its ready to launch the application.
     */
    ClientLauncher() {
    }

    /**
     * Launches the application in "client" mode. Network capabilities are enabled. The main window is launched with a
     * <tt>GuiManager</tt> that uses a remote database client.
     */
    public void launch() {
        final GuiManagerFactory factory = new GuiManagerFactory();
        final GuiManager manager = factory.getInstance(Mode.ONLINE);
        new ClientMainWindow(manager);
    }
}
