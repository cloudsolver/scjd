package com.rohitsood.urlybird.gui.server;

import com.rohitsood.urlybird.gui.AbstractMainWindow;
import com.rohitsood.urlybird.gui.manager.GuiManager;
import com.rohitsood.urlybird.gui.widget.ExceptionOptionPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * The sever implemention of <tt>AbstractMainWindow</tt> for the server.  When the application server is started this
 * main window is invoked.
 *
 * @author Rohit Sood
 * @version 1.1
 */
public class ServerMainWindow extends AbstractMainWindow {
    /**
     * Creates a new ServerMainWindow object.
     *
     * @param manager The manager to register this window with.
     */
    public ServerMainWindow(GuiManager manager) {
        super.manager = manager;
        manager.setMainWindow(this);
        init();
    }

    /**
     * Initializes the window listener.
     */
    protected void initListener() {
        final ServerMainWindow mainWindow = this;
        super.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                    ExceptionOptionPane.showInformation(mainWindow,"Abort Server.",
                        "This window will close and abort the server if its started.");
                    System.exit(-1);
                }
            });
    }

    /**
     * Initalizes the window.
     * Makes calls to other initializers which initializes components.
     */
    protected void init() {
        initWindow();
        initMainMenuBar(manager);
        initSizes();
        initListener();
        initMode();
    }

    /**
     * Initializes the main menu bar.
     *
     * @param manager The manager to initialize the menu bar with.
     */
    protected void initMainMenuBar(GuiManager manager) {
        final ServerMainMenuBar menuBar = new ServerMainMenuBar(manager);
        super.setJMenuBar(menuBar);
		manager.openServerConsoleInternalFrame();
    }
}
