package com.rohitsood.urlybird.gui.server;

import com.rohitsood.urlybird.gui.manager.GuiManager;

import javax.swing.JMenuItem;


/**
 * Menu item that triggers the server to start or stop.
 * The menu item toggles. If the server is started Stop Server is displayed.
 * When the server is stopped the Start Server is displayed.
 *
 * @author Rohit Sood
 * @version 1.0
 */
public class ServerModeMenuItem extends JMenuItem {
    /**Text for start server.*/
    public static final String START_SERVER = "Start Server";

    /**Text for stop server.*/
    public static final String STOP_SERVER = "Stop Server";

    /**Manager to register this with.*/
    private GuiManager manager;

    /**Server mode indicator.*/
    private boolean started = false;

    /**
     * Creates a new <tt>ServerModeMenuItem</tt> object.
     *
     * @param manager The manager to register this with.
     */
    public ServerModeMenuItem(GuiManager manager) {
        this.manager = manager;
        manager.setServerModeMenuItem(this);
        init();
    }

    /**
     * Initialize the server mode.
     */
    private void init() {
        addActionListener(new ServerModeMenuItemListener(manager));
        setServerStartedText();
        setMnemonic('S');
    }

    /**
     * Sets the mode to started.
     * Text is set accordingly.
     * @param started Indication of server mode.
     */
    public void setStarted(boolean started) {
        this.started = started;
        setServerStartedText();
    }

    /**
     * Sets the server mode menu item text based on server state.
     * If server is started the stop server text is shown.
     * If the server is stopped the start server text is shown.
     */
    private void setServerStartedText() {
        if (!started) {
            setText(START_SERVER);
        } else {
            setText(STOP_SERVER);
        }
    }
}
