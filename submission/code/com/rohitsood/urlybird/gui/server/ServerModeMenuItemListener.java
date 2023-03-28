package com.rohitsood.urlybird.gui.server;

import com.rohitsood.urlybird.gui.manager.GuiManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Listener for mode menu item.
 * Delegates calls to the <tt>GuiManager</tt>.
 * Makes calls to start or stop the server.
 *
 * @author Rohit Sood
 * @version 1.3
 */
class ServerModeMenuItemListener implements ActionListener {
    /**The manager to delegate calls to.*/
    private GuiManager manager;

    /**
     * Creates a new <tt>ServerModeMenuItemListener</tt> object.
     *
     * @param manager The manager to delegate to.
     */
    public ServerModeMenuItemListener(GuiManager manager) {
        this.manager = manager;
    }

    /**
     * Starts the server.
     * Delegates the call to the <tt>GuiManager</tt> to start or stop the server.
     */
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals(ServerModeMenuItem.START_SERVER)) {
            manager.startServer();
        } else {
            manager.stopServer();
        }
    }
}
