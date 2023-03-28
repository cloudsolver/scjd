package com.rohitsood.urlybird.gui.server;

import com.rohitsood.urlybird.gui.manager.GuiManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Listens for all actions on the clear button of the server console internal frame. Closes the internal frame by
 * erasing all data. Actions are delegate to the <tt>GuiManager</tt>.
 *
 * @author Rohit Sood
 * @version 1.0
 */
public class ServerConsoleButtonActionListener implements ActionListener {
    /** The manager to which calls are delegated */
    private GuiManager manager;

    /**
     * Creates a new <tt>AddButtonActionListener</tt> object.
     *
     * @param manager The manager to delegate to.
     */
    public ServerConsoleButtonActionListener(GuiManager manager) {
        this.manager = manager;
    }

    /**
     * Called when the add button is clicked. The action is delegate to the <tt>GuiManager</tt> for handling.
     *
     * @param event The event that occured.
     */
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals(ServerConsoleButtonPanel.CLEAR_TEXT)) {
            manager.clearServerConsole();
        } else if (event.getActionCommand().equals(ServerConsoleButtonPanel.CLOSE_TEXT)) {
            manager.closeServerConsole();
        }
    }
}
