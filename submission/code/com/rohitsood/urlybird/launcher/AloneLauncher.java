package com.rohitsood.urlybird.launcher;

import com.rohitsood.urlybird.gui.ClientMainWindow;
import com.rohitsood.urlybird.gui.manager.GuiManager;
import com.rohitsood.urlybird.gui.manager.GuiManagerFactory;
import com.rohitsood.urlybird.mode.Mode;


/**
 * Launches the application client in the "alone" mode. The client works offline. Starts the
 * <tt>GuiManager</tt> with a local client that connects to the local database. Launches the main window of the
 * application.
 *
 * @author Rohit Sood
 * @version 1.2
 */
class AloneLauncher implements Launcher {
    /**
     * Default constructor. Instantiates this launcher so its ready to launch the application.
     */
    AloneLauncher() {
    }

    /**
     * Launches the application in "alone" mode. Network capabilities are disabled. The main window is launched with a
     * <tt>GuiManager</tt> that uses a local database client.
     */
    public void launch() {
        final GuiManagerFactory factory = new GuiManagerFactory();
        final GuiManager manager = factory.getInstance(Mode.OFFLINE);
        new ClientMainWindow(manager);
    }
}
